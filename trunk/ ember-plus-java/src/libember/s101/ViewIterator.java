/**
 * 
 */
package libember.s101;

import java.util.Iterator;

import libember.util.Assert;

/**
 * This class implements the {@link Iterator} interface and is used to traverse
 * only over a subset of a parent {@link Iterator}.
 */
final class ViewIterator<E> implements Iterator<E> {
	final Iterator<E> source;
	final int count;
	int index;

	/**
	 * Initializes a new instance of the {@link ViewIterator} class.
	 * 
	 * @param source
	 *            The original {@link Iterator}. The view starts iterating from
	 *            the current position.
	 * @param count
	 *            The number of items this view may provide.
	 * @throws NullPointerException
	 *             Thrown if {@link source} is <i>null</i>.
	 */
	ViewIterator(Iterator<E> source, int count) throws NullPointerException {
		Assert.AssertNotNull(source, "source");

		this.source = source;
		this.count = count;
	}

	@Override
	public boolean hasNext() {
		return index < count && source.hasNext();
	}

	@Override
	public E next() {
		index++;
		return source.next();
	}

	@Override
	public void remove() {
		source.remove();
	}
}
