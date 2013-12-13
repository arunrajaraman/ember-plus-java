package libember.glow;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class describes the format of a value that is encoded in a
 * {@link GlowStreamEntry} that contains several binary encoded values.
 */
public class GlowStreamDescriptor extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowStreamDescriptor} class.
	 * 
	 * @param tag
	 *            The application tag to initialize this instance with.
	 */
	GlowStreamDescriptor(Tag tag) {
		super(InsertMode.SORTED, GlowType.STREAMDESCRIPTOR, tag);
	}

	/**
	 * Initializes a new nstance of the {@link GlowStreamDescriptor} class.
	 * 
	 * @param tag
	 *            The application tag to initialize this instance with.
	 * @param format
	 *            The {@link StreamFormat} of the encoded value.
	 * @param offset
	 *            The byte offset of the encoded value within the binary encoded
	 *            octet string stored in the stream entry.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	GlowStreamDescriptor(Tag tag, StreamFormat format, int offset)
			throws NullPointerException {
		super(InsertMode.SORTED, GlowType.STREAMDESCRIPTOR, tag);

		Assert.AssertNotNull(format, "format");

		insert(new Leaf(GlowTags.StreamDescriptor.FORMAT, new Value(
				format.value())));
		insert(new Leaf(GlowTags.StreamDescriptor.OFFSET, new Value(offset)));
	}

	/**
	 * Gets the {@link StreamFormat} of the value that is associated with this
	 * descriptor.
	 * 
	 * @return The {@link StreamFormat} of the value that is associated with
	 *         this descriptor.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public StreamFormat format() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.StreamDescriptor.FORMAT);

		if (leaf != null) {
			return StreamFormat.valueOf(leaf.value().toInt());
		} else {
			return null;
		}
	}

	/**
	 * Gets the byte offset of the value this descriptor describes.
	 * 
	 * @return The byte offset of the value this descriptor describes or
	 *         <i>null</i>, if the property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer offset() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.StreamDescriptor.OFFSET);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}
}
