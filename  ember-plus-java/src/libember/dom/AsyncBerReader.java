package libember.dom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import libember.ber.Encoding;
import libember.ber.Length;
import libember.ber.Oid;
import libember.ber.Tag;
import libember.ber.Type;
import libember.ber.UniversalType;
import libember.ber.Value;
import libember.util.MemoryStream;

;

/**
 * Base class for an BER reader which is able to decode fragmented ember packets
 * and which uses a set of callback methods to inform an implementation about
 * the completion of decoded nodes, items and the root node.
 */
public abstract class AsyncBerReader {
	/**
	 * Enumeration listing the available decoding states of this reader.
	 */
	private enum DecodeState {
		Tag, Length, Value, Terminator,
	}
	private final Stack<AsyncContainer> stack = new Stack<AsyncContainer>();
	private final MemoryStream inputStream = new MemoryStream();
	private final MemoryStream valueBuffer = new MemoryStream();
	private AsyncContainer container = null;
	private DecodeState state = DecodeState.Tag;
	private int bytesRead = 0;
	private int bytesExpected = 0;
	private int length = 0;
	private int outerLength = 0;
	private boolean isContainer = false;
	private Tag appTag = Tag.ZERO;

	private Tag typeTag = Tag.ZERO;

	/**
	 * Initializes a new instance of the {@link AsyncBerReader} class.
	 */
	protected AsyncBerReader() {

	}

	/**
	 * Resets the current type and length information.
	 */
	private void disposeTLV() {
		appTag = Tag.ZERO;
		typeTag = Tag.ZERO;
		isContainer = false;
		length = 0;
		outerLength = 0;
		valueBuffer.clear();
	}

	/**
	 * Removes the current {@link Container} from the stack.
	 * 
	 * @return <i>true</i>, if a container has been popped from the stack.
	 */
	private boolean popContainer() {
		reset(DecodeState.Tag);
		if (stack.empty() == false) {
			AsyncContainer container = stack.pop();

			appTag = container.tag();
			typeTag = container.type();
			length = container.length();
			isContainer = true;

			itemReady();
			if (stack.empty()) {
				this.container = null;
			} else {
				this.container = stack.peek();
				this.container.setBytesRead(length, true);
			}

			disposeTLV();
			return true;
		}
		return false;
	}

	/**
	 * Copies the current data buffer to the value buffer which is then used to
	 * decode the value.
	 * 
	 * @throws NoSuchElementException
	 *             Thrown if the {@link InputStream} contains no more elements.
	 */
	private void preloadValue() throws NoSuchElementException {
		valueBuffer.clear();

		int valueLength = Math.min(inputStream.size(), length);

		for (/* Nothing */; valueLength > 0; valueLength--) {
			valueBuffer.append(inputStream.peek());
			inputStream.consume();
		}

		inputStream.clear();
		reset(DecodeState.Tag);
		itemReady();
		disposeTLV();
	}

	/**
	 * Adds a new {@link AsyncContainer} to the container stack.
	 */
	private void pushContainer() {
		container = new AsyncContainer(appTag, typeTag, length);
		stack.push(container);
	}

	/**
	 * Reads a single byte when the decoding state {@link DecodeState.Length}.
	 * 
	 * @param input
	 *            The current byte.
	 * @return <i>true</i>, if the end of the current container has been
	 *         reached.
	 * @throws UnsupportedOperationException
	 *             Thrown if the length contains for than five bytes or when an
	 *             outer length of zero has been detected.
	 * @throws NoSuchElementException
	 *             Thrown if the {@link InputStream} contains no more elements.
	 */
	private boolean readLengthByte(int input)
			throws UnsupportedOperationException, NoSuchElementException {
		if (bytesExpected == 0) {
			if ((input & 0x80) != 0) {
				bytesExpected = (input & 0x7F) + 1;
			} else {
				bytesExpected = 1;
			}

			if (bytesExpected > 5)
				throw new UnsupportedOperationException(
						"Number of length octets out of bounds");
		}

		bytesRead++;

		if (bytesRead == bytesExpected) {
			final Type type = Type.fromTag(typeTag);
			if (type.value() == 0) {
				outerLength = Encoding.decode(inputStream, Encoding.LENGTH)
						.value();

				if (outerLength == 0)
					throw new UnsupportedOperationException(
							"Zero outer length encountered");

				reset(DecodeState.Tag);
			} else {
				length = Encoding.decode(inputStream, Encoding.LENGTH).value();

				final boolean isEofOk = length == 0;

				if (isContainer) {
					reset(DecodeState.Tag);
					containerReady();
					pushContainer();
					disposeTLV();
					return isEofOk;
				}

				if (length == 0) {
					preloadValue();
				} else {
					reset(DecodeState.Value);
				}

				return isEofOk;
			}
		}

		return false;
	}

	/**
	 * Reads a single byte when the decoding state is {@link DecodeState.Tag}.
	 * 
	 * @param input
	 *            The current byte
	 * @return This method always returns false.
	 * @throws UnsupportedOperationException
	 *             Thrown if a {@link Tag} contains too many items or is of an
	 *             invalid format.
	 * @throws NoSuchElementException
	 *             Thrown if the internal {@link MemoryStream} has no more
	 *             elements although a value is being decoded.
	 */
	private boolean readTagByte(int input) throws NoSuchElementException,
			UnsupportedOperationException {
		if (input == 0 && bytesRead == 0) {
			reset(DecodeState.Terminator);
			return false;
		}

		if (bytesRead > 12)
			throw new UnsupportedOperationException(
					"Number of tag octets out of bounds");

		if ((bytesRead == 0 && (input & 0x1F) != 0x1F)
				|| (bytesRead > 0 && (input & 0x80) == 0x00)) {
			if (appTag.isZero()) {
				final Tag tag = Encoding.decode(inputStream, Encoding.TAG);

				appTag = tag.toPrimitive();
			} else {
				final Tag tag = Encoding.decode(inputStream, Encoding.TAG);

				isContainer = tag.isContainer();
				typeTag = tag;
			}

			reset(DecodeState.Length);
			return false;
		}

		bytesRead++;
		return false;
	}

	/**
	 * Reads a single byte when the decoding state
	 * {@link DecodeState.Terminator}. This method is required when a container
	 * uses the indefinite length form.
	 * 
	 * @param input
	 *            The current byte.
	 * @throws UnsupportedOperationException
	 *             Thrown if the current <code>container</code> is
	 *             <code>null</code> or its length is not set to
	 *             {@link Length.INDEFINITE}. Or, if a non zero byte has been
	 *             read within the terminator.
	 */
	private boolean readTerminatorByte(int input)
			throws UnsupportedOperationException {
		if (container == null
				|| container.length() != Length.INDEFINITE.value())
			throw new UnsupportedOperationException("Unexpected terminator");

		if (input == 0) {
			bytesRead++;

			if (bytesRead == 3) {
				// End of indefinite length container
				if (container != null)
					container.setLength(container.bytesRead());

				return true;
			}
		} else {
			throw new UnsupportedOperationException(
					"Non-Zero byte in terminator");
		}

		return false;
	}

	/**
	 * Reads a single byte when the decoding state {@link DecodeState.Value}.
	 * 
	 * @param input
	 *            The current byte.
	 * @return <i>true</i>, if the end of the current container has been
	 *         reached.
	 * @throws NoSuchElementException
	 *             Thrown if a value is being preloaded but the
	 *             {@link InputStream} has no more elements.
	 */
	private boolean readValueByte(int input) throws NoSuchElementException {
		if (bytesRead == 0)
			bytesExpected = length;

		bytesRead++;

		if (bytesRead == bytesExpected) {
			preloadValue();
			return true;
		}

		return false;
	}

	/**
	 * Resets the decoding state.
	 * 
	 * @param state
	 *            The new {@link DecodeState}.
	 */
	private void reset(DecodeState state) {
		this.inputStream.clear();
		this.state = state;
		this.bytesExpected = 0;
		this.bytesRead = 0;
	}

	/**
	 * This <i>abstract</i> method is invoked when a {@link Container} has been
	 * decoded.
	 */
	protected abstract void containerReady();

	/**
	 * Decodes a {@link Node} whose concrete type is determined by the current
	 * {@link Tag}. The provided {@link NodeFactory} is used when the tag is
	 * marked as application defined (see {@link Type.isApplicationDefined}).
	 * 
	 * @param factory
	 *            The {@link NodeFactory} that is used to create user defined
	 *            nodes.
	 * @return The decoded {@link Node} instance or <i>null</i>, if no node
	 *         could be created.
	 * @throws NoSuchElementException
	 *             Thrown if a {@link Value} is being decoded but the
	 *             {@link InputStream} contains no more elements.
	 */
	protected Node decode(NodeFactory factory) throws NoSuchElementException {
		final Type type = Type.fromTag(typeTag);
		final Tag tag = appTag;

		if (type.isApplicationDefined() == false) {
			if (isContainer) {
				if (type.value() == UniversalType.SET.value()) {
					return new Set(tag);
				} else if (type.value() == UniversalType.SEQUENCE.value()) {
					return new Sequence(tag);
				} else {
					return null;
				}
			} else {
				final Value value = decode(type);

				if (value != null) {
					return new Leaf(tag, value);
				}
			}
			return null;
		} else {
			return factory.createApplicationDefinedNode(type, tag);
		}
	}

	/**
	 * Decodes a value from the current value buffer.
	 * 
	 * @param type
	 *            The decoded {@link Type} of the value to decode.
	 * @return A new instance of the {@link Value} class which contains the
	 *         decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if the {@link InputStream} has no more elements.
	 */
	protected Value decode(Type type) {
		final UniversalType universalType = UniversalType.valueOf(type.value());
		final int encodedLength = valueBuffer.size();

		switch (universalType) {
		case BOOLEAN: {
			final boolean value = Encoding
					.decode(valueBuffer, Encoding.BOOLEAN);
			return new Value(value);
		}
		case INTEGER: {
			final long value = Encoding.decode(valueBuffer, encodedLength,
					Encoding.LONG);
			return new Value(value);
		}
		case REAL: {
			final double value = Encoding.decode(valueBuffer, encodedLength,
					Encoding.DOUBLE);
			return new Value(value);
		}
		case UTF8STRING: {
			final String value = Encoding.decode(valueBuffer, encodedLength,
					Encoding.STRING);
			return new Value(value);
		}
		case RELATIVEOID: {
			final Oid value = Encoding.decode(valueBuffer, encodedLength,
					Encoding.OID);
			return new Value(value);
		}
		default: {
			return null;
		}
		}
	}

	/**
	 * Gets a value indicating whether the current item is a {@link Container}.
	 * 
	 * @return <i>true</i>, when the current item is a {@link Container}.
	 */
	protected boolean isContainer() {
		return isContainer;
	}

	/**
	 * This <i>abstract</i> method is invoked when a {@link Node} of any type
	 * has been decoded.
	 */
	protected abstract void itemReady();

	/**
	 * Gets the encoded length of the current item.
	 * 
	 * @return The encoded length of the current item.
	 */
	protected int length() {
		return length;
	}

	/**
	 * This <i>abstract</i> method is invoked when calling the {@link reset}.
	 */
	protected abstract void resetImpl();

	/**
	 * Parses a single byte.
	 * 
	 * @param input
	 *            The current byte.
	 * @throws UnsupportedOperationException
	 *             Thrown if the end of container has been reached but the
	 *             {@link InputStream} still contains items, or if a general
	 *             decoding error occurred.
	 * @throws NoSuchElementException
	 *             Thrown if a {@link Value} is being decoded but the
	 *             {@link InputStream} contains no more elements.
	 */
	public void read(int input) throws UnsupportedOperationException,
			NoSuchElementException {
		inputStream.append(input);

		if (container != null)
			container.setBytesRead(1, true);

		boolean isEofOk = false;

		switch (state) {
		case Tag:
			isEofOk = readTagByte(input);
			break;
		case Length:
			isEofOk = readLengthByte(input);
			break;
		case Value:
			isEofOk = readValueByte(input);
			break;
		case Terminator:
			isEofOk = readTerminatorByte(input);
			break;
		}

		while (container != null && container.eof()) {
			if (isEofOk == false)
				throw new UnsupportedOperationException(
						"Unexpected end of container");

			popContainer();
		}
	}

	/**
	 * Decodes the provided collection of integers.
	 * 
	 * @param iterator
	 *            The iterator referencing the integer collection.
	 * @throws UnsupportedOperationException
	 *             Thrown if the end of container has been reached but the
	 *             {@link InputStream} still contains items, or if a general
	 *             decoding error occurred.
	 * @throws NoSuchElementException
	 *             Thrown if a {@link Value} is being decoded but the
	 *             {@link InputStream} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link iterator} is <i>null</i>.
	 */
	public void read(Iterator<Integer> iterator) throws NoSuchElementException,
			UnsupportedOperationException, NullPointerException {
		while (iterator.hasNext()) {
			final int input = iterator.next().intValue() & 0xFF;

			read(input);
		}
	}

	/**
	 * Resets the complete state of the reader.
	 */
	public void reset() {
		stack.clear();
		container = null;
		disposeTLV();
		reset(DecodeState.Tag);
		resetImpl();
	}
}
