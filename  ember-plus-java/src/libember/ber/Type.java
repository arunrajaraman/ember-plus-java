package libember.ber;

import libember.util.Assert;

/**
 * Represents the type of a ber object. An instance of this class is either
 * created from a well-known {@link UniversalType} or from a {@link Tag}, which
 * represents an application defined type.
 */
public final class Type implements Comparable<Type> {
	private final long value;

	/**
	 * When the type represents an application defined type, the type value will
	 * be marked with this constant.
	 */
	public static final long APPLICATION_FLAG = 0x80000000;

	/**
	 * Creates a new instance of the {@link Type} class from a {@link Tag}.
	 * 
	 * @param tag
	 *            The {@link Tag} to create the {@link Type} instance from.
	 * @return A new instance of {@link Type} which contains the type
	 *         information of the provided {@link tag}.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	public static Type fromTag(Tag tag) throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");

		final boolean isApplicationType = tag.berClass() != Class.UNIVERSAL;

		return new Type(tag.number(), isApplicationType);
	}

	/**
	 * Tests whether the type passed to this method is application defined.
	 * 
	 * @param type
	 *            The {@link Type} instance to evaluate.
	 * @return <i>true</i>, if the type is application defined, otherwise
	 *         <i>false</i>.
	 */
	public static boolean isApplicationDefined(Type type) {
		final long mask = type.value & APPLICATION_FLAG;

		return mask == APPLICATION_FLAG;
	}

	/**
	 * Initializes a new instance of the {@link Type} class.
	 * 
	 * @param type
	 *            The numeric representation of this {@link Type} instance.
	 * @param isApplicationType
	 *            Indicates whether this type is application defined or not.
	 */
	private Type(int type, boolean isApplicationType) {
		this.value = (isApplicationType ? APPLICATION_FLAG : 0) | type;
	}

	/**
	 * Initializes a new instance of the {@link Type} class which represents a
	 * {@link UniversalType}.
	 * 
	 * @param type
	 *            The {@link UniversalType} to represent.
	 */
	public Type(UniversalType type) {
		this.value = type.value();
	}

	public int compareTo(Type o) {
		return (int) (value - o.value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Type) {
			return equals((Type) obj);
		}

		return false;
	}

	/**
	 * Compares two instances of {@link Type} for equality.
	 * 
	 * @param other
	 *            The second instance to compare.
	 * @return <i>true</i> if the Types are equal, otherwise <i>false</i>.
	 */
	public boolean equals(Type other) {
		return this.value == other.value;
	}

	/**
	 * Gets a value indicating whether this {@link Type} represents an
	 * application defined type.
	 * 
	 * @return <i>true</i>, if this type is application defined.
	 */
	public boolean isApplicationDefined() {
		return isApplicationDefined(this);
	}

	/**
	 * Gets a value indicating whether this \c Type represents a universal type.
	 * 
	 * @return \e true, if this instance contains a value that represents a
	 *         universal type.
	 */
	public boolean isUniversalType() {
		return isApplicationDefined(this) == false;
	}

	@Override
	public String toString() {
		return String.format("Type (Value = %d, ApplicationType = %s)", value,
				(isApplicationDefined() ? "true" : "false"));
	}

	/**
	 * Gets the numeric representation of this {@link Type}. Please note that
	 * the application type flag is being masked out.
	 * 
	 * @return The numeric type value.
	 */
	public int value() {
		return ((int) (value & ~APPLICATION_FLAG));
	}
}
