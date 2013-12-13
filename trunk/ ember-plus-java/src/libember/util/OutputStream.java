package libember.util;

/**
 * The interface which is used by the {@link libember.ber.Encoding} class to
 * encode a value and by {@link libember.dom.Node} and classes deriving from
 * that class to encode a complete Ember+ tree.
 */
public interface OutputStream {
	/**
	 * Adds a new value to the passed buffer. The implementation must ensure
	 * that the value has a range from 0x00 to 0xFF inclusive; which is the
	 * range of an unsigned byte. Larger values are not allowed.
	 * 
	 * @param value
	 *            The value to append.
	 */
	void append(int value);

	/**
	 * Converts the current content of the output stream to an array of bytes.
	 * 
	 * @return The current stream content as byte array.
	 */
	byte[] toArray();
}
