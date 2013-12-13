package libember.glow;

import java.util.Iterator;

import libember.ber.Octets;
import libember.ber.Oid;
import libember.ber.Tag;
import libember.dom.Leaf;
import libember.dom.Node;
import libember.dom.Set;
import libember.util.Assert;

/**
 * Base class for types that contain a contents set, like {@link GlowParameter},
 * {@link GlowNode} and {@link GlowMatrix}.
 */
public class GlowContentElement extends GlowElement {
	private final Tag contentsTag;
	private Set contents;

	/**
	 * Initializes a new GlowContentElement.
	 * 
	 * @param mode
	 *            The insertion mode for this container.
	 * @param type
	 *            The glow type this element represents.
	 * @param tag
	 *            The application tag to initializes this instance with.
	 * @param contentsTag
	 *            The application tag for the contents set.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	protected GlowContentElement(InsertMode mode, GlowType type, Tag tag,
			Tag contentsTag) throws NullPointerException {
		super(mode, type, tag);

		Assert.AssertNotNull(contentsTag, "contentsTag");

		this.contentsTag = contentsTag;
	}

	/**
	 * Searches for a node with the passed tag within the content set.
	 * 
	 * @param tag
	 *            The tag to look for.
	 * @return The node with the passed tag or null, if the content set is null
	 *         or the node doesn't exist.
	 */
	protected Node getContent(Tag tag) {
		contents();

		if (contents != null) {
			for (Iterator<Node> i = contents.iterator(); i.hasNext();) {
				final Node node = i.next();
				if (node.applicationTag().equals(tag)) {
					return node;
				}
			}
		}

		return null;
	}

	/**
	 * Searches for a node with the passed tagged within the content set. If the
	 * node exists and is of type Leaf, its type-erased value will be returned.
	 * 
	 * @param tag
	 *            The application tag of the node to search.
	 * @return The value of the {@link Leaef} with the specified {@link tag} or
	 *         <i>null</i>, if the leaf does not exist.
	 */
	protected libember.ber.Value getContentValue(Tag tag) {
		final Leaf leaf = (Leaf) getContent(tag);
		return leaf != null ? leaf.value() : null;
	}

	/**
	 * Searches for a content element.
	 * 
	 * @param tag
	 *            The of the element to look for.
	 * @return <i>true</i> if the element exists, otherwise <i>false</i>.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected boolean hasContent(Tag tag) throws NullPointerException {
		contents();

		if (contents != null) {
			for (Iterator<Node> i = contents.iterator(); i.hasNext(); /* Nothing */) {
				final Node node = i.next();

				if (node.applicationTag().equals(tag)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Inserts a node into the contents set.
	 * 
	 * @param value
	 *            The node to insert.
	 */
	protected void setContent(Node value) {
		if (contents == null) {
			contents = new Set(contentsTag);
			insert(contents);
		}

		contents.insert(value);
	}

	/**
	 * Inserts an element to the content {@link Set} with a specified
	 * {@link Tag} and value.
	 * 
	 * @param tag
	 *            The application tag of the node to insert.
	 * @param value
	 *            The value to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected void setContent(Tag tag, boolean value)
			throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");

		setContent(new Leaf(tag, new libember.ber.Value(value)));
	}

	/**
	 * Inserts an element to the content {@link Set} with a specified
	 * {@link Tag} and value.
	 * 
	 * @param tag
	 *            The application tag of the node to insert.
	 * @param value
	 *            The value to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected void setContent(Tag tag, double value)
			throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");

		setContent(new Leaf(tag, new libember.ber.Value(value)));
	}

	/**
	 * Inserts an element to the content {@link Set} with a specified
	 * {@link Tag} and value.
	 * 
	 * @param tag
	 *            The application tag of the node to insert.
	 * @param value
	 *            The value to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected void setContent(Tag tag, float value) throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");

		setContent(new Leaf(tag, new libember.ber.Value(value)));
	}

	/**
	 * Inserts an element to the content {@link Set} with a specified
	 * {@link Tag} and value.
	 * 
	 * @param tag
	 *            The application tag of the node to insert.
	 * @param value
	 *            The value to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected void setContent(Tag tag, int value) throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");
		Assert.AssertNotNull(value, "value");

		setContent(new Leaf(tag, new libember.ber.Value(value)));
	}

	/**
	 * Inserts an element to the content {@link Set} with a specified
	 * {@link Tag} and value.
	 * 
	 * @param tag
	 *            The application tag of the node to insert.
	 * @param value
	 *            The value to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	protected void setContent(Tag tag, long value) throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");

		setContent(new Leaf(tag, new libember.ber.Value(value)));
	}

	/**
	 * Inserts an element to the content {@link Set} with a specified
	 * {@link Tag} and value.
	 * 
	 * @param tag
	 *            The application tag of the node to insert.
	 * @param value
	 *            The value to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} or {@link value} is <i>null</i>.
	 */
	protected void setContent(Tag tag, Octets value)
			throws NullPointerException {
		setContent(new Leaf(tag, new libember.ber.Value(value)));
	}

	/**
	 * Inserts an element to the content {@link Set} with a specified
	 * {@link Tag} and value.
	 * 
	 * @param tag
	 *            The application tag of the node to insert.
	 * @param value
	 *            The value to insert.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} or {@link value} is <i>null</i>.
	 */
	protected void setContent(Tag tag, Oid value) throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");
		Assert.AssertNotNull(value, "value");

		setContent(new Leaf(tag, new libember.ber.Value(value)));
	}

	/**
	 * Inserts an element to the content {@link Set} with a specified
	 * {@link Tag} and value.
	 * 
	 * @param tag
	 *            The application tag of the node to insert.
	 * @param value
	 *            The value to insert.
	 * @throws NullPointerException
	 *             Thrown if either {@link tag} or {@link value} is <i>null</i>.
	 */
	protected void setContent(Tag tag, String value)
			throws NullPointerException {
		Assert.AssertNotNull(tag, "tag");
		Assert.AssertNotNull(value, "value");

		setContent(new Leaf(tag, new libember.ber.Value(value)));
	}

	/**
	 * Gets the {@link Set} storing the content properties.
	 * 
	 * @return The contents set. If the {@link Set} does not yet exist, it will
	 *         be created.
	 */
	public Set contents() {
		if (contents == null) {
			contents = (Set) find(contentsTag);

			if (contents == null) {
				contents = new Set(contentsTag);
				insert(contents);
			}
		}

		return contents;
	}

	@SuppressWarnings("unchecked")
	public <T extends libember.dom.Node> T getContentType(Tag tag) {
		return (T) getContent(tag);
	}
}
