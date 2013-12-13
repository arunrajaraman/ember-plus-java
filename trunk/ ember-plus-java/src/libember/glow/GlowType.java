package libember.glow;

import libember.ber.Class;
import libember.ber.Tag;

/**
 * Enumeration listing the symbolic names of all glow types supported.
 */
public enum GlowType {
	/**
	 * Represents an unknown type.
	 */
	UNKNOWN(0),

	/**
	 * Used by the {@link GlowParameter} class.
	 */
	PARAMETER(1),

	/**
	 * Used by the {@link GlowCommand} class.
	 */
	COMMAND(2),

	/**
	 * Used by the {@link GlowNode} class.
	 */
	NODE(3),

	/**
	 * Used by the {@link GlowElementCollection} class.
	 */
	ELEMENTCOLLECTION(4),

	/**
	 * Used by the {@link GlowStreamEntry} class.
	 */
	STREAMENTRY(5),

	/**
	 * Used by the {@link GlowStreamCollection} class.
	 */
	STREAMCOLLECTION(6),

	/**
	 * Used by the {@link GlowStringIntegerPair} class.
	 */
	STRINGINTEGERPAIR(7),

	/**
	 * Used by the {@link GlowStringIntegerCollection} class.
	 */
	STRINGINTEGERCOLLECTION(8),

	/**
	 * Used by the {@link GlowQualifiedParameter} class.
	 */
	QUALIFIEDPARAMETER(9),

	/**
	 * Used by the {@link GlowQualifiedNode} class.
	 */
	QUALIFIEDNODE(10),

	/**
	 * Used by the {@link GlowRootElementCollection} class.
	 */
	ROOTELEMENTCOLLECTION(11),

	/**
	 * Used by the {@link GlowStreamDescriptor} class.
	 */
	STREAMDESCRIPTOR(12),

	/**
	 * Used by the {@link GlowMatrix} class.
	 */
	MATRIX(13),

	/**
	 * Used by the {@link GlowTarget} class.
	 */
	TARGET(14),

	/**
	 * Used by the {@link GlowSource} class.
	 */
	SOURCE(15),

	/**
	 * Used by the {@link GlowConnection} class.
	 */
	CONNECTION(16),

	/**
	 * Used by the {@link GlowQualifiedMatrix} class.
	 */
	QUALIFIEDMATRIX(17),

	/**
	 * Used by the {@link GlowLabel} class.
	 */
	LABEL(18),

	/**
	 * Used by the {@link GlowFunction} class.
	 */
	FUNCTION(19),

	/**
	 * Used by the {@link GlowQualifiedFunction} class.
	 */
	QUALIFIEDFUNCTION(20),

	/**
	 * Used by the {@link GlowTupleItemDescription} class.
	 */
	TUPLEITEMDESCRIPTION(21),

	/**
	 * Used by the {@link GlowInvocation} class.
	 */
	INVOCATION(22),

	/**
	 * Used by the {@link GlowInvocationResult} class.
	 */
	INVOCATIONRESULT(23);

	/**
	 * Creates a {@link Tag} which is marked as application specific type and
	 * uses the numeric representation of the {@link type} as tag number.
	 * 
	 * @param type
	 *            The {@link} to get the type tag for.
	 * @return A {@link Tag} that represents the specified {@link GlowType}.
	 */
	public static Tag typeTagOf(GlowType type) {
		return new Tag(Class.APPLICATION, type.value());
	}

	/**
	 * Gets the {@link GlowType} that is represented by the specified
	 * {@link value}.
	 * 
	 * @param value
	 *            The numeric representation of the enumeration to return.
	 * @return The {@link GlowType} that is represented by the provided value.
	 */
	public static GlowType valueOf(int value) {
		switch (value) {
		case 1:
			return PARAMETER;
		case 2:
			return COMMAND;
		case 3:
			return NODE;
		case 4:
			return ELEMENTCOLLECTION;
		case 5:
			return STREAMENTRY;
		case 6:
			return STREAMCOLLECTION;
		case 7:
			return STRINGINTEGERPAIR;
		case 8:
			return STRINGINTEGERCOLLECTION;
		case 9:
			return QUALIFIEDPARAMETER;
		case 10:
			return QUALIFIEDNODE;
		case 11:
			return ROOTELEMENTCOLLECTION;
		case 12:
			return STREAMDESCRIPTOR;
		case 13:
			return MATRIX;
		case 14:
			return TARGET;
		case 15:
			return SOURCE;
		case 16:
			return CONNECTION;
		case 17:
			return QUALIFIEDMATRIX;
		case 18:
			return LABEL;
		case 19:
			return FUNCTION;
		case 20:
			return QUALIFIEDFUNCTION;
		case 21:
			return TUPLEITEMDESCRIPTION;
		case 22:
			return INVOCATION;
		case 23:
			return INVOCATIONRESULT;
		default:
			return UNKNOWN;
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link GlowType} class.
	 * 
	 * @param value
	 *            The numeric representation of this enumeration entry.
	 */
	private GlowType(int value) {
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
	 * Gets the numeric representation of the current type.
	 * 
	 * @return The numeric representation of the current type.
	 */
	public int value() {
		return value;
	}
}
