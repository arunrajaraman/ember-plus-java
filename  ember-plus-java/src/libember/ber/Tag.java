package libember.ber;

/**
 * A {@link Tag} consists of a class and a number. Additionally, it indicates
 * whether the data followed by the tag is a container.
 */
public final class Tag implements Comparable<Tag> {
	private final int preamble;
	private final int number;

	/**
	 * The container flag marks the preamble of a tag as container.
	 */
	private final static int CONTAINER_FLAG = 0x20;

	/**
	 * Gets the default {@link Tag}, where preamble and number are set to 0.
	 */
	public static final Tag ZERO = new Tag();

	/**
	 * Initializes a new instance of the {@link Tag} class with the preamble and
	 * the number set to 0.
	 */
	private Tag() {
		this.preamble = Class.UNIVERSAL.value();
		this.number = 0;
	}

	/**
	 * Initializes a new instance of the {@link Tag} class.
	 * 
	 * @param preamble
	 *            The preamble value.
	 * @param number
	 *            The tag number.
	 */
	Tag(int preamble, int number) {
		this.preamble = preamble;
		this.number = number;
	}

	/**
	 * Initializes a new instance of the {@link Tag} class which represents a
	 * primitive value.
	 * 
	 * @param preamble
	 *            The {@link Class} of this {@link Tag}.
	 * @param number
	 *            The number of this {@link Tag}.
	 */
	public Tag(Class preamble, int number) {
		this.preamble = preamble.value();
		this.number = number;
	}

	/**
	 * Initializes a new instance of the {@link Tag} class.
	 * 
	 * @param preamble
	 *            The {@link Class} of this {@link Tag}.
	 * @param number
	 *            The number of this {@link Tag}.
	 * @param isContainer
	 *            Container flag. If set to <i>true</i>, the preamble will be
	 *            marked with the <code>CONTAINER_FLAG</code>.
	 */
	public Tag(Class preamble, int number, boolean isContainer) {
		this.preamble = preamble.value()
				| (isContainer ? CONTAINER_FLAG : 0x00);
		this.number = number;
	}

	/**
	 * Gets the {@link Class} type of this {@link Tag}, which is stored in the
	 * preamble.
	 * 
	 * @return The {@link Class} of this tag.
	 */
	public Class berClass() {
		return Class.valueOf(preamble);
	}

	public int compareTo(Tag o) {
		final int result = preamble - o.preamble;
		return result != 0 ? result : (number & 0xFFFFFFFF)
				- (o.number & 0xFFFFFFFF);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tag) {
			return equals((Tag) obj);
		}
		return false;
	}

	/**
	 * Compares two instances of type {@link Tag} for equality. A {@link Tag} is
	 * considered equal if both, the number and the preamble are equal.
	 * 
	 * @param other
	 *            The second {@link Tag} instance to compare with the callee.
	 * @return <code>true</code> if both instances are equal, otherwise
	 *         <code>false</code>.
	 */
	public boolean equals(Tag other) {
		return number == other.number && preamble == other.preamble;
	}

	@Override
	public int hashCode() {
		return (preamble << 24) ^ number;
	}

	/**
	 * Gets a value indicating whether this {@link Tag} is a container.
	 * 
	 * @return <code>true</code> if the container flag is set, otherwise
	 *         <code>false</code>.
	 */
	public boolean isContainer() {
		return (preamble & CONTAINER_FLAG) == CONTAINER_FLAG;
	}

	/**
	 * Gets a value indicating whether this {@link Tag} represents the zero tag.
	 * 
	 * @return <code>true</code>, if this instance equals the zero tag.
	 *         Otherwise, this method returns <code>false</code>.
	 */
	public boolean isZero() {
		return preamble == 0 && number == 0;
	}

	/**
	 * Gets the number of this {@link Tag} instance.
	 * 
	 * @return The number of this {@link Tag} instance.
	 */
	public int number() {
		return number;
	}

	/**
	 * Gets the preamble of this {@link Tag}. The
	 * <code>preamble<code> is a byte containing both the {@link Class}
	 * information and the flag indicating whether this {@link Tag} represents a container.
	 * 
	 * @return The preamble of this instance.
	 */
	public int preamble() {
		return preamble;
	}

	/**
	 * Returns a new {@link Tag} with the container flag set, but the same class
	 * and number.
	 * 
	 * @return A copy of this instance, but with the container flag set.
	 */
	public Tag toContainer() {
		return new Tag(preamble | CONTAINER_FLAG, number);
	}

	/**
	 * Returns a new {@link Tag} with the container flag erased, but the same
	 * class and number.
	 * 
	 * @return A copy of this instance, but with the container flag erased.
	 */
	public Tag toPrimitive() {
		return new Tag(preamble & ~CONTAINER_FLAG, number);
	}

	@Override
	public String toString() {
		return String.format("Tag (Class = %s, Number = %d, Container = %s)",
				getClass().toString(), number(), (isContainer() ? "true"
						: "false"));
	}
}
