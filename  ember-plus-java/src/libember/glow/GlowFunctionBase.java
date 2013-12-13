package libember.glow;

import java.util.Iterator;
import java.util.Vector;

import libember.ber.Class;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Node;
import libember.dom.Sequence;
import libember.util.Assert;

/**
 * Base class to describe an Ember+ function. A function consists of an
 * identifier, an optional description, arguments and return types. This class
 * provides the description of a function. To invoke a function, use the
 * {@link GlowCommand} with the command type set to {@link CommandType.INVOKE}
 * and append it to an instance of this class as child node.
 */
public class GlowFunctionBase extends GlowContentElement {
	private final Tag childrenTag;

	/**
	 * Initializes a new instance of the {@link GlowFunctionBase} class.
	 * 
	 * @param type
	 *            The concrete {@link GlowType} of this instance, either
	 *            {@link GlowType.FUNCTION} or
	 *            {@link GlowType.QUALIFIEDFUNCTION}.
	 * @param tag
	 *            The application tag of this node.
	 * @param contentsTag
	 *            The {@link Tag} identifying the contents of the function.
	 * @param childrenTag
	 *            The {@link Tag} identifying the container of the child nodes.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	protected GlowFunctionBase(GlowType type, Tag tag, Tag contentsTag,
			Tag childrenTag) throws NullPointerException {
		super(InsertMode.DEFAULT, type, tag, contentsTag);

		Assert.AssertNotNull(childrenTag, "childrenTag");

		this.childrenTag = childrenTag;
	}

	/**
	 * Gets the {@link Sequence} containing the arguments of this instance.
	 * 
	 * @return The {@link Sequence} containing the arguments or <i>null</i> if
	 *         the container does not exist.
	 */
	public Sequence arguments() {
		return arguments(false);
	}

	/**
	 * Gets the {@link Sequence} containing the arguments of this instance.
	 * 
	 * @param create
	 *            If set to <i>true</i> and the arguments container does not yet
	 *            exist, it will be created.
	 * @return The {@link Sequence} containing the arguments or
	 *         <code>null</code>, if the container does not exist and
	 *         {@link create} is set to <i>false</i>.
	 */
	public Sequence arguments(boolean create) {
		final Node container = this
				.getContent(GlowTags.FunctionContents.ARGUMENTS);

		if (container != null) {
			return (Sequence) container;
		} else if (create) {
			final Sequence arguments = new Sequence(
					GlowTags.FunctionContents.ARGUMENTS);

			this.setContent(arguments);

			return arguments;
		} else {
			return null;
		}
	}

	/**
	 * Gets a {@link GlowElementCollection} which contains the children of this
	 * function. Usually, a function has no children exception the invocation
	 * command.
	 * 
	 * @return The container containing the children of this function.
	 */
	public GlowElementCollection children() {
		return children(false);
	}

	/**
	 * Gets a {@link GlowElementCollection} which contains the children of this
	 * function. Usually, a function has no children except the invocation
	 * command.
	 * 
	 * @param create
	 *            If <i>true</i>, the children container will be created if it
	 *            does not yet exist.
	 * @return The container containing the children of this function.
	 */
	public GlowElementCollection children(boolean create) {
		final Node container = this.find(this.childrenTag);

		if (container != null) {
			return (GlowElementCollection) container;
		} else if (create) {
			final GlowElementCollection children = new GlowElementCollection(
					this.childrenTag);
			this.insert(children);

			return children;
		} else {
			return null;
		}
	}

	/**
	 * Tests is the specified property exists within this instance.
	 * 
	 * @param property
	 *            The {@link FunctionProperty} to lookup.
	 * @return <i>true</i>, if the property is available. Otherwise, this method
	 *         return <i>false</i>.
	 * @throws NullPointerException
	 *             Thrown if {@link property} is <i>null</i>.
	 */
	public boolean contains(FunctionProperty property)
			throws NullPointerException {
		Assert.AssertNotNull(property, "property");

		final Tag tag = new Tag(Class.CONTEXT, property.value());
		final boolean result = hasContent(tag);

		return result;
	}

	/**
	 * Gets the description string of this function or <i>null</i>, if the
	 * property is not set.
	 * 
	 * @return The description string or <i>null</i>.
	 * @throws UnsupportedOperationException
	 *             Thrown if the property is of a different type.
	 */
	public String description() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.FunctionContents.DESCRIPTION);

		if (value != null) {
			return value.toUTF8String();
		} else {
			return null;
		}
	}

	/**
	 * Gets the identifier string of this function or <i>null</i>, if the
	 * property is not set.
	 * 
	 * @return The identifier string or <i>null</i>.
	 * @throws UnsupportedOperationException
	 *             Thrown if the property is of a different type.
	 */
	public String identifier() {
		final Value value = this
				.getContentValue(GlowTags.FunctionContents.IDENTIFIER);

		if (value != null) {
			return value.toUTF8String();
		} else {
			return null;
		}
	}

	/**
	 * Gets the {@link Sequence} containing the return types of this instance.
	 * If the container does not exist, this method returns <i>null</i>.
	 * 
	 * @return The {@link Sequence} containing the return types or <i>null</i>,
	 *         if it does not yet exist.
	 */
	public Sequence result() {
		return result(false);
	}

	/**
	 * Gets the {@link Sequence} containing the return types of this instance.
	 * 
	 * @param create
	 *            If <i>true</i>, the sequence will be created if it does not
	 *            yet exist.
	 * @return The {@link Sequence} containing the return types or <i>null</i>,
	 *         if {@link create} is <i>false</i> and the container does not yet
	 *         exist.
	 */
	public Sequence result(boolean create) {
		final Node container = this
				.getContent(GlowTags.FunctionContents.RESULT);

		if (container != null) {
			return (Sequence) container;
		} else if (create) {
			final Sequence result = new Sequence(
					GlowTags.FunctionContents.RESULT);

			this.setContent(result);

			return result;
		} else {
			return null;
		}
	}

	/**
	 * Sets the description string of this function. This string can be
	 * interpreted as optional display string. If available, an application may
	 * display this value instead of the identifier. This method must only be
	 * called once per instance.
	 * 
	 * @param description
	 *            The description string.
	 * @throws NullPointerException
	 *             Thrown if {@link description} is <i>null</i>.
	 */
	public void setDescription(String description) {
		setContent(GlowTags.FunctionContents.DESCRIPTION, description);
	}

	/**
	 * Sets the identifier string of this function. This method must only be
	 * called once per instance.
	 * 
	 * @param identifier
	 *            The string identifying this function.
	 * @throws NullPointerException
	 *             Thrown if {@link identifier} is <i>null</i>.
	 */
	public void setIdentifier(String identifier) throws NullPointerException {
		setContent(GlowTags.FunctionContents.IDENTIFIER, identifier);
	}

	/**
	 * Sets the argument descriptions for this function.
	 * 
	 * @param arguments
	 *            An {@link Iterator} containing the arguments to assign to this
	 *            instance.
	 * @throws NullPointerException
	 *             Thrown if {@link arguments} is <i>null</i>.
	 */
	public void setTypedArguments(Iterator<GlowTupleItemDescription> arguments)
			throws NullPointerException {
		Assert.AssertNotNull(arguments, "arguments");

		final Sequence container = this.arguments(true);

		if (container != null) {
			container.clear();

			while (arguments.hasNext()) {
				container.insert(arguments.next());
			}
		}
	}

	/**
	 * Sets the return type descriptions for this function.
	 * 
	 * @param result
	 *            An {@link Iterator} containing the descriptions for each
	 *            return value.
	 * @throws NullPointerException
	 *             Thrown if {@link result} is <i>null</i>.
	 */
	public void setTypedResult(Iterator<GlowTupleItemDescription> result)
			throws NullPointerException {
		Assert.AssertNotNull(result, "result");

		final Sequence container = this.result(true);

		if (container != null) {
			container.clear();

			while (result.hasNext()) {
				container.insert(result.next());
			}
		}
	}

	/**
	 * Gets an {@link Iterable} containing all arguments descriptions, where
	 * each argument is described by an instance of
	 * {@link GlowTupleItemDescription}. If the property is not present an empty
	 * collection is being returned.
	 * 
	 * @return An {@link Iterable} containing the typed argument descriptions.
	 */
	public Iterable<GlowTupleItemDescription> typedArguments() {
		final Sequence arguments = this.arguments(false);
		final Vector<GlowTupleItemDescription> result = new Vector<GlowTupleItemDescription>();

		if (arguments != null) {
			final Iterator<Node> it = arguments.iterator();

			while (it.hasNext()) {
				final GlowTupleItemDescription item = (GlowTupleItemDescription) it
						.next();

				if (item != null) {
					result.add(item);
				}
			}
		}

		return result;
	}

	/**
	 * Gets an {@link Iterable} containing the return value descriptions, where
	 * each return type is described by an instance of
	 * {@link GlowTupleItemDescription}. If the collection does not exist an
	 * empty {@link Iterable} will be returned.
	 * 
	 * @return An {@link Iterable} containing the return value descriptions.
	 */
	public Iterable<GlowTupleItemDescription> typedResult() {
		final Sequence resultContainer = this.result(false);
		final Vector<GlowTupleItemDescription> result = new Vector<GlowTupleItemDescription>();

		if (resultContainer != null) {
			final Iterator<Node> it = resultContainer.iterator();

			while (it.hasNext()) {
				final GlowTupleItemDescription item = (GlowTupleItemDescription) it
						.next();

				if (item != null) {
					result.add(item);
				}
			}
		}

		return result;
	}
}
