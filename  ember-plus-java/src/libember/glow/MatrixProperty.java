package libember.glow;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the symbolic names of the available matrix
 * properties.
 */
public enum MatrixProperty {
	/**
	 * Identifies the matrix identifier.
	 */
	IDENTIFIER(0),

	/**
	 * Identifies the matrix description.
	 */
	DESCRIPTION(1),

	/**
	 * Identifies the matrix type.
	 */
	TYPE(2),

	/**
	 * Identifies the addressing mode of the matrix.
	 */
	ADDRESSINGMODE(3),

	/**
	 * Identifies a property that contains the number of targets.
	 */
	TARGETCOUNT(4),

	/**
	 * Identifies a property that contains the number of sources.
	 */
	SOURCECOUNT(5),

	/**
	 * Identifies a property that contains the maximum number of active
	 * crosspoints.
	 */
	MAXIMUMTOTALCONNECTS(6),

	/**
	 * Identifies a property that contains the maximum number of sources that
	 * may be connected to a single target at once.
	 */
	MAXIMUMCONNECTSPERTARGET(7),

	/**
	 * Identifies a property that specifies the location of the matrix
	 * parameters.
	 */
	PARAMETERSLOCATION(8),

	/**
	 * Identifies a property that specifies the parameter number that is used
	 * for gain parameters.
	 */
	GAINPARAMETERNUMBER(9),

	/**
	 * Identifies a property that contains a collection of label descriptions.
	 */
	LABELS(10);

	/**
	 * Gets the {@link MatrixProperty} representation of the specified numeric
	 * value.
	 * 
	 * @param value
	 *            The numeric value to look up
	 * @return The {@link MatrixProperty} representation of the passed numeric
	 *         value.
	 * @throws NoSuchElementException
	 *             Thrown if an invalid value is passed.
	 */
	public static MatrixProperty valueOf(int value)
			throws NoSuchElementException {
		switch (value) {
		case 0:
			return IDENTIFIER;
		case 1:
			return DESCRIPTION;
		case 2:
			return TYPE;
		case 3:
			return ADDRESSINGMODE;
		case 4:
			return TARGETCOUNT;
		case 5:
			return SOURCECOUNT;
		case 6:
			return MAXIMUMTOTALCONNECTS;
		case 7:
			return MAXIMUMCONNECTSPERTARGET;
		case 8:
			return PARAMETERSLOCATION;
		case 9:
			return GAINPARAMETERNUMBER;
		case 10:
			return LABELS;
		default:
			throw new NoSuchElementException("The value is invalid.");
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link MatrixProperty} class.
	 * 
	 * @param value
	 *            The numeric representation of this instance.
	 */
	private MatrixProperty(int value) {
		this.value = value;
	}

	/**
	 * Compares the numeric representation of this instance against the provided
	 * {@link value} for equality.
	 * 
	 * @param value
	 *            The value to compare with this instance.
	 * @return <i>true</i>, if the values are equal. Otherwise, this method
	 *         returns <i>false</i>.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	/**
	 * Gets the numeric representation of this {@link MatrixProperty}.
	 * 
	 * @return The numeric representation of this {@link MatrixProperty}.
	 */
	public int value() {
		return this.value;
	}
}
