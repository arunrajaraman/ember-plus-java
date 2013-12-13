/**
 * 
 */
package libember.glow;

import java.util.Iterator;
import java.util.Vector;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.dom.Node;
import libember.dom.Sequence;
import libember.util.Assert;

/**
 * This class represents the invocation request of a function. It contains the
 * identifier of the single invocation and the arguments to pass.
 */
public final class GlowInvocation extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowInvocation} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	GlowInvocation(Tag tag) throws NullPointerException {
		super(InsertMode.SORTED, GlowType.INVOCATION, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowInvocation} class.
	 */
	public GlowInvocation() {
		super(InsertMode.SORTED, GlowType.INVOCATION,
				GlowTags.Command.INVOCATION);
	}

	/**
	 * Gets a {@link Sequence} which contains the argument values of this
	 * invocation request.
	 * 
	 * @return The {@link Sequence} containing the argument values or
	 *         <i>null</i>, if the container does not exist.
	 */
	public Sequence arguments() {
		return arguments(false);
	}

	/**
	 * Gets a {@link Sequence} which contains the argument values of this
	 * invocation request.
	 * 
	 * @param create
	 *            If <i>true</i>, the collection will be created if it does not
	 *            already exist.
	 * @return The {@link Sequence} containing the argument values or
	 *         <i>null</i>, if {@link create} is <i>false</i> and the container
	 *         does not exist.
	 */
	public Sequence arguments(boolean create) {
		final Node node = find(GlowTags.Invocation.ARGUMENTS);
		Sequence container = null;

		if (node != null) {
			container = (Sequence) node;
		} else if (create) {
			container = new Sequence(GlowTags.Invocation.ARGUMENTS);
			insert(container);
		}

		return container;
	}

	/**
	 * Gets the invocation identifier of this request, if available.
	 * 
	 * @return The invocation identifier or <i>null</i>, if the property is not
	 *         set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the property is of a different type.
	 */
	public Integer invocationId() throws UnsupportedOperationException {
		final Leaf leaf = findType(GlowTags.Invocation.INVOCATIONID);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}

	/**
	 * Sets the arguments for this invocation. The order must match the argument
	 * description provided by the {@link GlowFunction} that is being addressed
	 * with this request.
	 * 
	 * @param it
	 *            An {@link Iterator} containing the argument values.
	 * @throws NullPointerException
	 *             Thrown if {@link it} is <i>null</i>.
	 */
	public void setArguments(Iterator<Value> it) throws NullPointerException {
		Assert.AssertNotNull(it, "it");

		final Sequence container = arguments(true);

		if (container != null) {
			container.clear();

			while (it.hasNext()) {
				final Value value = it.next();

				container.insert(new Leaf(GlowTags.ELEMENT_DEFAULT, value));
			}
		}
	}

	/**
	 * Sets the invocation identifier of this invocation request. This number is
	 * associated with the invocation, so that a consumer can detect whether a
	 * received {@link GlowInvocationResult} is the result he expects or the
	 * result of another consumer's invocation request.
	 * 
	 * @param id
	 *            The numeric identifier of this invocation.
	 */
	public void setInvocationId(int id) {
		replace(new Leaf(GlowTags.Invocation.INVOCATIONID, new Value(id)));
	}

	/**
	 * Gets an {@link Iterator} which contains the concrete argument values of
	 * type {@link Value}.
	 * 
	 * @return An {@link Iterator} containing the argument values.
	 */
	public Iterable<Value> typedArguments() {
		final Sequence arguments = this.arguments(false);
		final Vector<Value> result = new Vector<Value>();

		if (arguments != null) {
			final Iterator<Node> it = arguments.iterator();

			while (it.hasNext()) {
				final Leaf leaf = (Leaf) it.next();

				if (leaf != null) {
					result.add(leaf.value());
				}
			}
		}

		return result;
	}
}
