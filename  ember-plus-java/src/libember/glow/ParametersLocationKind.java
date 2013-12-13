package libember.glow;

/**
 * Scoped enumeration listing the symbolic names of the available location
 * kinds.
 */
public enum ParametersLocationKind {
	/**
	 * The location is not specified.
	 */
	NONE(0),

	/**
	 * The location is specified via an {@link Oid} which contains the path to
	 * the node containing the parameters.
	 */
	BASEPATH(1),

	/**
	 * The node containing the parameters is a direct subnode of the matrix
	 * node.
	 */
	INLINE(2);

	/**
	 * Gets the instance of {@link ParametersLocationKind} that internally uses
	 * the specified value.
	 * 
	 * @param value
	 *            The numeric representation of the {@link ParametersLocation}.
	 * @return The {@link ParametersLocationKind} that is represented by the
	 *         provided value.
	 */
	public static ParametersLocationKind valueOf(int value) {
		if (value == 1)
			return BASEPATH;
		else if (value == 2)
			return INLINE;
		else
			return NONE;
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link ParametersLocationKind} class.
	 * 
	 * @param value
	 *            The numeric representation of this instance.
	 */
	private ParametersLocationKind(int value) {
		this.value = value;
	}

	/**
	 * Compares the specified value against the numeric representation of this
	 * instance.
	 * 
	 * @param value
	 *            The value to compare against this instance.
	 * @return <i>true</i>, if the values are equal.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	/**
	 * Gets the numeric representation of this enumeration entry.
	 * 
	 * @return The numeric representation of this enumeration entry.
	 */
	public int value() {
		return this.value;
	}
}
