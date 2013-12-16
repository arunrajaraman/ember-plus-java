package libember.glow;

/**
 * This class contains the Dtd version this library supports.
 */
public final class GlowDtd {
	/**
	 * Contains the major version number.
	 */
	public static final byte MAJOR = 2;

	/**
	 * Contains the minor version number.
	 */
	public static final byte MINOR = 30;

	/**
	 * Gets the dtd version, where the higher byte contains the major version,
	 * while the lower one contains the minor version.
	 * 
	 * @return The dtd version.
	 */
	public static int version() {
		return (MAJOR << 8) | MINOR;
	}
}
