package libember.glow;

/**
 * Scoped enumeration listing the symbolic names of the supported parameter
 * types.
 */
public enum ParameterType {
	/**
	 * The type is not specified.
	 */
	UNKNOWN(0),

	/**
	 * A signed integer.
	 */
	INTEGER(1),

	/**
	 * A decimal value.
	 */
	REAL(2),

	/**
	 * An UTF8 encoded string.
	 */
	STRING(3),

	/**
	 * A boolean value.
	 */
	BOOLEAN(4),

	/**
	 * The parameter is used to invoke a command.
	 */
	TRIGGER(5),

	/**
	 * The parameter contains a collection of strings that shall be displayed
	 * instead of the numeric representation. The value type of an enumeration
	 * must be integer.
	 */
	ENUM(6),

	/**
	 * A collection of bytes.
	 */
	OCTETS(7);

	/**
	 * Gets the {@link ParameterType} represented by the passed value.
	 * 
	 * @param value
	 *            The value to get the associated {@link ParameterType} for.
	 * @return The {@link ParameterType} represented by the passed value.
	 */
	public static ParameterType valueOf(int value) {
		switch (value) {
		case 1:
			return INTEGER;
		case 2:
			return REAL;
		case 3:
			return STRING;
		case 4:
			return BOOLEAN;
		case 5:
			return TRIGGER;
		case 6:
			return ENUM;
		case 7:
			return OCTETS;
		default:
			return UNKNOWN;
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link ParameterType} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private ParameterType(int value) {
		this.value = value;
	}

	/**
	 * Compares the {@link value} against the internal value of this
	 * {@link ParameterType}.
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
	 * Gets the numeric representation of the {@link ParameterType}.
	 * 
	 * @return The numeric representation of the {@link ParameterType}.
	 */
	public int value() {
		return value;
	}
}
