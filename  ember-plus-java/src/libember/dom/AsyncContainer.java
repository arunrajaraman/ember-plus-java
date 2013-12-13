package libember.dom;

import libember.ber.Length;
import libember.ber.Tag;

/**
 * This class stores the state of a nested container within an ber tree that is
 * being decoded by the {@link AsyncBerReader} or {@link AsyncDomReader} class.
 */
final class AsyncContainer {
	private Tag tag;
	private Tag type;
	private int length;
	private int bytesRead;

	/**
	 * Initializes a new instance of the {@link AsyncContainer} class.
	 * 
	 * @param tag
	 *            The outer {@link Tag} of the container.
	 * @param type
	 *            The inner {@link Tag} of the container, defining the type.
	 * @param length
	 *            The initial container length.
	 */
	public AsyncContainer(Tag tag, Tag type, int length) {
		this.tag = tag;
		this.type = type;
		this.length = length;
	}

	/**
	 * Gets the number of bytes already read.
	 * 
	 * @return The number of bytes already read.
	 */
	public int bytesRead() {
		return bytesRead;
	}

	/**
	 * Gets a value indicating whether all bytes of this container have already
	 * been read.
	 * 
	 * @return <i>true</i>, if all bytes have been read.
	 */
	public boolean eof() {
		return bytesRead >= length && length != Length.INDEFINITE.value();
	}

	/**
	 * Gets the encoded container length.
	 * 
	 * @return The encoded container length.
	 */
	public int length() {
		return length;
	}

	/**
	 * Sets or increments the number of bytes read.
	 * 
	 * @param bytes
	 *            The number of bytes to add to the current value or, if the
	 *            increment flag is set to false, the new value to set.
	 * @param increment
	 *            If set to <code>true</code>, the passed value will be added to
	 *            the current value. Otherwise, the {@link bytesRead} property
	 *            will be set to the value specified in {@link bytes}.
	 */
	public void setBytesRead(int bytes, boolean increment) {
		if (increment)
			bytesRead += bytes;
		else
			bytesRead = bytes;
	}

	/**
	 * Sets the container length.
	 * 
	 * @param length
	 *            The new length to set.
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Gets the application {@link Tag} of this container.
	 * 
	 * @return The application {@link Tag} of this container.
	 */
	public Tag tag() {
		return tag;
	}

	/**
	 * Gets the type {@link Tag} of this container.
	 * 
	 * @return The type {@link Tag} of this container.
	 */
	public Tag type() {
		return type;
	}
}
