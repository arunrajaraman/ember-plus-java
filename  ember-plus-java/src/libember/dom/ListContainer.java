package libember.dom;

import java.util.Iterator;
import java.util.Vector;

import libember.ber.Encoding;
import libember.ber.Length;
import libember.ber.Tag;
import libember.util.OutputStream;

/**
 * This class servers as a base class for the {@link Set} and the
 * {@link Sequence}.
 */
abstract class ListContainer extends Container {
	private final Vector<Node> children = new Vector<Node>();
	private int cachedLength = 0;

	/**
	 * Initializes a new instance of the {@link ListContainer} class.
	 * 
	 * @param applicationTag
	 *            The application {@link Tag} of this istance.
	 * @throws NullPointerException
	 *             Thrown if {@link applicationTag} is <i>null</i>.
	 */
	protected ListContainer(Tag applicationTag) throws NullPointerException {
		super(applicationTag);
	}

	/**
	 * Gets the accumulated encoded length of all children of this container.
	 * 
	 * @return The accumulated encoded length of all children of this container.
	 */
	private int encodedPayloadLength() {
		int payloadLength = 0;

		for (Iterator<Node> it = iterator(); it.hasNext();) {
			payloadLength += it.next().encodedLength();
		}
		return payloadLength;
	}

	/**
	 * Encodes the children of this container.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded data to.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>.
	 */
	private void encodePayload(OutputStream output) throws NullPointerException {
		for (Iterator<Node> it = iterator(); it.hasNext(); /** Nothing */
		) {
			it.next().encode(output);
		}
	}

	@Override
	protected int encodedLengthImpl() {
		return cachedLength;
	}

	@Override
	protected void encodeImpl(OutputStream output) {
		final Tag innerContainerTag = typeTag().toContainer();
		final int innerTagLength = Encoding.encodedLength(innerContainerTag);
		final int payloadLength = encodedPayloadLength();
		final int innerLength = innerTagLength
				+ Encoding.encodedLength(new Length(payloadLength))
				+ payloadLength;

		Encoding.encode(output, applicationTag().toContainer());
		Encoding.encode(output, new Length(innerLength));
		Encoding.encode(output, innerContainerTag);
		Encoding.encode(output, new Length(payloadLength));
		encodePayload(output);
	}

	@Override
	protected void eraseImpl(int index) {
		children.remove(index);
	}

	@Override
	protected void insertImpl(int index, Node node) {
		children.insertElementAt(node, index);
	}

	@Override
	protected Iterator<Node> iteratorImpl() {
		return children.iterator();
	}

	@Override
	protected void updateImpl() {
		final int innerTagLength = Encoding.encodedLength(typeTag()
				.toContainer());
		final int payloadLength = encodedPayloadLength();
		final int innerLength = innerTagLength
				+ Encoding.encodedLength(new Length(payloadLength))
				+ payloadLength;
		final int outerTagLength = Encoding.encodedLength(applicationTag()
				.toContainer());
		final int outerLength = outerTagLength
				+ Encoding.encodedLength(new Length(innerLength)) + innerLength;

		this.cachedLength = outerLength;
	}

	@Override
	public boolean isEmpty() {
		return children.isEmpty();
	}

	@Override
	public int size() {
		return children.size();
	}
}
