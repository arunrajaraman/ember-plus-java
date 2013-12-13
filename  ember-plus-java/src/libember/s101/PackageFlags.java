package libember.s101;

import java.util.Vector;

/**
 * This class represents the available flags of a single message. They indicate
 * whether the packet is fragmented or not.
 */
public final class PackageFlags {
	/**
	 * Indicates that the current packet is the first part of a message. If the
	 * {@link PackageFlags.LAST_PACKAGE} flag is set as well, the message is a
	 * single packet message.
	 */
	public static final int FIRST_PACKAGE = 0x80;

	/**
	 * Indicates that the current packet is the last part of a message.
	 */
	public static final int LAST_PACKAGE = 0x40;

	/**
	 * Indicates that the current package is empty. This scenario occurs when
	 * all data has already been transmitted in one or more packets but the
	 * {@link PackageFlags.LAST_PACKAGE} flag has not yet been sent.
	 */
	public static final int EMPTY_PACKAGE = 0x20;

	private final int flags;

	/**
	 * Initializes a new instance of the {@link PackageFlags} class.
	 * 
	 * @param flags
	 *            The flags to initialize this instance with.
	 */
	public PackageFlags(int flags) {
		this.flags = flags;
	}

	/**
	 * Tests if a flag is set.
	 * 
	 * @param flag
	 *            The flag to test.
	 * @return <i>true</i> if the requested flag is set, otherwise <i>false</i>.
	 */
	public boolean isSet(int flag) {
		return (flags & flag) == flag;
	}

	@Override
	public String toString() {
		final Vector<String> items = new Vector<String>();

		if (isSet(PackageFlags.EMPTY_PACKAGE))
			items.add("EMPTY");

		if (isSet(PackageFlags.FIRST_PACKAGE))
			items.add("FIRST");

		if (isSet(PackageFlags.LAST_PACKAGE))
			items.add("LAST");

		if (items.isEmpty())
			return "NONE";
		else
			return libember.util.StringUtil.join(" | ", items);
	}

	/**
	 * Gets the flags set in this instance.
	 * 
	 * @return The flags set in this instance.
	 */
	public int value() {
		return flags;
	}
}
