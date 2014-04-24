package libember.glow;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

/**
 * Scoped enumeration listing the symbolic names of the available node
 * properties.
 */
public enum NodeProperty {
	/**
	 * Identifies the identifier of a node.
	 */
	IDENTIFIER(0),

	/**
	 * Identifies the description of a node.
	 */
	DESCRIPTION(1),

	/**
	 * Identifies the root flag of a node. This flag is used by proxies in order
	 * to be able to know how to translate relative object identifiers.
	 */
	ISROOT(2), 
	
	/**
	 * Identifies the online state of a node.
	 */
	ISONLINE(3),
	
	/**
	 * Identifies the schema identifier string of a node.
	 */
	SCHEMAIDENTIFIERS(4);

	/**
	 * Gets the {@link NodeProperty} represented by the passed numeric value.
	 * 
	 * @param value
	 *            The numeric value representing a {@link NodeProperty}.
	 * @return The {@link NodeProperty} represented by the passed numeric value.
	 * @throws NoSuchElementException
	 *             Thrown if the specified value is invalid.
	 */
	public static NodeProperty valueOf(int value) {
		switch (value) {
		case 0:
			return IDENTIFIER;
		case 1:
			return DESCRIPTION;
		case 2:
			return ISROOT;
		case 3:
			return ISONLINE;
		case 4:
			return SCHEMAIDENTIFIERS;
		default:
			throw new InvalidParameterException(
					"The specified value is invalid.");
		}
	}

	private final int value;

	/**
	 * Initializes a new instance of the {@link NodeProperty} class.
	 * 
	 * @param value
	 *            The value to initialize this instance with.
	 */
	private NodeProperty(int value) {
		this.value = value;
	}

	/**
	 * Gets the numeric representation of this instance.
	 * 
	 * @return The numeric representation of this instance.
	 */
	public int value() {
		return value;
	}
}
