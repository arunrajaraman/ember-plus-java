package libember.s101;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the supported DTD types, which is currently only
 * {@link Dtd.GLOW}.
 */
public enum Dtd {
	/**
	 * The Glow Dtd, which is used to describe and control a device via Ember+.
	 */
	GLOW(0x01);

	/**
	 * Gets the {@link Dtd} instance that is represented by the provided
	 * {@link value}.
	 * 
	 * @param value
	 *            The value to get the associated {@link Dtd} instance for.
	 * @return The {@link Dtd} instance that is represented by the provided
	 *         {@link value}.
	 * @exception NoSuchElementException
	 *                Thrown when an invalid value has been specified.
	 */
	public static Dtd valueOf(int value) throws NoSuchElementException {
		if (value == 0)
			return GLOW;
		else
			throw new NoSuchElementException(
					"value does not specify a valid Dtd instance.");
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link Dtd} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private Dtd(int value) {
		this.value = value;
	}

	/**
	 * Gets integer representation of this instance.
	 * 
	 * @return The integer representation of this instance.
	 */
	public int value() {
		return value;
	}
}
