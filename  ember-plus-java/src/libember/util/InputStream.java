package libember.util;

import java.util.NoSuchElementException;

/**
 * The interface which is used by the {@link libember.ber.Encoding} class to
 * decode a value and by {@link libember.dom.AsyncBerReader} to decode a
 * complete Ember+ tree.
 */
public interface InputStream {
	/**
	 * Erases the current element from the stream and moves to the next item in
	 * the buffer.
	 */
	void consume();

	/**
	 * Gets the current stream element.
	 * 
	 * @return The current stream element.
	 * @throws NoSuchElementException
	 *             Thrown if the stream contains no more elements.
	 */
	int peek() throws NoSuchElementException;
}
