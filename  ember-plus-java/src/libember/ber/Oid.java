package libember.ber;

import java.util.Collection;
import java.util.Vector;

import libember.util.ImmutableCollection;
import libember.util.StringUtil;

/**
 * Represents an Object identifier. An object is identified by a collection of
 * numeric values.
 */
public final class Oid extends ImmutableCollection<Integer> implements
		Comparable<Oid> {
	/**
	 * Gets a constant value representing an empty {@link Oid}.
	 */
	public static final Oid EMPTY = new Oid();

	/**
	 * Initializes a new instance of the {@link Oid} class.
	 */
	private Oid() {
		super(new Vector<Integer>());
	}

	/**
	 * Initializes a new instance of the {@link Oid} class.
	 * 
	 * @param collection
	 *            The collection to copy the numbers for this instance from.
	 * @throws NullPointerException
	 *             Thrown if {@link collection} is <i>null</i>.
	 */
	public Oid(Collection<Integer> collection) throws NullPointerException {
		super(collection);
	}

	@Override
	public int compareTo(Oid o) {
		final int length = size();

		if (length == o.size()) {
			for (int i = 0; i < length; ++i) {
				final int difference = elementAt(i) - o.elementAt(i);

				if (difference != 0) {
					return difference;
				}
			}
		} else {
			return length - o.size();
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Oid) {
			return equals((Oid) obj);
		}
		return false;
	}

	/**
	 * Compares this {@link Oid} with the one provided for equality.
	 * 
	 * @param other
	 *            The second {@link Oid} to compare against.
	 * @return <code>true</code> if both instances are equal, otherwise
	 *         <code>false</code>.
	 */
	public boolean equals(Oid other) {
		return compareTo(other) == 0;
	}

	@Override
	public String toString() {
		final String items = StringUtil.join(".", iterator());
		return String.format("Oid (Length = %d, Items = %s)", size(), items);
	}
}
