package libember.ber;

import libember.util.Assert;

/**
 * Represents an encoded length, which is used when encoding a container or a
 * value. The {@link Length} stores the encoded length of the data to follow. To
 * avoid ambiguous calls to the {@link Encoding.encode} or
 * {@link Encoding.decode} methods, a length value is encapsulated into a
 * separate class.
 */
public final class Length implements Comparable<Length> {
	private final int value;

	/**
	 * This constant value represents an indefinite length, which may be used
	 * for containers of unknown length.
	 */
	public static final Length INDEFINITE = new Length(-1);

	/**
	 * Initializes a new instance of the {@link Length} class.
	 * 
	 * @param value
	 *            The length value.
	 */
	public Length(int value) {
		this.value = value;
	}

	public int compareTo(Length o) {
		Assert.AssertNotNull(o, "o");
		return this.value - o.value;
	}

	/**
	 * Compares this instance with the one passed for equality.
	 * 
	 * @param other
	 *            The second {@link Length} instance to compare.
	 * @return <code>true</code>, if both lengths are equal. Otherwise, this
	 *         method returns <code>false</code>.
	 * @throws NullPointerException
	 *             Thrown if {@link other} is <i>null</i>.
	 */
	public boolean equals(Length other) throws NullPointerException {
		Assert.AssertNotNull(other, "other");

		return this.value == other.value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Length) {
			return equals((Length) obj);
		}
		return false;
	}

	/**
	 * Gets a value indicating whether this instance represents the indefinite
	 * length.
	 * 
	 * @return <code>true</code>, when the Length is indefinite, otherwise
	 *         <code>false</code>.
	 */
	public boolean isIndefinite() {
		return this.value == INDEFINITE.value();
	}

	@Override
	public String toString() {
		return String.format("Length (Value = %d)", this.value);
	}

	/**
	 * Gets the value of this instance.
	 * 
	 * @return The encoded length of an object.
	 */
	public int value() {
		return this.value;
	}
}
