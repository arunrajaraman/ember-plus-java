package libember.glow;

import libember.ber.Tag;

/**
 * This class represents the root node of a gadget tree.
 */
public final class GlowRootElementCollection extends GlowContainer {
	/**
	 * Creates a new instance of the {@link GlowRootElementCollection} class.
	 * 
	 * @return A new new instance of the {@link GlowRootElementCollection}
	 *         class.
	 */
	public static GlowRootElementCollection create() {
		return new GlowRootElementCollection(GlowTags.ROOT);
	}

	/**
	 * Initializes a new instance of the {@link GlowRootElementCollection}
	 * class.
	 * 
	 * @param tag
	 *            The application tag for this instance.
	 */
	GlowRootElementCollection(Tag tag) {
		super(InsertMode.DEFAULT, GlowType.ROOTELEMENTCOLLECTION, tag);
	}
}
