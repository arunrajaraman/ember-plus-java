package libember.glow;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the symbolic names of the available connection
 * disposition types. The {@link ConnectionDisposition} indicates why a
 * connection is being reported, for example whether it is a requested tally
 * notification or a modified connection.
 */
public enum ConnectionDisposition {
	/**
	 * A connection is being reported for informational purposes only. For
	 * example, when requesting the current source(s) of a target.
	 */
	TALLY(0),

	/**
	 * A connection has been modified. So a source has either been connected to
	 * a target or a disconnected from that target.
	 */
	MODIFIED(1),

	/**
	 * An operation that has been issued is still pending.
	 */
	PENDING(2),

	/**
	 * The target is locked and can therefore not be modified right now.
	 */
	LOCKED(3);

	/**
	 * Gets the {@link ConnectionDisposition} that is associated with the
	 * provided value.
	 * 
	 * @param value
	 *            The numeric value to look up.
	 * @return The {@link ConnectionDisposition} representation of the passed
	 *         numeric value.
	 * @throws NoSuchElementException
	 *             Thrown if the specified value is invalid.
	 */
	public static ConnectionDisposition valueOf(int value)
			throws NoSuchElementException {
		switch (value) {
		case 0:
			return TALLY;
		case 1:
			return MODIFIED;
		case 2:
			return PENDING;
		case 3:
			return LOCKED;
		}

		throw new NoSuchElementException("The specified value is invalid.");
	}

	private final int value;

	private ConnectionDisposition(int value) {
		this.value = value;
	}

	/**
	 * Compares the {@link value} against the internal value of this
	 * {@link ConnectionDisposition}.
	 * 
	 * @param value
	 *            The value to compare.
	 * @return <i>true</i>, if both values are equal. Otherwise, this method
	 *         returns <i>false</i>.
	 */
	public boolean equals(int value) {
		return this.value == value;
	}

	/**
	 * Gets the numeric representation of this instance.
	 * 
	 * @return The numeric representation of this instance.
	 */
	public int value() {
		return this.value;
	}
}
