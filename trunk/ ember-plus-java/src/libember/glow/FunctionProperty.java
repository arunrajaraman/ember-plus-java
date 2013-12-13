package libember.glow;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the symbolic names of the properties a
 * {@link GlowFunction} has.
 */
public enum FunctionProperty {
	/**
	 * The identifier of a funtion.
	 */
	IDENTFIER(0),

	/**
	 * The description of a function.
	 */
	DESCRIPTION(1),

	/**
	 * The descripiton of the arguments of a function.
	 */
	ARGUMENTS(2),

	/**
	 * The description of the return types of a function.
	 */
	RESULT(3);

	/**
	 * Gets the {@link FunctionProperty} that is associated with the specified
	 * value.
	 * 
	 * @param value
	 *            The numeric representation of the {@link FunctionProperty} to
	 *            return.
	 * @return The {@link FunctionProperty} that is associated with the
	 *         specified value.
	 * @throws NoSuchElementException
	 *             Thrown if the specified value is invalid.
	 */
	public static FunctionProperty valueOf(int value)
			throws NoSuchElementException {
		switch (value) {
		case 0:
			return IDENTFIER;
		case 1:
			return DESCRIPTION;
		case 2:
			return ARGUMENTS;
		case 3:
			return RESULT;
		}

		throw new NoSuchElementException("The specified value is invalid.");
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link FunctionProperty} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private FunctionProperty(int value) {
		this.value = value;
	}

	/**
	 * Compares the specified {@link value} with the numeric representation of
	 * this instance for equality.
	 * 
	 * @param value
	 *            The value to compare with this instance.
	 * @return <i>true</i>, if both values a equal.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	/**
	 * Gets the numeric representation of this instance.
	 * 
	 * @return Thenumeric representation of this instance.
	 */
	public int value() {
		return this.value;
	}
}
