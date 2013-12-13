package libember.glow;

import libember.ber.Oid;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class is used to describe the location of a specific label type.
 */
public final class GlowLabel extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowLabel} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	GlowLabel(Tag tag) throws NullPointerException {
		super(InsertMode.SORTED, GlowType.LABEL, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowLabel} class.
	 * 
	 * @param basePath
	 *            The location within the gadget tree which contains the labels
	 *            this instance describes.
	 * @param description
	 *            The description string of the label type.
	 * @throws NullPointerException
	 *             Thrown if either {@link basePath} or {@link description} is
	 *             <i>null</i>}.
	 */
	public GlowLabel(Oid basePath, String description)
			throws NullPointerException {
		super(InsertMode.SORTED, GlowType.LABEL);

		Assert.AssertNotNull(basePath, "basePath");
		Assert.AssertNotNull(description, "description");

		this.insert(new Leaf(GlowTags.Label.BASEPATH, new Value(basePath)));
		this.insert(new Leaf(GlowTags.Label.DESCRIPTION, new Value(description)));
	}

	/**
	 * Initializes a new instance of the {@link GlowLabel} class.
	 * 
	 * @param basePath
	 *            The location within the gadget tree which contains the labels
	 *            this instance describes.
	 * @param description
	 *            The description string of the label type.
	 * @param tag
	 *            The application tag of this instance.
	 * @throws NullPointerException
	 *             Thrown if either {@link basePath}, {@link description} or
	 *             {@link tag} is <i>null</i>}.
	 */
	public GlowLabel(Oid basePath, String description, Tag tag)
			throws NullPointerException {
		super(InsertMode.SORTED, GlowType.LABEL, tag);

		Assert.AssertNotNull(basePath, "basePath");
		Assert.AssertNotNull(description, "description");

		this.insert(new Leaf(GlowTags.Label.BASEPATH, new Value(basePath)));
		this.insert(new Leaf(GlowTags.Label.DESCRIPTION, new Value(description)));
	}

	/**
	 * Gets the base path which points to the location of the labels.
	 * 
	 * @return An {@link Oid} containing the path to the location of the labels
	 *         or <i>null</i>, if the property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Oid basePath() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.Label.BASEPATH);

		if (leaf != null) {
			return leaf.value().toOid();
		} else {
			return null;
		}
	}

	/**
	 * Gets the category name of this label type.
	 * 
	 * @return The category name of this label type.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String description() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.Label.DESCRIPTION);

		if (leaf != null) {
			return leaf.value().toUTF8String();
		} else {
			return null;
		}
	}
}
