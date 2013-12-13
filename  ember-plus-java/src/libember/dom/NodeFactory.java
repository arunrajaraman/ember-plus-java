package libember.dom;

import libember.ber.Tag;
import libember.ber.Type;

/**
 * A default implementation of the {@link NodeFactory} class, which is only able
 * to create the default ber container types {@link Set} and {@link Sequence}.
 * The {@link createApplicationDefinedNode} method always returns <i>null</i>.
 */
final class NodeFactoryImpl extends NodeFactory {
	/**
	 * Initializes a new instance of the {@link NodeFactoryImpl} class.
	 */
	NodeFactoryImpl() {
	}

	@Override
	public Node createApplicationDefinedNode(Type type, Tag tag) {
		return null;
	}
}

/**
 * Base class for a factory wich is used by the {@link AsyncDomReader} class to
 * create a concrete instance of an application defined class that is deriving
 * from the {@link Node} class.
 */
public abstract class NodeFactory {
	/**
	 * Gets a default implementation of a {@link NodeFactory}, which is only
	 * able to create default containers of type {@link Set} or {@link Sequence}
	 * .
	 */
	public static final NodeFactory DEFAULT = new NodeFactoryImpl();

	/**
	 * Creates a new instance of the {@link Sequence} class.
	 * 
	 * @param tag
	 *            The application tag of the set to create.
	 * @return The new instance of the {@link Sequence} class.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected Node createSequence(Tag tag) {
		return new Sequence(tag);
	}

	/**
	 * Creates a new instance of the {@link Set} class.
	 * 
	 * @param tag
	 *            The application tag of the set to create.
	 * @return The new instance of the {@link Set} class.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected Node createSet(Tag tag) throws NullPointerException {
		return new Set(tag);
	}

	/**
	 * This method is called by the {@link AsyncDomReader} class in order to
	 * create an application defined node which is not part in the default BER
	 * object model. This method must not throw any exceptions.
	 * 
	 * @param type
	 *            The application defined {@link Type}.
	 * @param tag
	 *            The application {@link Tag} of the node to create.
	 * @return An instance of the newly created node or <i>null</i>, if the
	 *         {@link NodeFactory} does not know the requested type.
	 */
	public abstract Node createApplicationDefinedNode(Type type, Tag tag);
}
