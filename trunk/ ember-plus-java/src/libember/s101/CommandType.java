package libember.s101;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the symbolic names of the supported S101 command
 * types.
 */
public enum CommandType {
	/**
	 * The message contains an Ember+ packet. This packet may be fragmented.
	 * Please see the Ember+ documentation for details.
	 */
	EMBER(0x00),

	/**
	 * This message can be sent by both, a consumer and a provider. The instance
	 * receiving this message must reply with a
	 * {@link CommandType.KEEPALIVERESPONSE} message.
	 */
	KEEPALIVEREQUEST(0x01),

	/**
	 * This is the response to the {@link CommandType.KEEPALIVERESPONSE}.
	 */
	KEEPALIVERESPONSE(0x02);

	/**
	 * Gets the {@link CommandType} that is associated with the provided
	 * {@link value}.
	 * 
	 * @param value
	 *            The value to get the associated {@link CommandType} for.
	 * @return The {@link CommandType} represented by {@link value}.
	 * @exception NoSuchElementException
	 *                Thrown if {@link value} specifies an invalid number.
	 */
	public static CommandType valueOf(int value) throws NoSuchElementException {
		if (value == 0)
			return EMBER;
		else if (value == 1)
			return KEEPALIVEREQUEST;
		else if (value == 2)
			return KEEPALIVERESPONSE;
		else
			throw new NoSuchElementException("The value is invalid.");
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link CommandType} class.
	 * 
	 * @param value
	 *            The value initialize this instance with.
	 */
	private CommandType(int value) {
		this.value = value;
	}

	/**
	 * Compares this instance of {@link CommandType} with the one provided in
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
	 * Gets the numeric representation of this command.
	 * 
	 * @return The numeric representation of this command.
	 */
	public int value() {
		return value;
	}
}
