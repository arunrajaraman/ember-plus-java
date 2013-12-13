package libember.glow;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.glow.GlowTags.Function;

/**
 * This class represents a {@link Function} within the Ember+ tree.
 */
public final class GlowFunction extends GlowFunctionBase {
	/**
	 * Initializes a new instance of the {@link GlowFunction} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowFunction(Tag tag) {
		super(GlowType.FUNCTION, tag, GlowTags.Function.CONTENTS,
				GlowTags.Function.CHILDREN);
	}

	/**
	 * Initializes a new instance of the {@link GlowFunction} class.
	 * 
	 * @param parent
	 *            The {@link GlowNodeBase} to insert this instance to.
	 * @param number
	 *            The number identifying this function.
	 */
	public GlowFunction(GlowNodeBase parent, int number) {
		super(GlowType.FUNCTION, GlowTags.ELEMENT_DEFAULT,
				GlowTags.Function.CONTENTS, GlowTags.Function.CHILDREN);

		this.insert(new Leaf(GlowTags.Function.NUMBER, new Value(number)));

		if (parent != null) {
			parent.children(true).insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowFunction} class.
	 * 
	 * @param parent
	 *            The {@link GlowRootElementCollection} to insert this instance
	 *            to.
	 * @param number
	 *            The number identifying this function.
	 */
	public GlowFunction(GlowRootElementCollection parent, int number) {
		super(GlowType.FUNCTION, GlowTags.ELEMENT_DEFAULT,
				GlowTags.Function.CONTENTS, GlowTags.Function.CHILDREN);

		this.insert(new Leaf(GlowTags.Function.NUMBER, new Value(number)));

		if (parent != null) {
			parent.insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowFunction} class.
	 * 
	 * @param number
	 *            The number identifying this function.
	 */
	public GlowFunction(int number) {
		super(GlowType.FUNCTION, GlowTags.ELEMENT_DEFAULT,
				GlowTags.Function.CONTENTS, GlowTags.Function.CHILDREN);

		this.insert(new Leaf(GlowTags.Function.NUMBER, new Value(number)));
	}

	/**
	 * Gets the number of this function or <i>null</i>, if it is not specified.
	 * 
	 * @return The number of this function. A return value of <i>null</i>
	 *         indicates that the number is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer number() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.Function.NUMBER);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}
}
