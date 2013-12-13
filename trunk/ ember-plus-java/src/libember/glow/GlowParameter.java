package libember.glow;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;

/**
 * This class represents a parameter within an Ember+ tree.
 */
public final class GlowParameter extends GlowParameterBase {
	/**
	 * Initializes a new instance of the {@link GlowParameter} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	GlowParameter(Tag tag) throws NullPointerException {
		super(GlowType.PARAMETER, tag, GlowTags.Parameter.CONTENTS,
				GlowTags.Parameter.CHILDREN);
	}

	/**
	 * Initializes a new instance of the {@link GlowParameter} class.
	 * 
	 * @param parent
	 *            The {@link GlowNodeBase} to append this {@link GlowParameter}
	 *            to.
	 * @param number
	 *            The number identifying this parameter.
	 */
	public GlowParameter(GlowNodeBase parent, int number) {
		this(number);

		if (parent != null)
			parent.children(true).insert(this);
	}

	/**
	 * Initializes a new instance of the {@link GlowParameter} class.
	 * 
	 * @param number
	 *            The number identifying this parameter.
	 */
	public GlowParameter(int number) {
		this(GlowTags.ELEMENT_DEFAULT, number);
	}

	/**
	 * Initializes a new instance of the {@link GlowParameter} class.
	 * 
	 * @param tag
	 *            The application tag of this parameter.
	 * @param number
	 *            The number identifying this parameter.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	public GlowParameter(Tag tag, int number) throws NullPointerException {
		this(tag);

		insert(new Leaf(GlowTags.Parameter.NUMBER, new Value(number)));
	}

	/**
	 * Gets the number identifying this parameter.
	 * 
	 * @return The number identifying this parameter or <i>null</i>, if the
	 *         property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer number() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.Parameter.NUMBER);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}
}
