package libember.glow;

import libember.ber.Oid;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class represents a qualified parameter.
 */
public final class GlowQualifiedParameter extends GlowParameterBase {
	/**
	 * Initializes a new instance of the {@link GlowQualifiedParameter} class.
	 * 
	 * @param tag
	 *            The application tag of this parameter.
	 */
	GlowQualifiedParameter(Tag tag) {
		super(GlowType.QUALIFIEDPARAMETER, tag,
				GlowTags.QualifiedParameter.CONTENTS,
				GlowTags.QualifiedParameter.CHILDREN);
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedParameter} class.
	 * 
	 * @param parent
	 *            A {@link GlowRootElementCollection} to append this parameter
	 *            to.
	 * @param path
	 *            The {@link Oid} specifying the location of this parameter.
	 * @throws NullPointerException
	 *             Thrown if {@link path} is <i>null</i>.
	 */
	public GlowQualifiedParameter(GlowRootElementCollection parent, Oid path) {
		this(path);

		if (parent != null) {
			parent.insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedParameter} class.
	 * 
	 * @param path
	 *            The {@link Oid} specifying the location of this parameter.
	 * @throws NullPointerException
	 *             Thrown if {@link path} is <i>null</i>.
	 */
	public GlowQualifiedParameter(Oid path) throws NullPointerException {
		this(path, GlowTags.ELEMENT_DEFAULT);
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedParameter} class.
	 * 
	 * @param path
	 *            The {@link Oid} specifying the location of this parameter.
	 * @param tag
	 *            The application tag of this parameter.
	 * @throws NullPointerException
	 *             Thrown if either {@link path} or {@link tag} is <i>null</i>.
	 */
	public GlowQualifiedParameter(Oid path, Tag tag)
			throws NullPointerException {
		this(tag);

		Assert.AssertNotNull(path, "path");

		insert(new Leaf(GlowTags.QualifiedParameter.PATH, new Value(path)));
	}

	/**
	 * Gets the {@link Oid} specifying the location of this parameter.
	 * 
	 * @return The {@link Oid} specifying the location of this parameter or
	 *         <i>null</i>, if the property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Oid path() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.QualifiedParameter.PATH);

		if (leaf != null) {
			return leaf.value().toOid();
		} else {
			return null;
		}
	}
}
