package libember.glow;

import java.util.Iterator;

import libember.ber.Tag;
import libember.dom.Node;
import libember.dom.Sequence;
import libember.util.Assert;

/**
 * Base class for all types defined within the glow dtd which are based on the
 * {@link Sequence}.
 */
public class GlowContainer extends Sequence {
	/**
	 * Scoped enumeration listing the avaiable insertion modes.
	 */
	protected enum InsertMode {
		/**
		 * All new children are appended at the end of the container.
		 */
		DEFAULT,

		/**
		 * All children are sorted by the application tag.
		 */
		SORTED,
	}
	private final Tag universalTag;

	private final InsertMode insertMode;;

	/**
	 * Initializes a new instance of the {@link GlowContainer}. The application
	 * tag will be initialized with the default element tag (
	 * {@link Class.CONTEXT}, 0).
	 * 
	 * @param mode
	 *            The {@link InsertMode} to use for this container.
	 * @param type
	 *            The {@link GlowType} which determines the concrete type of
	 *            this object.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	protected GlowContainer(InsertMode mode, GlowType type) {
		super(GlowTags.ELEMENT_DEFAULT);

		Assert.AssertNotNull(type, "type");
		Assert.AssertNotNull(mode, "mode");

		universalTag = GlowType.typeTagOf(type);
		insertMode = mode;
	}

	/**
	 * Initializes a new instance of the {@link GlowContainer} class.
	 * 
	 * @param mode
	 *            The {@link InsertMode} to use for this container.
	 * @param type
	 *            The {@link GlowType} which determines the concrete type of
	 *            this object.
	 * @param tag
	 *            The application tag of this node.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	protected GlowContainer(InsertMode mode, GlowType type, Tag tag)
			throws NullPointerException {
		super(tag);

		Assert.AssertNotNull(type, "type");
		Assert.AssertNotNull(mode, "mode");

		universalTag = GlowType.typeTagOf(type);
		insertMode = mode;
	}

	/**
	 * Removes the node with the specified {@link tag}.
	 * 
	 * @param tag
	 *            The application tag of the node to remove.
	 * @return <i>true</i> if the node has been found and erased, otherwise
	 *         <i>false</i>.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected boolean erase(Tag tag) throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");

		Iterator<Node> i = iterator();

		for (int index = 0; i.hasNext(); index++) {
			final Node node = i.next();

			if (node.applicationTag().equals(tag)) {
				erase(index);
				return true;
			}
		}

		return false;
	}

	/**
	 * Searches for the child node by comparing its application tag with the one
	 * provided.
	 * 
	 * @param tag
	 *            The tag to look for.
	 * @return The {@link Node} which uses the specified {@link Tag} or
	 *         <i>null</i>, if no node with the requested tag exists.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected Node find(Tag tag) throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");

		Iterator<Node> i = iterator();

		while (i.hasNext()) {
			Node node = i.next();

			if (node.applicationTag().equals(tag)) {
				return node;
			}
		}

		return null;
	}

	/**
	 * Searches for a node with a specified application tag and a specified
	 * type. The specified type <code>T</code> must be a class deriving from
	 * {@link Node}.
	 * 
	 * @param tag
	 *            The {@link Tag} to look for.
	 * @return The node of type <code>T</code> or <code>null</code>, if no node
	 *         with the specified tag exists or the node is of a different type.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	@SuppressWarnings("unchecked")
	protected <T extends libember.dom.Node> T findType(Tag tag)
			throws NullPointerException {
		return (T) this.find(tag);
	}

	@Override
	protected void insertImpl(int index, Node node) {
		if (insertMode == InsertMode.SORTED) {
			final Tag tag = node.applicationTag();

			index = 0;

			for (Iterator<Node> i = iterator(); i.hasNext(); index++) {
				final Tag other = i.next().applicationTag();

				if (other.compareTo(tag) > 0) {
					super.insertImpl(index, node);
					return;
				}
			}
		}

		super.insertImpl(index, node);
	}

	/**
	 * Inserts the provided {@link Node} but removes an eventually existing node
	 * with the same application tag first.
	 * 
	 * @param node
	 *            The node to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link node} is <i>null</i>.
	 */
	protected void replace(Node node) {
		Assert.AssertNotNull(node, "node");

		erase(node.applicationTag());
		insert(node);
	}

	@Override
	protected Tag typeTagImpl() {
		return universalTag;
	}
}
