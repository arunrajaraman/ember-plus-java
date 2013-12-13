package libember.dom;

import libember.ber.Tag;
import libember.util.Assert;
import libember.util.OutputStream;

/**
 * Base class for a dom node which is encodable.
 */
public abstract class Node {
	private final Tag appTag;
	private Node parent;
	private boolean dirty = true;

	/**
	 * Initializes a new instance of the {@link Node} class.
	 * 
	 * @param applicationTag
	 *            The application {@link Tag} of this node. This {@link Tag} can
	 *            be used to indicate the semantics of a {@link Node}, while the
	 *            type tag contains the type information - as the name
	 *            indicates.
	 * @throws NullPointerException
	 *             Thrown if {@link applicationTag} is <i>null</i>.
	 */
	protected Node(Tag applicationTag) throws NullPointerException {
		Assert.AssertNotNull(applicationTag, "applicationTag");

		this.appTag = applicationTag;
	}

	/**
	 * Invokes {@link updateImpl} if <code>dirty</code> is set to
	 * <code>true</code> and resets the dirty state to <code>false</code>.
	 */
	void update() {
		if (dirty) {
			updateImpl();
			dirty = false;
		}
	}

	/**
	 * Gets the encoded length of this node, which also includes the accumulated
	 * length of all eventually existing children.
	 * 
	 * @return The encoded length of this node.
	 */
	protected abstract int encodedLengthImpl();

	/**
	 * Encodes the data of this node and writes to the provided
	 * {@link OutputStream}.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded data to.
	 */
	protected abstract void encodeImpl(OutputStream output);

	/**
	 * Gets a value indicating whether this {@link Node} is marked dirty. If so,
	 * {@link update} must be called in order to compute the encoded length of
	 * this {@link Node}.
	 * 
	 * @return <i>true</i>, if the node is marked dirty.
	 */
	protected boolean isDirty() {
		return dirty;
	}

	/**
	 * Marks this node and all of its parents dirty. This method is invoked when
	 * a child node is inserted to a {@link Container}.
	 */
	protected void markDirty() {
		dirty = true;

		if (parent != null) {
			parent.markDirty();
		}
	}

	/**
	 * Assigns a new parent to this {@link Node}. This method is usually only
	 * called by the {@link Container} class.
	 * 
	 * @param parent
	 *            The new parent of this node.
	 */
	protected void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Gets the {@link Tag} identifying the type of this {@link Node}.
	 * 
	 * @return The type tag of this node.
	 */
	protected abstract Tag typeTagImpl();

	/**
	 * Recomputes the encoded length of this node. An implementation may also
	 * preencode the node's data. This method is called by update.
	 */
	protected abstract void updateImpl();

	/**
	 * Gets the application tag of this node.
	 * 
	 * @return The application tag of this node.
	 */
	public Tag applicationTag() {
		return appTag;
	}

	/**
	 * Encodes the node and writes the data to the specified
	 * {@link OutputStream}.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded data to.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>
	 */
	public void encode(OutputStream output) throws NullPointerException {
		Assert.AssertNotNull(output, "output");

		update();
		encodeImpl(output);
	}

	/**
	 * Gets the encoded length of this node. If the node is marked dirty,
	 * {@link update} is being called to issue a recomputation of the encoded
	 * length.
	 * 
	 * @return The encoded length of this node.
	 */
	public int encodedLength() {
		update();
		return encodedLengthImpl();
	}

	/**
	 * Gets the parent of this node. This value might be <i>null</i>.
	 * 
	 * @return The parent of this node.
	 */
	public Node parent() {
		return parent;
	}

	/**
	 * Gets the {@link Tag} identifying the type of this {@link Node}.
	 * 
	 * @return The type tag of this node.
	 */
	public Tag typeTag() {
		return typeTagImpl();
	}
}
