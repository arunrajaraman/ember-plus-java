package libember.ber;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration containing the symbolic names for the various universal
 * type tag numbers. To indicate a {@link UniversalType}, the {@link Class} of
 * the {@link Tag} must be set to {@link Class.UNIVERSAL}.
 */
public enum UniversalType {
	/**
	 * The specified type is either invalid or unknown.
	 */
	INVALID(0),

	/**
	 * A boolean value, usually represented as byte, where 0xFF represents true
	 * and 0x00 false.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	BOOLEAN(1),

	/**
	 * An arbitrary integer value.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	INTEGER(2),

	/**
	 * An arbitrary string of bits (ones and zeroes). This type is not supported
	 * by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	BITSTRING(3),

	/**
	 * An arbitrary string of octets (8 bit values).
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	OCTETSTRING(4),

	/**
	 * Null.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	NULL(5),

	/**
	 * An object identifier, which is a sequence of integer components that
	 * identify an object such as an algorithm or attribute type. This type is
	 * not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	OBJECTIDENTIFIER(6),

	/**
	 * A data value descriptor. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	OBJECTDESCRIPTOR(7),

	/**
	 * A structure describing an externally referenced object. This type is not
	 * supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	EXTERNAL(8),

	/**
	 * A decimal value.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	REAL(9),

	/**
	 * An enumerated integral value. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	ENUMERATED(10),

	/**
	 * Embedded presentation data value. This type is not supported by this
	 * library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	EMBEDDEDPDV(11),

	/**
	 * An UTF8 encoded string.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	UTF8STRING(12),

	/**
	 * A collection of multibyte encoded integers which represent an numeric
	 * object identifier.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	RELATIVEOID(13),

	/**
	 * A container storing elements of one type.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	SEQUENCE(16),

	/**
	 * A container storing elements of any type and in any order.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	SET(17),

	/**
	 * A numeric string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	NUMERICSTRING(18),

	/**
	 * A printable string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	PRINTABLESTRING(19),

	/**
	 * A teletex string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	TELETEXSTRING(20),

	/**
	 * A videotex string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	VIDEOTEXSTRING(21),

	/**
	 * An IA5 string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	IA5STRING(22),

	/**
	 * The string representation of a UTC time. This type is not supported by
	 * this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	UTCTIME(23),

	/**
	 * A generalized time. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	GENERALIZEDTIME(24),

	/**
	 * A graphics string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	GRAPHICSTRING(25),

	/**
	 * A visible string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	VISIBLESTRING(26),

	/**
	 * A general string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	GENERALSTRING(27),

	/**
	 * A universal string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	UNIVERSALSTRING(28),

	/**
	 * An unspecified string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	UNSPECIFIEDSTRING(29),

	/**
	 * A bitmap string. This type is not supported by this library.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	BMPSTRING(30),

	/**
	 * Indicates the last valid type number.
	 * <p>
	 * See <a href =
	 * "http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf"
	 * >http://www.itu.int/ITU-T/studygroups/com17/languages/X.680-0207.pdf</a>
	 * for more information
	 * </p>
	 */
	LASTUNIVERSAL(31);

	/**
	 * The mask is used to assure that a provided integer value is within the
	 * valid boundaries.
	 */
	private final static int MASK = 31;

	/**
	 * Gets a {@link Tag} representing the specified {@link UniversalType}.
	 * 
	 * @param type
	 *            The {@link UniversalType} to return a {@link Tag} for.
	 * @return The {@link Tag} representing the provided {@link type}.
	 */
	public static Tag universalTag(UniversalType type) {
		return new Tag(Class.UNIVERSAL.value(), type.value());
	}

	/**
	 * Gets the {@link UniversalType} instance that represents the provided
	 * {@link value}. If the specified value does not represent a valid
	 * {@link UniversalType}, {@linkUniversalType.INVALID} is being returned.
	 * 
	 * @param value
	 *            The numeric type value to lookup.
	 * @return The {@link UniversalType} that represents the provided value.
	 */
	public static UniversalType valueOf(int value) {
		final int ordinal = value & MASK;

		switch (ordinal) {
		case 1:
			return BOOLEAN;
		case 2:
			return INTEGER;
		case 3:
			return BITSTRING;
		case 4:
			return OCTETSTRING;
		case 5:
			return NULL;
		case 6:
			return OBJECTIDENTIFIER;
		case 7:
			return OBJECTDESCRIPTOR;
		case 8:
			return EXTERNAL;
		case 9:
			return REAL;
		case 10:
			return ENUMERATED;
		case 11:
			return EMBEDDEDPDV;
		case 12:
			return UTF8STRING;
		case 13:
			return RELATIVEOID;
		case 16:
			return SEQUENCE;
		case 17:
			return SET;
		case 18:
			return NUMERICSTRING;
		case 19:
			return PRINTABLESTRING;
		case 20:
			return TELETEXSTRING;
		case 21:
			return VIDEOTEXSTRING;
		case 22:
			return IA5STRING;
		case 23:
			return UTCTIME;
		case 24:
			return GENERALIZEDTIME;
		case 25:
			return GRAPHICSTRING;
		case 26:
			return VISIBLESTRING;
		case 27:
			return GENERALSTRING;
		case 28:
			return UNIVERSALSTRING;
		case 29:
			return UNSPECIFIEDSTRING;
		case 30:
			return BMPSTRING;
		default:
			return INVALID;
		}
	}

	/**
	 * Gets the {@link UniversalType} that is represented by {@link type}. The
	 * type <b>must not</b> be an application defined type.
	 * 
	 * @param type
	 *            The {@link Type} to get the {@link UniversalType} from.
	 * @return The {@link UniversalType} that is represent by {@link type}.
	 * @throws NoSuchElementException
	 *             Thrown when {@link type} is marked as application specific.
	 */
	public static UniversalType valueOf(Type type)
			throws NoSuchElementException {
		if (type.isUniversalType())
			return valueOf(type.value());
		else
			throw new NoSuchElementException(
					"type must not be marked application specified.");
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link UniversalType} class.
	 * 
	 * @param value
	 *            The numeric value of the type.
	 */
	private UniversalType(int value) {
		this.value = value;
	}

	/**
	 * Compares the provided value against the internal representation of this
	 * instance.
	 * 
	 * @param value
	 *            The value to compare this instance against.
	 * @return <i>true</i>, if the values are equal. Otherwise, this method
	 *         returns <i>false</i>.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	/**
	 * Gets the numeric representation of this {@link Type}.
	 * 
	 * @return The numeric representation of this {@link Type}.
	 */
	public int value() {
		return this.value;
	}
}
