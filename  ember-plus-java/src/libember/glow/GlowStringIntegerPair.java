package libember.glow;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class represents a tuple consisting of a string and an integer value. It
 * is used by the enumeration map of a {@link GlowParameterBase}.
 */
public final class GlowStringIntegerPair extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowStringIntegerPair} class.
	 * 
	 * @param tag
	 *            The application tag to initialize this instance with.
	 */
	GlowStringIntegerPair(Tag tag) {
		super(InsertMode.SORTED, GlowType.STRINGINTEGERPAIR, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowStringIntegerPair} class.
	 * 
	 * @param name
	 *            The name of the entry.
	 * @param value
	 *            The value of the entry.
	 * @throws NullPointerException
	 *             Thrown if {@link name} is <i>null</i>.
	 */
	public GlowStringIntegerPair(String name, int value)
			throws NullPointerException {
		super(InsertMode.SORTED, GlowType.STRINGINTEGERPAIR);

		Assert.AssertNotNull(name, "name");

		insert(new Leaf(GlowTags.StringIntegerPair.NAME, new Value(name)));
		insert(new Leaf(GlowTags.StringIntegerPair.VALUE, new Value(value)));
	}

	/**
	 * Gets the name of this tuple.
	 * 
	 * @return The name of this tuple or <i>null</i>, if the value has not been
	 *         specified.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String name() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.StringIntegerPair.NAME);

		if (leaf != null) {
			return leaf.value().toUTF8String();
		} else {
			return null;
		}
	}

	/**
	 * Gets the integer value of this tuple.
	 * 
	 * @return The value of this pair or <i>null</i>, if the property does not
	 *         exist.
	 */
	public Integer value() {
		final Leaf leaf = findType(GlowTags.StringIntegerPair.VALUE);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}
}
