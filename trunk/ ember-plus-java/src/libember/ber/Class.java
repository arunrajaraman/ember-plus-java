package libember.ber;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration containing the available types of a class. The
 * {@link Class} is a component of a {@link Tag} and may be used to either
 * indicate the semantics or the type of an object.
 */
public enum Class {
	/**
	 * The Universal class is reserved for well known ber types, like integer,
	 * real or octet string. It must not be used for user-defined types or as
	 * application tag.
	 */
	UNIVERSAL(0x00),

	/**
	 * Application specific tags have the same meaning wherever they appear.
	 */
	APPLICATION(0x40),

	/**
	 * The meaning of a context specific tag depends - like the name indicates -
	 * on the location of the tag.
	 */
	CONTEXT(0x80),

	/**
	 * Private tags are usually not being used.
	 */
	PRIVATE(0xC0);

	private final int value;

	/**
	 * Gets a mask which can be used to determine the class by performing a
	 * binary-and operation. It is required to extract the {@link Class} value
	 * from an integer, since the lower nibble might contain additional data
	 * that needs to be masked out.
	 */
	public static final int MASK = 0xC0;

	/**
	 * Gets the {@link Class} representation of the specified numeric value.
	 * 
	 * @param value
	 *            The numeric value to look up
	 * @return The {@link Class} representation of the passed numeric value.
	 * @throws NoSuchElementException
	 *             Thrown if an invalid value is passed.
	 */
	public static Class valueOf(int value) throws NoSuchElementException {
		switch (value) {
		case 0x00:
			return UNIVERSAL;
		case 0x40:
			return APPLICATION;
		case 0x80:
			return CONTEXT;
		case 0xC0:
			return PRIVATE;
		}

		throw new NoSuchElementException("value is invalid.");
	}

	/**
	 * Initializes a new instance of the {@link Class} class.
	 * 
	 * @param value
	 *            The internal numeric value of this instance.
	 */
	private Class(int value) {
		this.value = value;
	}

	/**
	 * Compares the value against the internal value of this {@link Class}.
	 * 
	 * @param value
	 *            The value to compare.
	 * @return <i>true</i>, if both values are equal. Otherwise, this method
	 *         returns <i>false</i>.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	/**
	 * Gets the numeric value of the {@link Class}.
	 * 
	 * @return The numeric value of the {@link Class}.
	 */
	public int value() {
		return this.value;
	}
}
