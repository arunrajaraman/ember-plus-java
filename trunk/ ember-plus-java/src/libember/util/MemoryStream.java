package libember.util;

import java.util.Vector;

/**
 * This class implements the {@link InputStream} and {@link OutputStream}
 * interfaces and can be used to encode or decode an Ember+ tree or primitive
 * ber types. When using the methods of the {@link InputStream} interface, the
 * internal buffer is shrinking, while it is growing when using the methods of
 * the {@link OutputStream} interface. So one instance can be used for both
 * directions, if necessary.
 */
public final class MemoryStream implements InputStream, OutputStream {
	private final Vector<Integer> vector = new Vector<Integer>(1024);

	/**
	 * Initializes a new instance of the {@link MemoryStream} class, which
	 * contains no elements.
	 */
	public MemoryStream() {
	}

	/**
	 * Initializes a new instance of the {@link MemoryStream} class.
	 * 
	 * @param data
	 *            The initial data to copy.
	 * @throws NullPointerException
	 *             Thrown if {@link data} is <code>null</code>.
	 */
	public MemoryStream(byte[] data) throws NullPointerException {
		Assert.AssertNotNull(data, "data");

		vector.setSize(data.length);

		for (int i = 0; i < data.length; ++i) {
			final int value = Integer.valueOf(data[i] & 0xFF);
			vector.set(i, value);
		}
	}

	/**
	 * Initializes a new instance of the {@link MemoryStream} class.
	 * 
	 * @param data
	 *            The initial data to copy.
	 * @throws NullPointerException
	 *             Thrown if {@link data} is <code>null</code>.
	 */
	public MemoryStream(int[] data) throws NullPointerException {
		Assert.AssertNotNull(data, "data");

		vector.setSize(data.length);

		for (int i = 0; i < data.length; ++i) {
			final int value = Integer.valueOf(data[i] & 0xFF);
			vector.set(i, value);
		}
	}

	public void append(int value) {
		vector.add(Integer.valueOf(value & 0xFF));
	}

	/**
	 * Removes all elements from the stream.
	 */
	public void clear() {
		vector.clear();
	}

	public void consume() {
		vector.remove(0);
	}

	public int peek() {
		return vector.firstElement().intValue() & 0xFF;
	}

	/**
	 * Gets the number of elements the stream currently contains.
	 * 
	 * @return The number of elements the stream contains.
	 */
	public int size() {
		return vector.size();
	}

	public byte[] toArray() {
		final byte[] array = new byte[size()];

		for (int i = 0; i < array.length; ++i) {
			array[i] = vector.elementAt(i).byteValue();
		}
		return array;
	}
}
