package libember.glow;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the symbolic names of the available matrix
 * addressing modes.
 */
public enum MatrixAddressingMode {
	/**
	 * The signals of a matrix are being addressing from 0 to N, without any
	 * gaps.
	 */
	LINEAR(0),

	/**
	 * The signals of a matrix are not provided in any order.
	 */
	NONLINEAR(1);

	/**
	 * Gets the {@link MatrixAddressingMode} representation of the specified
	 * numeric value.
	 * 
	 * @param value
	 *            The numeric value to look up
	 * @return The {@link MatrixAddressingMode} representation of the passed
	 *         numeric value.
	 * @throws NoSuchElementException
	 *             Thrown if an invalid value is passed.
	 */
	public static MatrixAddressingMode valueOf(int value)
			throws NoSuchElementException {
		if (value == 0)
			return LINEAR;
		else if (value == 1)
			return NONLINEAR;
		else
			throw new NoSuchElementException("The value is invalid.");
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link MatrixAddressingMode} class.
	 * 
	 * @param value
	 *            The numeric representation of the addressing mode.
	 */
	private MatrixAddressingMode(int value) {
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
	 * Gets the numeric representation of the {@link MatrixAddressingMode}.
	 * 
	 * @return
	 */
	public int value() {
		return this.value;
	}
}
