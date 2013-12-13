package libember.s101;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the message types supported by this library.
 */
public enum MessageType {
	/**
	 * Ember encoded message. The next byte in the message is the
	 * {@link CommandType}.
	 */
	EMBER(0x0E);

	/**
	 * Gets the {@link MessageType} instance that represents the provided
	 * {@link value}. If the specified value does not represent a valid
	 * {@link MessageType} an exception is thrown.
	 * 
	 * @param value
	 *            The numeric type value to lookup.
	 * @return The {@link MessageType} that represents the provided value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link value} is invalid.
	 */
	public static MessageType valueOf(int value) throws NoSuchElementException {
		if (value == 0x0E)
			return EMBER;
		else
			throw new NoSuchElementException("The value is invalid.");
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link MessageType} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private MessageType(int value) {
		this.value = value;
	}

	/**
	 * Compares this instance of {@link MessageType} with the one provided in
	 * {@link value} for equality.
	 * 
	 * @param value
	 *            The value to compare with this instance.
	 * @return <i>true</i>, if both values are equal.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	/**
	 * Gets the numeric representation of this enumeration.
	 * 
	 * @return The numeric representation of this enumeration.
	 */
	public int value() {
		return value;
	}
}
