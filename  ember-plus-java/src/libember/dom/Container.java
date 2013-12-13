package libember.dom;

import java.util.Iterator;

import libember.ber.Tag;
import libember.util.Assert;

/**
 * Abstract base class for a {@link Node} which represents a container.
 */
public abstract class Container extends Node implements Iterable<Node> {
	/**
	 * Initializes a new instance of the {@link Container} class.
	 * 
	 * @param appTag
	 *            The application tag of this instance.
	 */
	protected Container(Tag appTag) {
		super(appTag);
	}

	/**
	 * The implementation of this method is responsible for removing the child
	 * node at the specified {@link index}.
	 * 
	 * @param index
	 *            The index of the node to remove.
	 */
	protected abstract void eraseImpl(int index);

	/**
	 * Updates the parent reference of the passed child node.
	 * 
	 * @param child
	 *            A reference to the child node whos parent must be changed.
	 * @throws UnsupportedOperationException
	 *             Thrown if {@link child} already has a parent node.
	 * @throws NullPointerException
	 *             Thrown if {@link child} is <i>null</i>.
	 */
	protected void fixParent(Node child) throws UnsupportedOperationException,
			NullPointerException {
		Assert.AssertNotNull(child, "child");

		if (child.parent() != null)
			throw new UnsupportedOperationException(
					"Attempt to reset parent of an already owned child node.");

		child.setParent(this);
	}

	/**
	 * The implementation of this method is responsible for inserting the
	 * provided child node at the specified index. If the index is out of the
	 * bounds, the {@link node} has to be appended at the end of the internal
	 * node list.
	 * 
	 * @param index
	 *            The index to insert the node at.
	 * @param node
	 *            The child node to insert.
	 */
	protected abstract void insertImpl(int index, Node node);

	/**
	 * Gets an {@link Iterator} which provides access to the children of this
	 * {@link Container} instance.
	 * 
	 * @return An {@link Iterator} which provididing access to the child nodes.
	 */
	protected abstract Iterator<Node> iteratorImpl();

	/**
	 * Removes all child nodes from this container.
	 */
	public void clear() {
		int count = size();

		while (count-- > 0) {
			erase(0);
		}
	}

	/**
	 * Removes the {@link Node} at the specified index. Invoking this method
	 * marks this instance dirty, since its state changes.
	 * 
	 * @param index
	 *            The index of the {@link Node} ro remove.
	 */
	public void erase(int index) {
		eraseImpl(index);
		markDirty();
	}

	/**
	 * Inserts a {@link Node} at the specified position. The node must be be the
	 * child of another node. Otherwise, an exception will be thrown. Invoking
	 * this method also marks this instance dirty, since its state changes.
	 * 
	 * @param index
	 *            The index to insert the {@link Node} at.
	 * @param node
	 *            The {@link Node} to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link node} is <i>null</i>.
	 * @throws UnsupportedOperationException
	 *             Thrown if {@link node} is already the child of another
	 *             container.
	 */
	public void insert(int index, Node node)
			throws UnsupportedOperationException, NullPointerException {
		Assert.AssertNotNull(node, "node");

		if (node.parent() != null)
			throw new UnsupportedOperationException(
					"Attempt to add a node already owned by a container");

		insertImpl(index, node);
		node.setParent(this);
		markDirty();
	}

	/**
	 * Appends a {@link Node} at the end of the container. The node must not be
	 * the child of another node. Otherwise, an exception will be thrown.
	 * Invoking this method also marks this instance dirty, since its state
	 * changes.
	 * 
	 * @param node
	 *            The {@link Node} to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link node} is <i>null</i>.
	 * @throws UnsupportedOperationException
	 *             Thrown if {@link node} is already the child of another
	 *             container.
	 */
	public void insert(Node node) throws NullPointerException,
			UnsupportedOperationException {
		insert(size(), node);
	}

	/**
	 * Gets a value indicating whether this node is empty.
	 * 
	 * @return <i>true</i>, if the node contains no children.
	 */
	public abstract boolean isEmpty();

	/**
	 * Gets an {@link Iterator} which provides access to the child nodes.
	 */
	@Override
	public Iterator<Node> iterator() {
		return iteratorImpl();
	}

	/**
	 * Gets the number of child nodes.
	 * 
	 * @return The number of child nodes.
	 */
	public abstract int size();
}
