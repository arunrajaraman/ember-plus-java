package libember.glow;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration containing the symbolic names of the available matrix
 * types.
 */
public enum MatrixType {
	/**
	 * A target is connected to one source only, but a source may be used by
	 * several targets.
	 */
	ONE_TO_N(0),

	/**
	 * A target is connected to one source and a source can only be connected
	 * with a single target.
	 */
	ONE_TO_ONE(1),

	/**
	 * A target may be connected with several sources, and each source may also
	 * be connected to several targets.
	 */
	N_TO_N(2);

	/**
	 * Gets the {@link MatrixType} that is associated with the specified
	 * {@link value}.
	 * 
	 * @param value
	 *            The value whose {@link MatrixType} shall be returned.
	 * @return The {@link MatrixType} that is associated with {@link value}.
	 * @throws NoSuchElementException
	 *             Thrown if the specified value is invalid.
	 */
	public static MatrixType valueOf(int value) throws NoSuchElementException {
		switch (value) {
		case 0:
			return ONE_TO_N;
		case 1:
			return ONE_TO_ONE;
		case 2:
			return N_TO_N;
		default:
			throw new NoSuchElementException("The value is invalid.");
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link MatrixType} enumeration.
	 * 
	 * @param value
	 *            The numeric representation of the enumeration instance.
	 */
	private MatrixType(int value) {
		this.value = value;
	}

	/**
	 * Compares the provided {@link value} with the numeric representation of
	 * this instance for equality.
	 * 
	 * @param value
	 *            The value to compare this instance with.
	 * @return <i>true</i>, if both values are equal. Otherwise, this method
	 *         returns <i>false</i>.
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
		return this.value;
	}
}
