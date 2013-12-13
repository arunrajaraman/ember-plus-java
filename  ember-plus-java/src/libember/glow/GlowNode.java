package libember.glow;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;

/**
 * This class represents a node within an Ember+ tree.
 */
public final class GlowNode extends GlowNodeBase {
	/**
	 * Initializes a new instance of the {@link GlowNode} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowNode(Tag tag) {
		super(GlowType.NODE, tag, GlowTags.Node.CONTENTS,
				GlowTags.Node.CHILDREN);
	}

	/**
	 * Initializes a new instance of the {@link GlowNode} class.
	 * 
	 * @param parent
	 *            The {@link GlowNodeBase} to append this instance to.
	 * @param number
	 *            The number identifying this node.
	 */
	public GlowNode(GlowNodeBase parent, int number) {
		this(number);

		if (parent != null) {
			parent.children(true).insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowNode} class.
	 * 
	 * @param parent
	 *            The {@link GlowRootElementCollection} to append this instance
	 *            to.
	 * @param number
	 *            The number identifying this node.
	 */
	public GlowNode(GlowRootElementCollection parent, int number) {
		this(number);

		if (parent != null) {
			parent.insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowNode} class.
	 * 
	 * @param number
	 *            The number to initialize this instance with.
	 */
	public GlowNode(int number) {
		super(GlowType.NODE, GlowTags.ELEMENT_DEFAULT, GlowTags.Node.CONTENTS,
				GlowTags.Node.CHILDREN);
		insert(new Leaf(GlowTags.Node.NUMBER, new Value(number)));
	}

	/**
	 * Gets the number identifying this node.
	 * 
	 * @return The number of this node or <i>null</i>, if the property is not
	 *         set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer number() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.Node.NUMBER);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}
}
