package libember.glow;

/**
 * Scoped enumeration listing the symbolic names of the available access types.
 * The {@link Access} determines whether a {@link GlowParameter} can be read,
 * written, both, or is not accessible at all.
 */
public enum Access {
	/**
	 * No access at all.
	 */
	NONE(0),

	/**
	 * The parameter can be read.
	 */
	READ(1),

	/**
	 * The parameter can be changed by a control system.
	 */
	WRITE(2),

	/**
	 * The parameter supports both, read and write.
	 */
	READ_WRITE(3);

	/**
	 * Gets the {@link Access} representation of the specified numeric value.
	 * 
	 * @param value
	 *            The numeric value to look up.
	 * @return The instance of {@link Access} that is associated with the
	 *         provided value. If an invalid value is specified, this method
	 *         returns {@link Access.NONE}.
	 */
	public static Access valueOf(int value) {
		final int c = value & 3;

		if (c == 1)
			return READ;
		else if (c == 2)
			return WRITE;
		else if (c == 3)
			return READ_WRITE;

		return NONE;
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link Access} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private Access(int value) {
		this.value = value;
	}

	/**
	 * Compares {@link value} against the internal value of this instance.
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
	 * Gets the integer representation of this instance.
	 * 
	 * @return The integer representation of this instance.
	 */
	public int value() {
		return this.value;
	}
}
