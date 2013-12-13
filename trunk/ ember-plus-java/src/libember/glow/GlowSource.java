package libember.glow;

import libember.ber.Tag;

/**
 * This class represents the source of a matrix within an Ember+ tree.
 */
public final class GlowSource extends GlowSignal {
	/**
	 * Initializes a new instance of the {@link GlowSource} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowSource(Tag tag) {
		super(GlowType.SOURCE, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowSource} class.
	 * 
	 * @param number
	 *            The number indetifying this source.
	 */
	public GlowSource(int number) {
		super(GlowType.SOURCE, number);
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
	public GlowSource(int number, Tag tag) throws NullPointerException {
		super(GlowType.SOURCE, tag, number);
	}
}
