package libember.glow;

/**
 * Scoped enumeration listing the symbolic names of the available command types.
 * A {@link CommandType} is a property of the {@link GlowCommand} class.
 */
public enum CommandType {
	/**
	 * The command is unknown.
	 */
	UNKNOWN(0),

	/**
	 * Requests the children of a {@link GlowNode}, the properties of a
	 * {@link GlowParameter}, or the content of a {@link GlowMatrix}. By
	 * applying this command on a node, the consumer implicitly subscribes to
	 * that node.
	 */
	GETDIRECTORY(32),

	/**
	 * Subscribes to a {@link GlowNode} or a {@link GlowParameter}. A consumer
	 * also implicitly subscribes to a node by requesting its directory (see
	 * {@link CommandType.GETDIRECTORY}.
	 */
	SUBSCRIBE(30),

	/**
	 * Unsubscribes from a {@link GlowNode}, {@link GlowParameter} or a
	 * {@link GlowMatrix}.
	 */
	UNSUBSCRIBE(31),

	/**
	 * Invokes a {@link GlowFunction}.
	 */
	INVOKE(33);

	/**
	 * Gets the {@link CommandType} associated to the passed integer value.
	 * 
	 * @param value
	 *            The value to get associated command for.
	 * @return The {@link CommandType} represented by the passed value. If the
	 *         specified value is invalid, this method returns
	 *         {@link CommandType.UNKNOWN}.
	 */
	public static CommandType valueOf(int value) {
		switch (value) {
		case 30:
			return SUBSCRIBE;
		case 31:
			return UNSUBSCRIBE;
		case 32:
			return GETDIRECTORY;
		case 33:
			return INVOKE;
		default:
			return UNKNOWN;
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link CommandType} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private CommandType(int value) {
		this.value = value;
	}

	/**
	 * Compares the {@link value} against the internal value of this
	 * {@link CommandType}.
	 * 
	 * @param value
	 *            The value to compare.
	 * @return <i>value</i>, if both values are equal. Otherwise, this method
	 *         returns <i>false</i>.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	@Override
	public String toString() {
		if (value == SUBSCRIBE.value()) {
			return "Subscribe";
		} else if (value == UNSUBSCRIBE.value()) {
			return "Unsubscribe";
		} else if (value == GETDIRECTORY.value()) {
			return "GetDirectory";
		} else {
			return "Unknown";
		}
	}

	/**
	 * Gets the numeric representation of this {@link CommandType}.
	 * 
	 * @return The numeric representation of this {@link CommandType}.
	 */
	public int value() {
		return value;
	}
}
