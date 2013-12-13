package libember.util;

/**
 * A Pair stores a key-value tuple.
 * 
 * @param <TKey>
 *            The type of the key.
 * @param <TValue>
 *            The type of the value associated with the key.
 */
public final class Pair<TKey, TValue> {
	private final TKey key;
	private final TValue value;

	/**
	 * Initializes a new instance of the {@link Pair} class.
	 * 
	 * @param key
	 *            The key of this pair.
	 * @param value
	 *            The value of this pair.
	 */
	public Pair(TKey key, TValue value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		final Pair<TKey, TValue> other = (Pair<TKey, TValue>) obj;
		if (other != null)
			return equals(other);
		else
			return false;
	}

	/**
	 * Compares to pairs for equality.
	 * 
	 * @param other
	 *            The second Pair to compare against.
	 * @return <i>true</i> if the Pairs are equal, otherwise <i>false</i>.
	 */
	public boolean equals(Pair<TKey, TValue> other) {
		return key.equals(other.key) && value.equals(other.value);
	}

	/**
	 * Gets the key.
	 * 
	 * @return The key.
	 */
	public TKey key() {
		return key;
	}

	/**
	 * Gets the value that is associated with the {@link key}.
	 * 
	 * @return The value.
	 */
	public TValue value() {
		return value;
	}
}
