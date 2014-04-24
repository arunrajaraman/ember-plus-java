package libember.glow;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the symbolic names of the available parameter
 * properties.
 */
public enum ParameterProperty {
	/**
	 * Identifies the parameter identifier.
	 */
	IDENTIFIER(0), 
	
	/**
	 * Identifies the parameter description string, which is a display string.
	 */
	DESCRIPTION(1), 
	
	/**
	 * Identifies the parameter value.
	 */
	VALUE(2), 
	
	/**
	 * Identifies the minimum of a parameter value. 
	 */
	MINIMUM(3), 
	
	/**
	 * Identifies the maximum of a parameter value.
	 */
	MAXIMUM(4), 
	
	/**
	 * Identifies the access modifier of a parameter.
	 */
	ACCESS(5), 
	
	/**
	 * Identifies the format string.
	 */
	FORMAT(6), 
	
	/**
	 * Identifies the enumeration entries.
	 */
	ENUMERATION(7), 
	
	/**
	 * Identifies the factor.
	 */
	FACTOR(8), 
	
	/**
	 * Identifies the online flag.
	 */
	ISONLINE(9), 
	
	/**
	 * Identifies the formula.
	 */
	FORMULA(10), 
	
	/**
	 * Identifies the step of a parameter value.
	 */
	@Deprecated
	STEP(11), 
	
	/**
	 * Identifies the default value property.
	 */
	DEFAULT(12), 
	
	/**
	 * Identifies the parameter type hint.
	 */
	TYPE(13), 
	
	/**
	 * Identifies the stream identifier.
	 */
	STREAMIDENTIFIER(14), 
	
	/**
	 * Identifies the enumeration map.
	 */
	ENUMMAP(15), 
	
	/**
	 * Identifies the stream descriptor.
	 */
	STREAMDESCRIPTOR(16),
	
	/**
	 * Identifies the schema identifier.
	 */
	SCHEMAIDENTIFIERS(17);

	/**
	 * Gets the {@link ParameterProperty} represented by the passed numeric
	 * value.
	 * 
	 * @param value
	 *            The numeric value representing a {@link ParameterProperty}.
	 * @return The {@link ParameterProperty} represented by the passed numeric
	 *         value.
	 * @throws NoSuchElementException
	 *             Thrown if the specified value is invalid.
	 */
	public static ParameterProperty valueOf(int value)
			throws NoSuchElementException {
		switch (value) {
		case 0:
			return IDENTIFIER;
		case 1:
			return DESCRIPTION;
		case 2:
			return VALUE;
		case 3:
			return MINIMUM;
		case 4:
			return MAXIMUM;
		case 5:
			return ACCESS;
		case 6:
			return FORMAT;
		case 7:
			return ENUMERATION;
		case 8:
			return FACTOR;
		case 9:
			return ISONLINE;
		case 10:
			return FORMULA;
		case 11:
			return STEP;
		case 12:
			return DEFAULT;
		case 13:
			return TYPE;
		case 14:
			return STREAMIDENTIFIER;
		case 15:
			return ENUMMAP;
		case 16:
			return STREAMDESCRIPTOR;
		case 17:
			return SCHEMAIDENTIFIERS;
		default:
			throw new NoSuchElementException("The value is invalid.");
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the \c ParameterProperty enumeration.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private ParameterProperty(int value) {
		this.value = value;
	}

	/**
	 * Compares \a value against the internal value of this \c
	 * ParameterProperty.
	 * 
	 * @param value
	 *            The value to compare.
	 * @return \e true, if both values are equal. Otherwise, this method return
	 *         \e false.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	/**
	 * Returns the numeric representation of this enumeration entry.
	 * 
	 * @return The numeric representation of this enumeration entry.
	 */
	public int value() {
		return value;
	}
}
