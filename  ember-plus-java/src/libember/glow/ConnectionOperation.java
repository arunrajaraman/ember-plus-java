package libember.glow;

import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the symbolic names of the available connection
 * operations. The {@link ConnectionOperation} indicates whether a crosspoint
 * shall be connected or disconnected, or whether an absolute list of connected
 * sources is being reported.
 */
public enum ConnectionOperation {
	/**
	 * A {@link GlowConnection} object is reported an absolute state which
	 * contains all sources that are currently connected to a target.
	 */
	ABSOLUTE(0),

	/**
	 * The {@link GlowConnection} object contains a source target tuple that
	 * shall be connected.
	 */
	CONNECT(1),

	/**
	 * The {@link GlowConnection} object contains a source target tuple to
	 * disconnect.
	 */
	DISCONNECT(2);

	/**
	 * Gets the {@link ConnectionOperation} that is associated with the
	 * specified {@link value}.
	 * 
	 * @param value
	 *            The numeric representation of the {@link ConnectionOperation}
	 *            to return.
	 * @return The {@link ConnectionOperation} that uses {@link value} as
	 *         numeric representation.
	 * @throws NoSuchElementException
	 *             Thrown if the specified value is not associated with a
	 *             {@link ConnectionOperation}.
	 */
	public static ConnectionOperation valueOf(int value)
			throws NoSuchElementException {
		switch (value) {
		case 0:
			return ABSOLUTE;
		case 1:
			return CONNECT;
		case 2:
			return DISCONNECT;
		}

		throw new NoSuchElementException("The value is invalid.");
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link ConnectionOperation}.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private ConnectionOperation(int value) {
		this.value = value;
	}

	/**
	 * Compares the provided {@link value} against the numeric representation of
	 * this instance for equality.
	 * 
	 * @param value
	 *            The value to compare against this instance.
	 * @return <i>true</i>, if {@link value} equals
	 *         {@link ConnectionOperation.value}. Otherwise, <i>false</i>.
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
