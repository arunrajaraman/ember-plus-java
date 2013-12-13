package libember.glow;

import libember.ber.Octets;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class is used to transport values of parameters that use a stream
 * identifier. A {@link GlowStreamEntry} must be transported as child of a
 * {@link GlowStreamCollection}.
 */
public class GlowStreamEntry extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowStreamEntry} class.
	 * 
	 * @param tag
	 *            The application tag to initialize this instance with.
	 */
	GlowStreamEntry(Tag tag) {
		super(InsertMode.SORTED, GlowType.STREAMENTRY, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowStreamEntry} class.
	 * 
	 * @param streamIdentifier
	 *            The stream identifier.
	 * @param value
	 *            The value of the parameter.
	 */
	public GlowStreamEntry(int streamIdentifier, double value) {
		super(InsertMode.SORTED, GlowType.STREAMENTRY);
		insert(new Leaf(GlowTags.StreamEntry.STREAM_IDENTIFIER, new Value(
				streamIdentifier)));
		insert(new Leaf(GlowTags.StreamEntry.STREAM_VALUE, new Value(value)));
	}

	/**
	 * Initializes a new instance of the {@link GlowStreamEntry} class.
	 * 
	 * @param streamIdentifier
	 *            The stream identifier.
	 * @param value
	 *            The value of the parameter.
	 */
	public GlowStreamEntry(int streamIdentifier, int value) {
		super(InsertMode.SORTED, GlowType.STREAMENTRY);

		insert(new Leaf(GlowTags.StreamEntry.STREAM_IDENTIFIER, new Value(
				streamIdentifier)));
		insert(new Leaf(GlowTags.StreamEntry.STREAM_VALUE, new Value(value)));
	}

	/**
	 * Initializes a new instance of the {@link GlowStreamEntry} class.
	 * 
	 * @param streamIdentifier
	 *            The stream identifier.
	 * @param value
	 *            The binary encoded values of all parameters that share the
	 *            same stream identifier. To decode this binary data usually a
	 *            {@link GlowStreamDescriptor} is required. The descriptor is
	 *            provided by the parameters.
	 * @throws NullPointerException
	 *             Thrown if {@link octets} is <i>null</i>.
	 */
	public GlowStreamEntry(int streamIdentifier, Octets octets)
			throws NullPointerException {
		super(InsertMode.SORTED, GlowType.STREAMENTRY);

		Assert.AssertNotNull(octets, "octets");

		insert(new Leaf(GlowTags.StreamEntry.STREAM_IDENTIFIER, new Value(
				streamIdentifier)));
		insert(new Leaf(GlowTags.StreamEntry.STREAM_VALUE, new Value(octets)));
	}

	/**
	 * Initializes a new instance of the {@link GlowStreamEntry} class.
	 * 
	 * @param streamIdentifier
	 *            The stream identifier.
	 * @param value
	 *            The value of the parameter.
	 * @throws NullPointerException
	 *             Thrown if {@link value} is <i>null</i>.
	 */
	public GlowStreamEntry(int streamIdentifier, String value)
			throws NullPointerException {
		super(InsertMode.SORTED, GlowType.STREAMENTRY);

		Assert.AssertNotNull(value, "value");

		insert(new Leaf(GlowTags.StreamEntry.STREAM_IDENTIFIER, new Value(
				streamIdentifier)));
		insert(new Leaf(GlowTags.StreamEntry.STREAM_VALUE, new Value(value)));
	}

	/**
	 * Gets the stream identifier of this entry.
	 * 
	 * @return The stream identifier or <i>null</i>, if the property does not
	 *         exist.
	 */
	public Integer streamIdentifier() {
		final Leaf leaf = findType(GlowTags.StreamEntry.STREAM_IDENTIFIER);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}

	/**
	 * Gets the type erased value of this entry.
	 * 
	 * @return The value of this entry or <i>null</i>, if the property does not
	 *         exist.
	 */
	public Value value() {
		final Leaf leaf = findType(GlowTags.StreamEntry.STREAM_VALUE);

		if (leaf != null) {
			return leaf.value();
		} else {
			return null;
		}
	}
}
