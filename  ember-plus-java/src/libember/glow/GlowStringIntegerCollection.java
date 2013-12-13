package libember.glow;

import java.util.Iterator;
import java.util.Vector;

import libember.ber.Tag;
import libember.dom.Node;

/**
 * This class represents a collection of string-integer pairs, which is used by
 * the enumeration map of a parameter.
 * 
 */
public final class GlowStringIntegerCollection extends GlowElement {
	/**
	 * Initializes a new instance of the {@link GlowStringIntegerCollection}
	 * class.
	 * 
	 * @param tag
	 *            The application tag to initialize this instance with.
	 */
	GlowStringIntegerCollection(Tag tag) {
		super(InsertMode.DEFAULT, GlowType.STRINGINTEGERCOLLECTION, tag);
	}

	/**
	 * Adds a new string-integer pair into this collection.
	 * 
	 * @param name
	 *            The name of the entry to insert.
	 * @param value
	 *            The value of the entry to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link name} is <i>null</i>.
	 */
	public void insert(String name, int value) throws NullPointerException {
		insert(new GlowStringIntegerPair(name, value));
	}

	/**
	 * Gets an {@link Iterable} containing all instances of the
	 * {@link GlowStringIntegerPair} class.
	 * 
	 * @return An {@link Iterable} containing the string-integer pairs of this
	 *         collection.
	 */
	public Iterable<GlowStringIntegerPair> pairs() {
		final Iterator<Node> it = iterator();
		final Vector<GlowStringIntegerPair> result = new Vector<GlowStringIntegerPair>();

		while (it.hasNext()) {
			final GlowStringIntegerPair pair = (GlowStringIntegerPair) it
					.next();

			if (pair != null)
				result.add(pair);
		}

		return result;
	}
}
