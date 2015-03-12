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
	private int end;

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
	  this(data, clone, 0, data.length);
	}

  /**
   * Initializes a new instance of the {@link ArrayIterator} class.
   * 
   * @param data
   *            The data the iterator shall traverse.
   * @param clone
   *            If set to <code>true</code>, the iterator creates a copy of
   *            the provided array. Otherwise, the iterator will reference the
   *            array passed.
   * @param offset
   *            Index of the first item to yield.
   * @param count
   *            Number of items to yield.
   * @throws NullPointerException
   *             Thrown if {@link data} is <code>null</code>.
   */
  ArrayIterator(T[] data, boolean clone, int offset, int count) throws NullPointerException {
    Assert.AssertNotNull(data, "data");
    
    if (offset < 0 || offset + count > data.length)
      throw new IndexOutOfBoundsException();

    this.data = clone ? data.clone() : data;
    this.end = offset + count;
    this.cursor = offset;
  }

	/**
	 * Gets a value indicating whether another item is available.
	 * 
	 * @return <i>true</i>, if another item is available by calling {@link next}
	 *         . Otherwise, this method returns <i>false</i>.
	 */
	public boolean hasNext() {
		return this.cursor < this.end;
	}

	/**
	 * Gets the current item the iterator references and moves to the next
	 * element in the collection.
	 */
	public T next() {
		return this.data[this.cursor++];
	}

	/**
	 * This operation is not supported by the {@link ArrayIterator} and throws
	 * an {@link UnsupportedOperationException} exception.
	 * 
	 * @throws UnsupportedOperationException
	 *             Thrown when this method is being called.
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
