package libember.glow;

import java.util.Collection;
import java.util.NoSuchElementException;

import libember.ber.Oid;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class represents a connection of a {@link GlowMatrix}. The
 * {@link GlowConnection} is used to report crosspoints or to issue a connect or
 * disconnect request.
 */
public final class GlowConnection extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowConnection} class
	 * 
	 * @param tag
	 *            The application tag.
	 */
	GlowConnection(Tag tag) {
		super(InsertMode.DEFAULT, GlowType.CONNECTION, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowConnection} class.
	 * 
	 * @param target
	 *            The index of the target this connection references.
	 */
	public GlowConnection(int target) {
		super(InsertMode.DEFAULT, GlowType.CONNECTION);

		insert(new Leaf(GlowTags.Connection.TARGET, new Value(target)));
	}

	/**
	 * Initializes a new instance of the {@link GlowConnection} class.
	 * 
	 * @param target
	 *            The index of the target this connection references.
	 * @param tag
	 *            The application tag.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	public GlowConnection(int target, Tag tag) throws NullPointerException {
		super(InsertMode.DEFAULT, GlowType.CONNECTION, tag);

		Assert.AssertNotNull(tag, "tag");

		insert(new Leaf(GlowTags.Connection.TARGET, new Value(target)));
	}

	/**
	 * Gets the {@link ConnectionDisposition}, if available.
	 * 
	 * @return The {@link ConnectionDisposition} or <i>null</i>, if the property
	 *         is not set.
	 * @throws NoSuchElementException
	 *             Thrown if an invalid value is specified which does not match
	 *             an instance of {@link ConnectionOperation}.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public ConnectionDisposition disposition() throws NoSuchElementException,
			UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.Connection.DISPOSITION);

		if (leaf != null) {
			return ConnectionDisposition.valueOf(leaf.value().toInt());
		} else {
			return null;
		}
	}

	/**
	 * Gets the {@link ConnectionOperation}, if available.
	 * 
	 * @return The {@link ConnectionOperation} or <i>null</i>, if the property
	 *         is not set.
	 * @throws NoSuchElementException
	 *             Thrown if an invalid value is specified which does not match
	 *             an instance of {@link ConnectionOperation}.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public ConnectionOperation operation() throws NoSuchElementException,
			UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.Connection.OPERATION);

		if (leaf != null) {
			return ConnectionOperation.valueOf(leaf.value().toInt());
		} else {
			return null;
		}
	}

	/**
	 * Sets the {@link ConnectionDisposition}, which indicates the notification
	 * type of this connection.
	 * 
	 * @param disposition
	 *            The {@link ConnectionDisposition}.
	 * @throws NullPointerException
	 *             Thrown if {@link disposition} is <i>null</i>.
	 */
	public void setDisposition(ConnectionDisposition disposition)
			throws NullPointerException {
		Assert.AssertNotNull(disposition, "disposition");

		replace(new Leaf(GlowTags.Connection.DISPOSITION, new Value(
				disposition.value())));
	}

	/**
	 * Sets the {@link ConnectionOperation}, which indicates the purpose of this
	 * instance.
	 * 
	 * @param operation
	 *            The {@link ConnectionOperation}.
	 * @throws NullPointerException
	 *             Thrown if {@link operation} is <i>null</i>.
	 */
	public void setOperation(ConnectionOperation operation)
			throws NullPointerException {
		Assert.AssertNotNull(operation, "operation");

		replace(new Leaf(GlowTags.Connection.OPERATION, new Value(
				operation.value())));
	}

	/**
	 * Sets the sources of this connection.
	 * 
	 * @param sources
	 *            The indices of the sources.
	 * @throws NullPointerException
	 *             Thrown if {@link sources} is <i>null</i>.
	 */
	public void setSources(Collection<Integer> sources)
			throws NullPointerException {
		Assert.AssertNotNull(sources, "sources");

		replace(new Leaf(GlowTags.Connection.SOURCES, new Value(
				new Oid(sources))));
	}

	/**
	 * Gets the sources of this instance.
	 * 
	 * @return The collection of sources. If the sources are not specified,
	 *         <i>null</i> is returned.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Iterable<Integer> sources() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.Connection.SOURCES);

		if (leaf != null) {
			return leaf.value().toOid();
		} else {
			return null;
		}
	}

	/**
	 * Gets the target of this instance. If no target is specified, <i>null</i>
	 * is returned.
	 * 
	 * @return The target index or <i>null</i>, if no target is specified.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer target() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.Connection.TARGET);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}
}
