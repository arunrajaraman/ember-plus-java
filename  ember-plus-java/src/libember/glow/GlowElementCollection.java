package libember.glow;

import libember.ber.Tag;

/**
 * An Element Collection is usually used as root element and also used by nodes
 * and parameters to store children and commands.
 */
public class GlowElementCollection extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowElementCollection} class.
	 * 
	 * @note The application tag is set {@link GlowTags.ELEMENT_DEFAULT}.
	 */
	public GlowElementCollection() {
		super(InsertMode.DEFAULT, GlowType.ELEMENTCOLLECTION);
	}

	/**
	 * Initializes a new instance of the {@link GlowElementCollection} class.
	 * 
	 * @param tag
	 *            The application tag to set.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	public GlowElementCollection(Tag tag) throws NullPointerException {
		super(InsertMode.DEFAULT, GlowType.ELEMENTCOLLECTION, tag);
	}
}
