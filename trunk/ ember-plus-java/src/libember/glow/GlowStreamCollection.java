package libember.glow;

import libember.ber.Octets;
import libember.ber.Tag;
import libember.util.Assert;

/**
 * This class serves as a container for elements of type {@link GlowStreamEntry}
 * . It may be sent at root level and is a compact way of transmitting value
 * changes.
 */
public class GlowStreamCollection extends GlowElement {
	/**
	 * Creates a new instance of the {@link GlowStreamCollection} class which is
	 * marked as root object.
	 * 
	 * @return A new instance of the {@link GlowStreamCollection} class.
	 */
	public static GlowStreamCollection create() {
		return new GlowStreamCollection(GlowTags.ROOT);
	}

	/**
	 * Initializes a new instance of the {@link GlowStreamCollection} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowStreamCollection(Tag tag) {
		super(InsertMode.DEFAULT, GlowType.STREAMCOLLECTION, tag);
	}

	/**
	 * Appends a new instance of the {@link GlowStreamEntry} class.
	 * 
	 * @param entry
	 *            The entry to append to this collection.
	 * @throws NullPointerException
	 *             Thrown if {@link entry} is <i>null</i>.
	 * @throws UnsupportedOperationException
	 *             Thrown if the entry already has a parent.
	 */
	public void insert(GlowStreamEntry entry) throws NullPointerException,
			UnsupportedOperationException {
		Assert.AssertNotNull(entry, "entry");

		super.insert(entry);
	}

	/**
	 * Creates a new instance of the {@link GlowStreamEntry} class and adds it
	 * to this container.
	 * 
	 * @param streamIdentifier
	 *            The stream identifier of the entry to append.
	 * @param value
	 *            The current value of the parameter that is associated to this
	 *            stream entry.
	 */
	public void insert(int streamIdentifier, double value) {
		insert(new GlowStreamEntry(streamIdentifier, value));
	}

	/**
	 * Creates a new instance of the {@link GlowStreamEntry} class and adds it
	 * to this container.
	 * 
	 * @param streamIdentifier
	 *            The stream identifier of the entry to append.
	 * @param value
	 *            The current value of the parameter that is associated to this
	 *            stream entry.
	 */
	public void insert(int streamIdentifier, int value) {
		insert(new GlowStreamEntry(streamIdentifier, value));
	}

	/**
	 * Creates a new instance of the {@link GlowStreamEntry} class and adds it
	 * to this container.
	 * 
	 * @param streamIdentifier
	 *            The stream identifier of the entry to append.
	 * @param value
	 *            The current value of the parameter that is associated to this
	 *            stream entry.
	 */
	public void insert(int streamIdentifier, Octets value) {
		insert(new GlowStreamEntry(streamIdentifier, value));
	}

	/**
	 * Creates a new instance of the {@link GlowStreamEntry} class and adds it
	 * to this container.
	 * 
	 * @param streamIdentifier
	 *            The stream identifier of the entry to append.
	 * @param value
	 *            The current value of the parameter that is associated to this
	 *            stream entry.
	 */
	public void insert(int streamIdentifier, String value) {
		insert(new GlowStreamEntry(streamIdentifier, value));
	}
}
