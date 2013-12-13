package libember.util;

/**
 * Helper class which evaluates different kinds of expressions and throws an
 * exception if evaluation fails.
 */
public final class Assert {
	/**
	 * Tests whether {@link arg} is not <code>null</code> and throws an
	 * exception, if {@link arg} is <code>null</code>.
	 * 
	 * @param arg
	 *            The argument to compare against <code>null</code>.
	 * @param name
	 *            The name of the argument. This name will be contained in the
	 *            exception message ia {@link arg} is <code>null</code>.
	 * @throws NullPointerException
	 *             Thrown if {@link arg} is <code>null</code>.
	 */
	public static void AssertNotNull(Object arg, String name)
			throws NullPointerException {
		if (arg == null)
			throw new NullPointerException(name + " must not be null.");
	}
}
