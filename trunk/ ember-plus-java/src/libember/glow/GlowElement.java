package libember.glow;

import libember.ber.Tag;

/**
 * Base class for all types listed in the {@link GlowType} enumeration.
 */
public class GlowElement extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowElement} class with the
	 * application tag is set to {@link GlowTags.ELEMENT_DEFAULT} (
	 * {@link libember.ber.Class.CONTEXT}, 0).
	 * 
	 * @param mode
	 *            The {@link InsertMode} to use for this container.
	 * @param type
	 *            The type this element represents.
	 * @throws NullPointerException
	 *             Thrown if any of the parameters is <i>null</i>.
	 */
	protected GlowElement(InsertMode mode, GlowType type)
			throws NullPointerException {
		super(mode, type);
	}

	/**
	 * Initializes a new instance of the {@link GlowElement} class.
	 * 
	 * @param mode
	 *            The {@link InsertMode} to use for this container.
	 * @param type
	 *            The type this element represents.
	 * @param tag
	 *            The applicaton tag to initialize this instance with.
	 * @throws NullPointerException
	 *             Thrown if any of the parameters is <i>null</i>.
	 */
	protected GlowElement(InsertMode mode, GlowType type, Tag tag)
			throws NullPointerException {
		super(mode, type, tag);
	}
}
