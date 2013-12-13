package libember.glow;

import libember.ber.Tag;

/**
 * This class represents the target of a matrix within the Ember+ tree.
 */
public final class GlowTarget extends GlowSignal {
	/**
	 * Initializes a new instance of the {@link GlowSource} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 * @param number
	 *            The number indetifying this source.
	 */
	GlowTarget(Tag tag) {
		super(GlowType.TARGET, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowSource} class.
	 * 
	 * @param number
	 *            The number indetifying this source.
	 */
	public GlowTarget(int number) {
		super(GlowType.TARGET, number);
	}

	/**
	 * Initializes a new instance of the {@link GlowSource} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 * @param number
	 *            The number indetifying this source.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	public GlowTarget(int number, Tag tag) throws NullPointerException {
		super(GlowType.TARGET, tag, number);
	}
}
