package libember.glow;

import libember.ber.Oid;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class represents a function within an Ember+ tree. A
 * {@link GlowQualifiedFunction} is reported at root level and uses an
 * {@link Oid} that specifies the position within the tree.
 */
public final class GlowQualifiedFunction extends GlowFunctionBase {
	/**
	 * Initializes a new instance of the {@link GlowQualifiedFunction} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowQualifiedFunction(Tag tag) {
		super(GlowType.QUALIFIEDFUNCTION, tag,
				GlowTags.QualifiedFunction.CONTENTS,
				GlowTags.QualifiedFunction.CHILDREN);
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedFunction} class.
	 * 
	 * @param parent
	 *            A {@link GlowRootElementCollection} to append this instance
	 *            to.
	 * @param path
	 *            The {@link Oid} specifying the location of the function.
	 * @throws NullPointerException
	 *             Thrown if {@link path} is <i>null</i>.
	 */
	public GlowQualifiedFunction(GlowRootElementCollection parent, Oid path)
			throws NullPointerException {
		super(GlowType.QUALIFIEDFUNCTION, GlowTags.ELEMENT_DEFAULT,
				GlowTags.QualifiedFunction.CONTENTS,
				GlowTags.QualifiedFunction.CHILDREN);

		Assert.AssertNotNull(path, "path");

		this.insert(new Leaf(GlowTags.QualifiedFunction.PATH, new Value(path)));

		if (parent != null) {
			parent.insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedFunction} class.
	 * 
	 * @param path
	 *            The {@link Oid} specifying the location of the function.
	 * @throws NullPointerException
	 *             Thrown if {@link path} is <i>null</i>.
	 */
	public GlowQualifiedFunction(Oid path) throws NullPointerException {
		super(GlowType.QUALIFIEDFUNCTION, GlowTags.ELEMENT_DEFAULT,
				GlowTags.QualifiedFunction.CONTENTS,
				GlowTags.QualifiedFunction.CHILDREN);

		Assert.AssertNotNull(path, "path");

		this.insert(new Leaf(GlowTags.QualifiedFunction.PATH, new Value(path)));
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedFunction} class.
	 * 
	 * @param path
	 *            The {@link Oid} specifying the location of the function.
	 * @param tag
	 *            The application tag of this instance.
	 * @throws NullPointerException
	 *             Thrown if {@link path} or {@link tag} is <i>null</i>.
	 */
	public GlowQualifiedFunction(Oid path, Tag tag) throws NullPointerException {
		super(GlowType.QUALIFIEDFUNCTION, tag,
				GlowTags.QualifiedFunction.CONTENTS,
				GlowTags.QualifiedFunction.CHILDREN);

		Assert.AssertNotNull(path, "path");

		this.insert(new Leaf(GlowTags.QualifiedFunction.PATH, new Value(path)));
	}

	/**
	 * Gets the {@link Oid} specifying the location of this function within the
	 * tree.
	 * 
	 * @return The {@link Oid} or <i>null</i>, if the property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Oid path() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.QualifiedFunction.PATH);

		if (leaf != null) {
			return leaf.value().toOid();
		} else {
			return null;
		}
	}
}
