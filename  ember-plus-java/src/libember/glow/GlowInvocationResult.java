package libember.glow;

import java.util.ArrayList;
import java.util.Iterator;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.dom.Node;
import libember.dom.Sequence;
import libember.util.Assert;

/**
 * This class represents the result of a function invocation, which is triggerd
 * by transmitting an object of type {@link GlowInvocation}.
 */
public final class GlowInvocationResult extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowInvocationResult} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	GlowInvocationResult(Tag tag) throws NullPointerException {
		super(InsertMode.DEFAULT, GlowType.INVOCATIONRESULT, tag);
	}

  /**
   * Creates a new instance of the {@link GlowInvocationResult} class which is
   * marked as root object.
   * 
   * @return A new instance of the {@link GlowInvocationResult} class.
   */
  public static GlowInvocationResult create() {
    return new GlowInvocationResult(GlowTags.ROOT);
  }

  /**
	 * Gets the invocation identifier that is associated with the invocation.
	 * 
	 * @return The numeric invocation identifier or <i>null</i>, if the property
	 *         does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer invocationId() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.InvocationResult.INVOCATIONID);

		if (leaf != null)
			return leaf.value().toInt();
		else
			return null;
	}

	/**
	 * Gets a {@link Sequence} containing the return values of this instance.
	 * 
	 * @return The {@link Sequence} containing the return values or <i>null</i>,
	 *         if the container does not exist.
	 */
	public Sequence result() {
		return result(false);
	}

	/**
	 * Gets a {@link Sequence} containing the return values of this instance.
	 * 
	 * @param create
	 *            Indicates whether the {@link Sequence} shall be created, if it
	 *            does not yet exist.
	 * @return The {@link Sequence} containing the return values or <i>null</i>,
	 *         if the container does not exist and {@link create} has been set
	 *         to <i>false</i>.
	 */
	public Sequence result(boolean create) {
		Node node = find(GlowTags.InvocationResult.RESULT);

		if (node == null && create) {
			node = new Sequence(GlowTags.InvocationResult.RESULT);
			insert(node);
		}

		return (Sequence) node;
	}

	/**
	 * Sets the invocation identifier of this invocation request. This number
	 * has been associated with the invocation, so that a consumer can detect
	 * whether a received {@link GlowInvocationResult} is the result he expects
	 * or the result of another consumer's invocation request.
	 * 
	 * @param id
	 *            The numeric identifier of this invocation.
	 */
	public void setInvocationId(int id) {
		replace(new Leaf(GlowTags.InvocationResult.INVOCATIONID, new Value(id)));
	}

	/**
	 * Sets a value indicating whether the invocation succeeded or not.
	 * 
	 * @param success
	 *            A value indicating whether the invocation succeeded.
	 */
	public void setSuccess(boolean success) {
		replace(new Leaf(GlowTags.InvocationResult.SUCCESS, new Value(success)));
	}

	/**
	 * Sets a collection containing the return values of the invocation. The
	 * order must match the order described by the {@link GlowFunction}.
	 * 
	 * @param it
	 *            The {@link Iterator} containing the return values.
	 * @throws NullPointerException
	 *             Thrown if {@link it} is <i>null</i>.
	 */
	public void setTypedResult(Iterator<Value> it) throws NullPointerException {
		Assert.AssertNotNull(it, "it");

		final Sequence result = this.result(true);

		if (result != null) {
			while (it.hasNext()) {
				result.insert(new Leaf(GlowTags.ELEMENT_DEFAULT, it.next()));
			}
		}
	}

	/**
	 * Gets a value indicating whether the invocation succeeded or not.
	 * 
	 * @return A value indicating whether the invocation succeded or not. Or, if
	 *         this property has not been specified, <i>null</i> is being
	 *         returned.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Boolean success() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.InvocationResult.SUCCESS);

		if (leaf != null)
			return leaf.value().toBoolean();
		else
			return null;
	}

	/**
	 * Gets an {@link Iterable} containing all return values.
	 * 
	 * @return An {@link Iterable} which contains all return values.
	 */
	public Iterable<Value> typedResult() {
		final Sequence result = this.result(false);
		final ArrayList<Value> typedResult = new ArrayList<Value>();

		if (result != null) {
			final Iterator<Node> it = result.iterator();

			while (it.hasNext()) {
				final Leaf leaf = (Leaf) it.next();

				if (leaf != null) {
					typedResult.add(leaf.value());
				}
			}
		}

		return typedResult;
	}
}
