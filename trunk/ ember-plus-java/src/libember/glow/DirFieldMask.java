package libember.glow;

/**
 * Scoped enumeration listing the symbolic names of the available masks that may
 * be specified when send a command of type {@link CommandType.GETDIRECTORY}.
 */
public enum DirFieldMask {
	/**
	 * Return no properties.
	 */
	NONE(0x00),

	/**
	 * Request the identifiers of all children.
	 */
	IDENTIFIER(0x01),

	/**
	 * Request the descriptions of all children.
	 */
	DESCRIPTION(0x02),

	/**
	 * Request the complete tree.
	 */
	TREE(0x03),

	/**
	 * Request the values of all children (only valid for the paramenters of the
	 * node being queried).
	 */
	VALUE(0x04),

	/**
	 * Request the connections of a matrix.
	 */
	CONNECTIONS(0x05),
	
  /**
   * Request identifier, description and value.
   * Useful for synchronization.
   */
	TREE_AND_VALUE(0x07),

	/**
	 * Request all properties.
	 */
	ALL(-1);

	/**
	 * Gets the {@link DirFieldMask} representation of the specified numeric
	 * value.
	 * 
	 * @param value
	 *            The numeric value to look up
	 * @return The {@link DirFieldMask} that is associated with the specified
	 *         value. If an invalid value is provided,
	 *         {@link DirFieldMask.DEFAULT} is being returned.
	 */
	public static DirFieldMask valueOf(int value) {
		switch (value) {
		case 1:
			return IDENTIFIER;
		case 2:
			return DESCRIPTION;
		case 3:
			return TREE;
		case 4:
			return VALUE;
		case 5:
			return CONNECTIONS;
    case 7:
      return TREE_AND_VALUE;
		case -1:
    default:
			return ALL;
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link DirFieldMask} class.
	 * 
	 * @param value
	 *            The flags to initialize this instance with.
	 */
	private DirFieldMask(int value) {
		this.value = value;
	}

	/**
	 * Compares the {@link value} against the numeric representation of this
	 * instance.
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
		return value;
	}
}
