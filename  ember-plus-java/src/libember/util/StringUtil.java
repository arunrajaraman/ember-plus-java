package libember.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * This class which provides some helper methods for string operations.
 */
public final class StringUtil {
	/**
	 * Concatenates a collection of elements into a single string.
	 * 
	 * @param <T>
	 *            The type of a collection element.
	 * @param separator
	 *            The separator to insert between to items of the collection.
	 * @param items
	 *            Collection containing the items to concatenate.
	 * @return A string containing all elements concatenated into a single
	 *         string.
	 * @throws NullPointerException
	 *             Thrown if either {@link separator} or {@link items} is
	 *             <i>null</i>.
	 */
	public static <T> String join(String separator, Collection<T> items)
			throws NullPointerException {
		Assert.AssertNotNull(items, "items");

		return join(separator, items.iterator());
	}

	/**
	 * Concatenates a collection of elements into a single string.
	 * 
	 * @param <T>
	 *            The type of a collection element.
	 * @param separator
	 *            The separator to insert between the items of the collection.
	 * @param it
	 *            The {@link Iterator} containing the elements to concatenate.
	 * @return A string containing all elements concatenated into a single
	 *         string.
	 * @throws NullPointerException
	 *             Thrown if either {@link separator} or {@link it} is
	 *             <i>null</i>.
	 */
	public static <T> String join(String separator, Iterator<T> it)
			throws NullPointerException {
		Assert.AssertNotNull(separator, "separator");
		Assert.AssertNotNull(it, "it");

		final StringBuilder sb = new StringBuilder();

		for (; it.hasNext();) {
			sb.append(it.next());
			if (it.hasNext())
				sb.append(separator);
		}
		return sb.toString();
	}

	/**
	 * Concatenates a collection of elements into a single string.
	 * 
	 * @param <T>
	 *            The type of a collection element.
	 * @param separator
	 *            The separator to insert between to items of the collection.
	 * @param items
	 *            Array containing the items to concatenate.
	 * @return A string containing all elements concatenated into a single
	 *         string.
	 * @throws NullPointerException
	 *             Thrown if either {@link separator} or {@link items} is
	 *             <i>null</i>.
	 */
	public static <T> String join(String separator, T[] items)
			throws NullPointerException {
		Assert.AssertNotNull(items, "items");

		return join(separator, new ArrayIterator<T>(items, false /* clone */));
	}
}
