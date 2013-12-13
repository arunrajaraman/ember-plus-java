package libember.glow;

import libember.ber.Oid;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class represents a node within the gadget tree. The qualified variant
 * uses an {@link Oid} to represent its location within the tree.
 */
public final class GlowQualifiedNode extends GlowNodeBase {
	/**
	 * Initializes a new instance of the {@link GlowQualifiedNode} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowQualifiedNode(Tag tag) {
		super(GlowType.QUALIFIEDNODE, tag, GlowTags.QualifiedNode.CONTENTS,
				GlowTags.QualifiedNode.CHILDREN);
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedNode} class.
	 * 
	 * @param parent
	 *            The {@link GlowRootElementCollection} where this node will be
	 *            appended to.
	 * @param path
	 *            The {@link Oid} specifying the location of this node.
	 */
	public GlowQualifiedNode(GlowRootElementCollection parent, Oid path) {
		this(path);
		if (parent != null) {
			parent.insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowQualifiedNode} class.
	 * 
	 * @param path
	 *            An {@link Oid} specifying the location of this node within the
	 *            tree.
	 * @throws NullPointerException
	 *             Thrown if {@link path} is <i>null</i>.
	 */
	public GlowQualifiedNode(Oid path) throws NullPointerException {
		this(GlowTags.ELEMENT_DEFAULT);

		Assert.AssertNotNull(path, "path");

		insert(new Leaf(GlowTags.QualifiedNode.PATH, new Value(path)));
	}

	/**
	 * Gets the {@link Oid} specifying the location of this node within the
	 * tree.
	 * 
	 * @return The {@link Oid} or <i>null</i>, if the property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Oid path() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.QualifiedNode.PATH);

		if (leaf != null) {
			return leaf.value().toOid();
		} else {
			return Oid.EMPTY;
		}
	}
}
