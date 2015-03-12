package libember.ber;

import java.util.Collection;
import java.util.ArrayList;

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
		super(new ArrayList<Integer>());
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

	public int compareTo(Oid o) {
		final int length = size();

		if (length == o.size()) {
			for (int i = 0; i < length; ++i) {
				final int difference = get(i) - o.get(i);

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
	
	/**
	 * Concatenates the sub-ids from this {@link Oid} and the sub-ids
	 * of another {@link Oid} into a new {@link Oid}.
	 * 
	 * @param other
	 *           The {@link Oid} to append to this {@link Oid}. 
	 * @return A new instance of {@link Oid}.
	 */
	public Oid append(Oid other) {
	  final ArrayList<Integer> data = new ArrayList<Integer>(size() + other.size());

	  for (final Integer n : this)
	    data.add(n);

	  for (final Integer n : other)
	    data.add(n);

	  return new Oid(data);
	}

  /**
   * Appends a single sub-id to this oid into a new {@link Oid}.
   * 
   * @param subid
   *           The sub-id to append. 
   * @return A new instance of {@link Oid}.
   */
  public Oid append(int subid) {
    final int size = size();
    final ArrayList<Integer> data = new ArrayList<Integer>(size + 1);

    for (final Integer n : this)
      data.add(n);

    data.set(size, subid);

    return new Oid(data);
  }

	@Override
	public String toString() {
		final String items = StringUtil.join(".", iterator());
		return String.format("Oid (Length = %d, Items = %s)", size(), items);
	}
}
