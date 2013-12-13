package libember.dom;

import java.util.NoSuchElementException;

import libember.util.Assert;

/**
 * Implementation of the {@link AsyncBerReader} class which uses an instance of
 * {@link NodeFactory} to construct a dom from ber encoded data. It is possible
 * to derive from this class and overload the {@link itemReady},
 * {@link containerReady} and {@link rootReady} methods to receive the current
 * decoding status.
 */
public class AsyncDomReader extends AsyncBerReader {
	private final NodeFactory factory;
	private boolean isRootReady;
	private Node root;
	private Node current;

	/**
	 * Initializes a new instance of the {@link AsyncDomReader} class.
	 * 
	 * @param factory
	 *            An instance of the {@link NodeFactory} class which is used to
	 *            create application defined nodes.
	 * @throws NullPointerException
	 *             Thrown if {@link factory} is <i>null</i>.
	 */
	public AsyncDomReader(NodeFactory factory) throws NullPointerException {
		Assert.AssertNotNull(factory, "factory");

		this.factory = factory;
	}

	@Override
	protected final void containerReady() throws NoSuchElementException {
		final Node node = decode(factory);

		if (isRootReady) {
			resetImpl();
		}

		if (root == null) {
			root = node;
		} else {
			final Container container = (Container) current;

			if (container != null)
				container.insert(node);
		}

		current = node;
		containerReady(node);
	}

	/**
	 * This method is invoked when a new {@link Container} has been created.
	 * This method may be overridden in a derived class.
	 * 
	 * @param node
	 *            The {@link Node} that has been created.
	 */
	protected void containerReady(Node node) {

	}

	@Override
	protected final void itemReady() {
		if (isContainer()) {
			final Node node = current;
			if (current == root) {
				isRootReady = true;
				rootReady(node);
				current = null;
			} else {
				current = current.parent();
			}

			itemReady(node);
		} else {
			final Node node = decode(factory);
			if (node != null) {
				final Container container = (Container) current;

				if (container != null)
					container.insert(node);

				itemReady(node);
			}
		}
	}

	/**
	 * This method is invoked when a new {@link Node} has been created. This
	 * method may be overridden in a dervied class.
	 * 
	 * @param node
	 *            The {@link Node} that has been created.
	 */
	protected void itemReady(Node node) {

	}

	@Override
	protected void resetImpl() {
		root = null;
		current = null;
		isRootReady = false;
	}

	/**
	 * This method is invoked when the ber tree is complete and can be used.
	 * 
	 * @param node
	 *            The decoded root node.
	 */
	protected void rootReady(Node node) {

	}

	/**
	 * Gets a value indicating whether the ber tree has been decoded completely.
	 * 
	 * @return <i>true</i>, if the complete tree has already been decoded.
	 */
	public boolean isRootReady() {
		return this.isRootReady;
	}

	/**
	 * Gets the root node. Please note that this method only returns a valid
	 * node when {@link isRootReady} returns <i>true</i>; which indicates that
	 * the encoded tree has been decoded completely. Otherwise, an exception
	 * will be thrown.
	 * 
	 * @return The decoded root node.
	 * @throws UnsupportedOperationException
	 *             Thrown if {@link isRootReady} returns <i>false</i> and the
	 *             tree has not yet been decoded completely.
	 */
	public Node root() throws UnsupportedOperationException {
		if (isRootReady() == false)
			throw new UnsupportedOperationException(
					"The root node is not yet ready.");

		return root;
	}
}
