package libember.s101;

import java.util.Iterator;
import java.util.Vector;

/**
 * This class encodes a collection of bytes and forms a valid S101 packet that
 * may be transmitted to connected clients.
 */
public final class StreamEncoder implements Iterable<Integer> {
	private final Vector<Integer> buffer = new Vector<Integer>(1024);
	boolean finished;
	int crc;

	/**
	 * Initializes a new instance of the {@link StreamEncoder} class.
	 */
	public StreamEncoder() {
	}

	/**
	 * Appends a byte to the encoding buffer. The byte will be escaped if it is
	 * greater than the Frame.INVALID value.
	 * 
	 * @param input
	 *            The value to add to the buffer.
	 */
	private void append(int input) {
		if (input < 0x00)
			input = 0x00;
		else if (input > 0xFF)
			input = 0xFF;

		if (input >= FrameByte.INVALID.value()) {
			buffer.add(Integer.valueOf(FrameByte.CE.value()));
			buffer.add(Integer.valueOf(input ^ FrameByte.XOR.value()));
		} else {
			buffer.add(Integer.valueOf(input));
		}
	}

	/**
	 * Adds a single value to the encoding buffer.
	 * 
	 * @param input
	 *            The value to append to the buffer.
	 */
	public void encode(int input) {
		if (isEmpty()) {
			crc = 0xFFFF;
			buffer.add(Integer.valueOf(FrameByte.BOF.value()));
		}

		crc = Crc16.sample(crc, input);
		append(input);
	}

	/**
	 * Adds all elements of the provided collection.
	 * 
	 * @param i
	 *            An {@link Iterable} containing the bytes to append.
	 * @throws NullPointerException
	 *             Thrown if {@link i} is <i>null</i>.
	 */
	public void encode(Iterable<Integer> i) throws NullPointerException {
		for (int input : i) {
			encode(input);
		}
	}

	/**
	 * Adds all elements the provided {@link Iterator} emits.
	 * 
	 * @param i
	 *            The iterator providing the elements to append.
	 * @throws NullPointerException
	 *             Thrown if {@link i} is <i>null</i>.
	 */
	public void encode(Iterator<Integer> i) throws NullPointerException {
		while (i.hasNext()) {
			encode(i.next());
		}
	}

	/**
	 * Finishes the current packet so that it can be transmitted.
	 */
	public void finish() {
		if (finished == false) {
			finished = true;
			crc = (~crc) & 0xFFFF;
			append((crc >> 0) & 0xFF);
			append((crc >> 8) & 0xFF);
			buffer.add(FrameByte.EOF.value());
		}
	}

	/**
	 * Gets a value indicating whether the buffer of this instance is empty.
	 * 
	 * @return <i>true</i> if the encoder buffer is empty.
	 */
	public boolean isEmpty() {
		return buffer.isEmpty();
	}

	/**
	 * Gets a value indicating whether the current packet is complete. A packet
	 * must be completed manually by calling {@link StreamEncoder.finish}.
	 * 
	 * @return <i>true</i> if the packet has already been completed.
	 */
	public boolean isFinished() {
		return finished;
	}

	@Override
	public Iterator<Integer> iterator() {
		return buffer.iterator();
	}

	/**
	 * Resets the encoder.
	 */
	public void reset() {
		buffer.clear();
		crc = 0xFFFF;
		finished = false;
	}

	/**
	 * Gets the current size of this buffer.
	 * 
	 * @return The current size of this buffer.
	 */
	public int size() {
		return buffer.size();
	}

	/**
	 * Converts the current buffer content into an array of bytes and returns
	 * the result. Please note that {@link StreamEncoder.finish} must be called
	 * prior to invoking this method. Otherwise, an exception will be thrown.
	 * 
	 * @return A byte array containing the current buffer elements.
	 * @throws UnsupportedOperationException
	 *             Thrown if {@link StreamEncoder.finish} has not yet been
	 *             called.
	 */
	public byte[] toArray() throws UnsupportedOperationException {
		if (isFinished() == false) {
			throw new UnsupportedOperationException(
					"finish() must be called prior to invoking the toArray() method.");
		}

		final byte[] bytes = new byte[size()];

		int index = 0;

		for (int i : this) {
			bytes[index++] = (byte) (i & 0xFF);
		}

		return bytes;
	}
}
