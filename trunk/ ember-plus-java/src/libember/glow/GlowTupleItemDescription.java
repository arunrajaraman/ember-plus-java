package libember.glow;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class is used to describe either an argument or the return type of a
 * function. It consists of a {@link ParameterType} and a name.
 */
public final class GlowTupleItemDescription extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowTupleItemDescription} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowTupleItemDescription(Tag tag) {
		super(InsertMode.SORTED, GlowType.TUPLEITEMDESCRIPTION, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowTupleItemDescription} class.
	 * 
	 * @param type
	 *            Specifies the value type of the argument or return type.
	 * @param name
	 *            The name of the argument or the return type.
	 * @throws NullPointerException
	 *             Thrown if either {@link type} or {@link name} is <i>null</i>.
	 */
	public GlowTupleItemDescription(ParameterType type, String name)
			throws NullPointerException {
		super(InsertMode.SORTED, GlowType.TUPLEITEMDESCRIPTION);

		Assert.AssertNotNull(type, "type");
		Assert.AssertNotNull(name, "name");

		insert(new Leaf(GlowTags.TupleItemDescription.TYPE, new Value(
				type.value())));
		insert(new Leaf(GlowTags.TupleItemDescription.NAME, new Value(name)));
	}

	/**
	 * Gets the name of this argument or return type.
	 * 
	 * @return The name of this argument or return type or <i>null</i>, if the
	 *         property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String name() {
		final Leaf leaf = this.findType(GlowTags.TupleItemDescription.NAME);

		if (leaf != null) {
			return leaf.value().toUTF8String();
		} else {
			return null;
		}
	}

	/**
	 * Gets the {@link ParameterType} specifying the value type of the argument
	 * or the return value.
	 * 
	 * @return The type of the argument or return type or <i>null</i>, if the
	 *         property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public ParameterType type() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.TupleItemDescription.TYPE);

		if (leaf != null) {
			return ParameterType.valueOf(leaf.value().toInt());
		} else {
			return null;
		}
	}
}
