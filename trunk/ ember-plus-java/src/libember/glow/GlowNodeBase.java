package libember.glow;

import libember.ber.Class;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Node;
import libember.util.Assert;

/**
 * Base class for an Ember+ node. A node is used to project the infrastructure
 * of a device or to categorize a collection of subnodes or parameters.
 */
public class GlowNodeBase extends GlowContentElement {
	private final Tag childrenTag;

	/**
	 * Initializes a new instance of the {@link GlowNodeBase} class.
	 * 
	 * @param type
	 *            The concrete type of this instance, either
	 *            {@link GlowType.NODE} or {@link GlowType.QUALIFIEDNODE}.
	 * @param tag
	 *            The application tag of this instance..
	 * @param contentTag
	 *            This tag identifies the contents set.
	 * @param childrenTag
	 *            This tag identifies the children container.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	protected GlowNodeBase(GlowType type, Tag tag, Tag contentTag,
			Tag childrenTag) throws NullPointerException {
		super(InsertMode.SORTED, type, tag, contentTag);

		Assert.AssertNotNull(childrenTag, "childrenTag");

		this.childrenTag = childrenTag;
	}

	/**
	 * Gets a collection that contains the children of this node.
	 * 
	 * @param create
	 *            If set to <i>true</i>, the container will be created if it
	 *            doesn't already exist.
	 * @return The collection that contains the children of this node. If the
	 *         sequence doesn't exist and {@link create} is set to <i>false</i>,
	 *         <i>null</i> is being returned.
	 */
	public GlowElementCollection children(boolean create) {
		final Node node = find(childrenTag);

		if (node == null && create) {
			final GlowElementCollection collection = new GlowElementCollection(
					childrenTag);
			insert(collection);
			return collection;
		} else {
			return (GlowElementCollection) node;
		}
	}

	/**
	 * Tests whether a {@link NodeProperty} exists or not.
	 * 
	 * @param property
	 *            The {@link NodeProperty} to lookup.
	 * @return <i>true</i>, if the property exists. Otherwise, <i>false</i>.
	 */
	public boolean contains(NodeProperty property) {
		final Tag tag = new Tag(Class.CONTEXT, property.value());
		final boolean result = hasContent(tag);
		return result;
	}

	/**
	 * Gets the description string of this node.
	 * 
	 * @return The description string of this node or <i>null</i>, if the
	 *         property is not set..
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String description() throws UnsupportedOperationException {
		final libember.ber.Value value = getContentValue(GlowTags.NodeContents.DESCRIPTION);

		if (value != null)
			return value.toUTF8String();
		else
			return null;
	}

	/**
	 * Gets the identifier string of this node.
	 * 
	 * @return The identifier string of this node or <i>null</i>, if the
	 *         property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String identifier() throws UnsupportedOperationException {
		final libember.ber.Value value = getContentValue(GlowTags.NodeContents.IDENTIFIER);

		if (value != null)
			return value.toUTF8String();
		else
			return null;
	}

	/**
	 * Gets the string containing the schema identifiers of this node. The identifiers
	 * are separated with the linefeed character (0x0A, \n).
	 * @return The schema identifier or <i>null</i>, if the property is not set.
	 * @throws UnsupportedOperationException
	 * 			   Thrown if the value is of a different type.
	 */
	public String schemaIdentifiers() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.NodeContents.SCHEMAIDENTIFIERS);
		
		if (value != null) {
			return value.toUTF8String();
		} else {
			return null;
		}
	}

	/**
	 * Gets a value indicating whether the node is online or not.
	 * 
	 * @return <i>true</i>, if the node is online or <i>false</i> if the node is
	 *         offline. If this property is not set, this method returns
	 *         <i>null</i>.
	 */
	public Boolean isOnline() {
		final libember.ber.Value value = getContentValue(GlowTags.NodeContents.ISONLINE);

		if (value != null)
			return value.toBoolean();
		else
			return null;
	}

	/**
	 * Gets a value indicating whether this node is marked as root node.
	 * 
	 * @return <i>true</i>, if the node is the root node or <i>false</i>, if
	 *         not. If this property is not set, this method returns
	 *         <i>null</i>.
	 */
	public Boolean isRoot() {
		final libember.ber.Value value = getContentValue(GlowTags.NodeContents.ISROOT);

		if (value != null)
			return value.toBoolean();
		else
			return null;
	}

	/**
	 * Sets the description string of this node. This value can be interpreted
	 * as display string.
	 * 
	 * @param description
	 *            The description string to set.
	 * @throws NullPointerException
	 *             Thrown if {@link description} <i>null</i>.
	 */
	public void setDescription(String description) throws NullPointerException {
		Assert.AssertNotNull(description, "description");

		setContent(GlowTags.NodeContents.DESCRIPTION, description);
	}

	/**
	 * Sets the identifier string of this node. This value must not contain the
	 * slash character and must not start with a digit.
	 * 
	 * @param identifier
	 *            The identifier string to set.
	 * @throws NullPointerException
	 *             Thrown if {@link identifier} <i>null</i>.
	 */
	public void setIdentidier(String identifier) throws NullPointerException {
		Assert.AssertNotNull(identifier, "identifier");

		setContent(GlowTags.NodeContents.IDENTIFIER, identifier);
	}
	
	/**
	 * Sets the string containing the schema identifiers of this node. This value must not be <i>null</i>.
	 * @param identifiers The identifier string to set.
	 * @throws NullPointerException
	 * 			   Thrown if {@link identifiers} is <i>null</i>.
	 */
	public void setSchemaIdentifiers(String identifiers) throws NullPointerException {
		Assert.AssertNotNull(identifiers, "identifiers");
		
		setContent(GlowTags.NodeContents.SCHEMAIDENTIFIERS, identifiers);
	}

	/**
	 * Specifies whether this node is online or not. As long as this property is
	 * not specified, a consumer must assume that the node is online.
	 * 
	 * @param value
	 *            The value indicating whether this root is online or not.
	 */
	public void setIsOnline(boolean value) {
		setContent(GlowTags.NodeContents.ISONLINE, value);
	}

	/**
	 * Specifies whether this node is a root node or not.
	 * 
	 * @param value
	 *            The value indicating whether this node is a root node.
	 */
	public void setIsRoot(boolean value) {
		setContent(GlowTags.NodeContents.ISROOT, value);
	}
}
