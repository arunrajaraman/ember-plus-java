package libember.glow;

import java.util.NoSuchElementException;

/**
 * Enumeration listing all supported stream formats.
 */
public enum StreamFormat {
	UNSIGNEDINT8(0), /* 00000 00 0 */
	UNSIGNEDINT16BIGENDIAN(2), /* 00000 01 0 */
	UNSIGNEDINT16LITTLENDIAN(3), /* 00000 01 1 */
	UNSIGNEDINT32BIGENDIAN(4), /* 00000 10 0 */
	UNSIGNEDINT32LITTLEENDIAN(5), /* 00000 10 1 */
	UNSIGNEDINT64BIGENDIAN(6), /* 00000 11 0 */
	UNSIGNEDINT64LITTLEENDIAN(7), /* 00000 11 1 */

	SIGNEDINT8(8), /* 00001 00 0 */
	SIGNEDINT16BIGENDIAN(10), /* 00001 01 0 */
	SIGNEDINT16LITTLEENDIAN(11), /* 00001 01 1 */
	SIGNEDINT32BIGENDIAN(12), /* 00001 10 0 */
	SIGNEDINT32LITTLEENDIAN(13), /* 00001 10 1 */
	SIGNEDINT64BIGENDIAN(14), /* 00001 11 0 */
	SIGNEDINT64LITTLEENDIAN(15), /* 00001 11 1 */

	IEEEFLOAT32BIGENDIAN(20), /* 00010 10 0 */
	IEEEFLOAT32LITTLEENDIAN(21), /* 00010 10 1 */
	IEEEFLOAT64BIGENDIAN(22), /* 00010 11 0 */
	IEEEFLOAT64LITTLEENDIAN(23); /* 00010 11 1 */

	/**
	 * Gets the {@link StreamFormat} represented by the passed value.
	 * 
	 * @param value
	 *            The value to get the associated StreamFormat for.
	 * @return The {@link StreamFormat} represented by the passed value.
	 * @throws NoSuchElementException
	 *             Thrown if the specified value is invalid.
	 */
	public static StreamFormat valueOf(int value) throws NoSuchElementException {
		switch (value) {
		case 0:
			return UNSIGNEDINT8;
		case 2:
			return UNSIGNEDINT16BIGENDIAN;
		case 3:
			return UNSIGNEDINT16LITTLENDIAN;
		case 4:
			return UNSIGNEDINT32BIGENDIAN;
		case 5:
			return UNSIGNEDINT32LITTLEENDIAN;
		case 6:
			return UNSIGNEDINT64BIGENDIAN;
		case 7:
			return UNSIGNEDINT64LITTLEENDIAN;
		case 8:
			return SIGNEDINT8;
		case 10:
			return SIGNEDINT16BIGENDIAN;
		case 11:
			return SIGNEDINT16LITTLEENDIAN;
		case 12:
			return SIGNEDINT32BIGENDIAN;
		case 13:
			return SIGNEDINT32LITTLEENDIAN;
		case 14:
			return SIGNEDINT64BIGENDIAN;
		case 15:
			return SIGNEDINT64LITTLEENDIAN;
		case 20:
			return IEEEFLOAT32BIGENDIAN;
		case 21:
			return IEEEFLOAT32LITTLEENDIAN;
		case 22:
			return IEEEFLOAT64BIGENDIAN;
		case 23:
			return IEEEFLOAT64LITTLEENDIAN;
		default:
			throw new NoSuchElementException("The value is invalid.");
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link StreamFormat} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private StreamFormat(int value) {
		this.value = value;
	}

	/**
	 * Compares the {@link value} against the internal value of this
	 * {@link StreamFormat} instance.
	 * 
	 * @param value
	 *            The value to compare.
	 * @return <i>true</i>, if both values are equal. Otherwise, this method
	 *         returns <i>false</i>.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	@Override
	public String toString() {
		final int type = (value >> 3) & 3;
		final int bits = 8 << ((value >> 1) & 3);
		final boolean isLittleEndian = (value & 1) == 1;
		final String typeString = type == 0 ? "Unsigned"
				: (type == 1 ? "Signed" : "IeeeFloat");

		return String.format("%s%d%d", typeString, bits,
				isLittleEndian ? "LittleEndian" : "BigEndian");
	}

	/**
	 * Gets the numeric representation of this instance.
	 * 
	 * @return The numeric representation.
	 */
	public int value() {
		return value;
	}
}
