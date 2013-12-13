package libember.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Base class for a generic array which implements the {@link Iterable}
 * interface.
 * 
 * @param <T>
 *            The type of a single array element.
 */
public abstract class ImmutableCollection<T> implements Iterable<T> {
	private final T[] data;

	/**
	 * Initialiezs a new instance of the {@link ImmutableCollection} class.
	 * 
	 * @param collection
	 *            The collection to copy the elements from.
	 * @throws NullPointerException
	 *             Thrown if {@link collection} is <code>null</code>.
	 */
	@SuppressWarnings("unchecked")
	protected ImmutableCollection(Collection<T> collection) {
		if (collection == null)
			throw new NullPointerException("collection must not be null.");

		data = (T[]) collection.toArray();
	}

	/**
	 * Gets a value indicating whether this collection contains any items.
	 * 
	 * @return <i>true</i>, if the collection contains at least one item.
	 */
	public boolean any() {
		return data.length > 0;
	}

	/**
	 * Gets the element at the specified index.
	 * 
	 * @param index
	 *            The of the element to return.
	 * @return The element at the specified index.
	 * @throws IndexOutOfBoundsException
	 *             Thrown if the specified index is out of bounds.
	 */
	public T elementAt(int index) {
		return data[index];
	}

	/**
	 * Gets a value indicating whether this collection is empty or not.
	 * 
	 * @return <i>true</i>, if the collection is empty. Otherwise, this method
	 *         returns <i>false</i>.
	 */
	public boolean empty() {
		return data.length == 0;
	}

	/**
	 * Gets a new {@link Iterator} which can be used to walk through all
	 * elements of the array.
	 * 
	 * @return A new iterator which can be used to walk through all elements of
	 *         the array.
	 */
	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator<T>(data, false);
	}

	/**
	 * Gets the number of items the collection contains.
	 * 
	 * @return The number of items the collection contains.
	 */
	public int size() {
		return data.length;
	}
}
