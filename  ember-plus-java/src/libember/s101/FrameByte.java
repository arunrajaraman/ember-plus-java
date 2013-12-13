package libember.s101;

/**
 * Scoped enumeration listing the different framing and control bytes that are
 * used in the <i>S101</i> encoding.
 */
public enum FrameByte {
	/**
	 * The byte a value that needs to be escaped needs to be xored with.
	 */
	XOR(0x20),

	/**
	 * The escape byte.
	 */
	CE(0xFD),

	/**
	 * Indicates the start of an S101 frame.
	 */
	BOF(0xFE),

	/**
	 * Indicates the end of an S101 frame.
	 */
	EOF(0xFF),

	/**
	 * Indicates an invalid byte value.
	 */
	INVALID(0xF8);

	private final int value;

	/**
	 * Initializes a new instance of the {@link FrameByte} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private FrameByte(int value) {
		this.value = value;
	}

	/**
	 * Gets the numeric representation of this instance.
	 * 
	 * @return The numeric representation of this instance.
	 */
	public int value() {
		return value;
	}
}
