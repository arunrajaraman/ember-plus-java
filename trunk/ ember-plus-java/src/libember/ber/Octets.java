package libember.ber;

import java.util.Collection;
import java.util.Vector;

import libember.util.ImmutableCollection;
import libember.util.StringUtil;

/**
 * This class represents a collection of unsigned bytes.
 */
public final class Octets extends ImmutableCollection<Integer> {
	/**
	 * Gets an instance of the {@link Octets} class which is empty.
	 */
	public final static Octets EMPTY = new Octets();

	/**
	 * Initializes an empty instance of the {@link Octets} class.
	 */
	public Octets() {
		super(new Vector<Integer>());
	}

	/**
	 * Initializes a new instance of the {@link Octets} class.
	 * 
	 * @param collection
	 *            A collection containing the elements to copy.
	 * @throws NullPointerException
	 *             Thrown if {@linkplain collection} is <i>null</i>.
	 */
	public Octets(Collection<Integer> collection) throws NullPointerException {
		super(collection);
	}

	@Override
	public String toString() {
		final String octets = StringUtil.join(",", iterator());
		return String.format("Octets (Length = %d, Values = %s)", size(),
				octets);
	}
}
