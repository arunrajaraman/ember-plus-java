package libember.glow;

/**
 * Represents a formula that is used to compute a display value from a device
 * value and vice versa.
 */
public final class Formula {
	private final String providerToConsumer;
	private final String consumerToProvider;

	/**
	 * Initializes a new instance of the {@link Formula} class.
	 * 
	 * @param providerToConsumer
	 *            The term used to compute a display value. This value may be
	 *            <i>null</i>.
	 * @param consumerToProvider
	 *            The term used to compute the device value from a display
	 *            value. This value may be <i>null</i>.
	 */
	Formula(String providerToConsumer, String consumerToProvider)
			throws NullPointerException {
		this.providerToConsumer = providerToConsumer;
		this.consumerToProvider = consumerToProvider;
	}

	/**
	 * Gets the term to compute the device value from a display value.
	 * 
	 * @return The term to compute the device value from a display value.
	 */
	public String consumerToProvider() {
		return consumerToProvider;
	}

	/**
	 * Gets the term to compute the display value from a device value.
	 * 
	 * @return The term to compute the display value from a device value.
	 */
	public String providerToConsumer() {
		return providerToConsumer;
	}
}
