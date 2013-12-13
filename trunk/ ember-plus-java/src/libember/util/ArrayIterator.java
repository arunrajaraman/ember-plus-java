package libember.util;

import java.util.Iterator;

/**
 * This class implements the generic {@link Iterator} interface to traverse a
 * collection of items.
 * 
 * @param <T>
 *            The type of a single item stored in the collection the iterator
 *            traverses.
 */
final class ArrayIterator<T> implements Iterator<T> {
	private final T[] data;
	private int cursor;

	/**
	 * Initializes a new instance of the {@link ArrayIterator} class.
	 * 
	 * @param data
	 *            The data the iterator shall traverse.
	 * @param clone
	 *            If set to <code>true</code>, the iterator creates a copy of
	 *            the provided array. Otherwise, the iterator will reference the
	 *            array passed.
	 * @throws NullPointerException
	 *             Thrown if {@link data} is <code>null</code>.
	 */
	ArrayIterator(T[] data, boolean clone) throws NullPointerException {
		Assert.AssertNotNull(data, "data");

		this.data = clone ? data.clone() : data;
	}

	/**
	 * Gets a value indicating whether another item is available.
	 * 
	 * @return <i>true</i>, if another item is available by calling {@link next}
	 *         . Otherwise, this method returns <i>false</i>.
	 */
	@Override
	public boolean hasNext() {
		return cursor < data.length;
	}

	/**
	 * Gets the current item the iterator references and moves to the next
	 * element in the collection.
	 */
	@Override
	public T next() {
		return data[cursor++];
	}

	/**
	 * This operation is not supported by the {@link ArrayIterator} and throws
	 * an {@link UnsupportedOperationException} exception.
	 * 
	 * @throws UnsupportedOperationException
	 *             Thrown when this method is being called.
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
