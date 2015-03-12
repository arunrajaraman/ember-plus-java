package libember.glow;

import libember.ber.Oid;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class represents a matrix within the Ember+ tree.
 */
public final class GlowQualifiedMatrix extends GlowMatrixBase {
	/**
	 * Initializes a new instance of the {@link GlowQualifiedMatrix} class.
	 * 
	 * @param parent
	 *            The {@link GlowRootElementCollection} to append this node to.
	 * @param path
	 *            An {@link Oid} specifying the location of this matrix within
	 *            the tree.
	 * @throws NullPointerException
	 *             Thrown if {@link path} or {@link tag} is <i>null</i>.
	 */
	public GlowQualifiedMatrix(GlowRootElementCollection parent, Oid path) {
		super(GlowType.QUALIFIEDMATRIX, GlowTags.ELEMENT_DEFAULT,
				GlowTags.QualifiedMatrix.CONTENTS,
				GlowTags.QualifiedMatrix.CHILDREN,
				GlowTags.QualifiedMatrix.TARGETS,
				GlowTags.QualifiedMatrix.SOURCES,
				GlowTags.QualifiedMatrix.CONNECTIONS);

		insert(new Leaf(GlowTags.QualifiedMatrix.PATH, new Value(path)));

		if (parent != null) {
			parent.insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedMatrix} class.
	 * 
	 * @param path
	 *            An {@link Oid} specifying the location of this matrix within
	 *            the tree.
	 * @throws NullPointerException
	 *             Thrown if {@link path} or {@link tag} is <i>null</i>.
	 */
	public GlowQualifiedMatrix(Oid path) throws NullPointerException {
		super(GlowType.QUALIFIEDMATRIX, GlowTags.ELEMENT_DEFAULT, GlowTags.QualifiedMatrix.CONTENTS,
				GlowTags.QualifiedMatrix.CHILDREN,
				GlowTags.QualifiedMatrix.TARGETS,
				GlowTags.QualifiedMatrix.SOURCES,
				GlowTags.QualifiedMatrix.CONNECTIONS);

		Assert.AssertNotNull(path, "path");

		insert(new Leaf(GlowTags.QualifiedMatrix.PATH, new Value(path)));
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedMatrix} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowQualifiedMatrix(Tag tag) {
		super(GlowType.QUALIFIEDMATRIX, tag, GlowTags.QualifiedMatrix.CONTENTS,
				GlowTags.QualifiedMatrix.CHILDREN,
				GlowTags.QualifiedMatrix.TARGETS,
				GlowTags.QualifiedMatrix.SOURCES,
				GlowTags.QualifiedMatrix.CONNECTIONS);
	}

	/**
	 * Gets the {@link Oid} specifying the location of this matrix within the
	 * tree.
	 * 
	 * @return The {@link Oid} or <i>null</i>, if the property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Oid path() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.QualifiedMatrix.PATH);

		if (leaf != null) {
			return leaf.value().toOid();
		} else {
			return null;
		}
	}
}
