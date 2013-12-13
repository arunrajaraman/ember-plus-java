package libember.glow;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import libember.ber.Class;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Node;
import libember.util.Assert;
import libember.util.Pair;

/**
 * This class represents a parameter within an Ember+ tree.
 */
public class GlowParameterBase extends GlowContentElement {
	private final Tag childrenTag;

	/**
	 * Initializes a new instance of the {@link GlowParameterBase} class.
	 * 
	 * @param type
	 *            The {@link GlowType} of this parameter. Either
	 *            {@link GlowType.PARAMETER} or
	 *            {@link GlowType.QUALIFIEDPARAMETER}.
	 * @param tag
	 *            The application tag of this parameter.
	 * @param contentTag
	 *            The tag of the contents set to create.
	 * @param childrenTag
	 *            The tag of the children sequence to create.
	 * @throws NullPointerException
	 *             Thrown if any of the parameters is <i>null</i>.
	 */
	protected GlowParameterBase(GlowType type, Tag tag, Tag contentTag,
			Tag childrenTag) throws NullPointerException {
		super(InsertMode.SORTED, type, tag, contentTag);

		Assert.AssertNotNull(type, "type");

		this.childrenTag = childrenTag;
	}

	/**
	 * Gets the {@link Access} of this parameter.
	 * 
	 * @return The {@link Access} of this parameter or <i>null</i>, if the
	 *         property does not exist.
	 */
	public Access access() {
		final Value value = getContentValue(GlowTags.ParameterContents.ACCESS);

		if (value != null) {
			return Access.valueOf(value.toInt(0));
		} else {
			return null;
		}
	}

	/**
	 * Gets a {@link GlowElementCollection} that contains the children of this
	 * node.
	 * 
	 * @param create
	 *            If set to <i>true</i>, the children sequence will be created
	 *            if it doesn't already exist.
	 * @return The element collection that contains the children of this node.
	 *         If the sequence doesn't exist and the create flag is set to
	 *         false, null is being returned.
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
	 * Tests whether the specified {@link ParameterProperty} is stored within
	 * this parameter's content set.
	 * 
	 * @param property
	 *            The property to lookup.
	 * @return <i>true</i> if the property exists, otherwise <i>false</i>.
	 */
	public boolean contains(ParameterProperty property) {
		final Tag tag = new Tag(Class.CONTEXT, property.value());
		final boolean result = hasContent(tag);
		return result;
	}

	/**
	 * Gets the parameter's default value.
	 * 
	 * @return The parameter's default value or <i>null</i>, if the property
	 *         does not exist.
	 */
	public Value defaultValue() {
		return getContentValue(GlowTags.ParameterContents.DEFAULT);
	}

	/**
	 * Gets the parameter's description string.
	 * 
	 * @return The parameter's description string or <i>null</i>, if the
	 *         property does not exist.
	 * @throws NullPointerException
	 *             Thrown if the value is of a different type.
	 */
	public String description() {
		final Value value = getContentValue(GlowTags.ParameterContents.DESCRIPTION);

		if (value != null)
			return value.toUTF8String();
		else
			return null;
	}

	/**
	 * Gets a {@link Collection} containing the enumeration entries of this
	 * parameter.
	 * 
	 * @return A {@link Collection} containing the enumeration strings or
	 *         <i>null</i>, if the property does not exist.
	 */
	public Collection<String> enumeration() {
		final Value enumerationString = getContentValue(GlowTags.ParameterContents.ENUMERATION);

		if (enumerationString != null) {
			final String[] entries = enumerationString.toUTF8String().split(
					"\n");
			final Vector<String> enumeration = new Vector<String>(
					entries.length, 1);

			for (int i = 0; i < entries.length; ++i) {
				enumeration.add(entries[i]);
			}

			return enumeration;
		} else {
			return null;
		}
	}

	/**
	 * Gets a {@link Collection} containing the enumeration map of this
	 * parameter.
	 * 
	 * @return A {@link Collection} containing the enumeration map of this
	 *         parameter or <i>null</i>, if the property does not exist.
	 */
	public Collection<Pair<String, Integer>> enumMap() {
		final GlowStringIntegerCollection collection = (GlowStringIntegerCollection) getContent(GlowTags.ParameterContents.ENUM_MAP);

		if (collection != null) {
			final Vector<Pair<String, Integer>> enumMap = new Vector<Pair<String, Integer>>();

			if (collection != null) {
				final Iterator<GlowStringIntegerPair> pairs = collection
						.pairs().iterator();

				while (pairs.hasNext()) {
					final GlowStringIntegerPair pair = pairs.next();
					enumMap.add(new Pair<String, Integer>(pair.name(), pair
							.value()));
				}
			}

			return enumMap;
		} else {
			return null;
		}
	}

	/**
	 * Gets the factor of this parameter.
	 * 
	 * @return The factor of this parameter or <i>null</i>, if the property does
	 *         not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer factor() throws UnsupportedOperationException {
		final Value value = getContentValue(GlowTags.ParameterContents.FACTOR);

		if (value != null)
			return value.toInt();
		else
			return null;
	}

	/**
	 * Gets the format string of this parameter.
	 * 
	 * @return The format string or <i>null</i>, if the property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String format() throws UnsupportedOperationException {
		final Value value = getContentValue(GlowTags.ParameterContents.FORMAT);

		if (value != null)
			return value.toUTF8String();
		else
			return null;
	}

	/**
	 * Gets the {@link Formula} of this parameter.
	 * 
	 * @return The {@link Formula} or <i>null</i>, if the property does not
	 *         exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Formula formula() throws UnsupportedOperationException {
		final Value value = getContentValue(GlowTags.ParameterContents.FORMULA);

		if (value != null) {
			final String[] terms = value.toUTF8String().split("\n");

			if (terms.length == 2) {
				return new Formula(terms[0], terms[1]);
			} else if (terms.length == 1) {
				return new Formula(terms[0], terms[0]);
			} else {
				return new Formula("$", "$");
			}
		} else {
			return null;
		}
	}

	/**
	 * Gets the identifier string of this parameter.
	 * 
	 * @return The identifier string or <i>null</i>, if the property does not
	 *         exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String identifier() throws UnsupportedOperationException {
		final Value value = getContentValue(GlowTags.ParameterContents.IDENTIFIER);

		if (value != null)
			return value.toUTF8String();
		else
			return null;
	}

	/**
	 * Gets the online state of this parameter.
	 * 
	 * @return The online state of this parameter or <i>null</i>, if the
	 *         property does not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Boolean isOnline() {
		final Value value = getContentValue(GlowTags.ParameterContents.IS_ONLINE);

		if (value != null)
			return value.toBoolean();
		else
			return null;
	}

	/**
	 * Gets the maximum value of this parameter.
	 * 
	 * @return The maximum value of this parameter or <i>null</i>, if the
	 *         property does not exist.
	 */
	public Value maximum() {
		return getContentValue(GlowTags.ParameterContents.MAXIMUM);
	}

	/**
	 * Gets the minimum value of this parameter.
	 * 
	 * @return The minimum value of this parameter or <i>null</i>, if the
	 *         property does not exist.
	 */
	public Value minimum() {
		return getContentValue(GlowTags.ParameterContents.MINIMUM);
	}

	/**
	 * Specifies the {@link Access} of this parameter.
	 * 
	 * @param access
	 *            The {@link Access} of this parameter.
	 * @throws NullPointerException
	 *             Thrown if {@link access} is <i>null</i>.
	 */
	public void setAccess(Access access) throws NullPointerException {
		Assert.AssertNotNull(access, "access");

		setContent(GlowTags.ParameterContents.ACCESS, access.value());
	}

	/**
	 * Sets the new default value for this parameter.
	 * 
	 * @param value
	 *            The new parameter default value.
	 */
	public void setDefault(boolean value) {
		setContent(GlowTags.ParameterContents.DEFAULT, value);
	}

	/**
	 * Sets the new default value for this parameter.
	 * 
	 * @param value
	 *            The new parameter default value.
	 */
	public void setDefault(double value) {
		setContent(GlowTags.ParameterContents.DEFAULT, value);
	}

	/**
	 * Sets the new default value for this parameter.
	 * 
	 * @param value
	 *            The new parameter default value.
	 */
	public void setDefault(long value) {
		setContent(GlowTags.ParameterContents.DEFAULT, value);
	}

	/**
	 * Sets the new default value for this parameter.
	 * 
	 * @param value
	 *            The new parameter default value.
	 * @throws NullPointerException
	 *             Thrown if {@link value} is <i>null</i>.
	 */
	public void setDefault(String value) throws NullPointerException {
		setContent(GlowTags.ParameterContents.DEFAULT, value);
	}

	/**
	 * Sets the description string. User interfaces can use this property as
	 * display string.
	 * 
	 * @param description
	 *            The new description string.
	 * @throws NullPointerException
	 *             Thrown if {@link description} is <i>null</i>.
	 */
	public void setDescription(String description) throws NullPointerException {
		Assert.AssertNotNull(description, "description");

		setContent(GlowTags.ParameterContents.DESCRIPTION, description);
	}

	/**
	 * Sets the legacy enumeration, which is a simple collection of strings. To
	 * modify a parameter providing an enumeration, the index of the enumeration
	 * entry must be transmitted as value.
	 * 
	 * @param values
	 *            The {@link Collection} of enumeration values.
	 * @throws NullPointerException
	 *             Thrown if {@link values} is <i>null</i>.
	 */
	public void setEnumeration(Collection<String> values)
			throws NullPointerException {
		Assert.AssertNotNull(values, "values");

		final String value = libember.util.StringUtil.join("\n", values);
		setContent(GlowTags.ParameterContents.ENUMERATION, value);
	}

	/**
	 * Sets the legacy enumeration, which is a simple collection of strings. To
	 * modify a parameter providing an enumeration, the index of the enumeration
	 * entry must be transmitted as value.
	 * 
	 * @param values
	 *            The {@link Iterator} containing the enumeration values.
	 * @throws NullPointerException
	 *             Thrown if {@link values} is <i>null</i>.
	 */
	public void setEnumeration(Iterator<String> values) {
		final String value = libember.util.StringUtil.join("\n", values);
		setContent(GlowTags.ParameterContents.ENUMERATION, value);
	}

	/**
	 * Sets the enumeration map. The map is a collection of string and integer
	 * pairs, where the string specifies the display text and the integer the
	 * numeric representation of that value. When a map is specified, a legacy
	 * enumeration must not be used.
	 * 
	 * @param map
	 *            The enumeration map.
	 * @throws NullPointerException
	 *             Thrown if {@link map} is <i>null</i>.
	 */
	public void setEnumMap(Collection<libember.util.Pair<String, Integer>> map)
			throws NullPointerException {
		Assert.AssertNotNull(map, "map");

		final GlowStringIntegerCollection collection = new GlowStringIntegerCollection(
				GlowTags.ParameterContents.ENUM_MAP);

		for (Iterator<Pair<String, Integer>> it = map.iterator(); it.hasNext(); /* Nothing */) {
			final libember.util.Pair<String, Integer> pair = it.next();
			collection.insert(pair.key(), pair.value().intValue());
		}

		setContent(collection);
	}

	/**
	 * Sets the factor for this parameter.
	 * 
	 * @param value
	 *            The new factor.
	 */
	public void setFactor(int value) {
		setContent(GlowTags.ParameterContents.FACTOR, value);
	}

	/**
	 * Sets the format string for this parameter. Currently, this must be a
	 * C-Style format string.
	 * 
	 * @param format
	 *            The format string.
	 * @throws NullPointerException
	 *             Thrown if {@link format} is <i>null</i>.
	 */
	public void setFormat(String format) throws NullPointerException {
		Assert.AssertNotNull(format, "format");

		setContent(GlowTags.ParameterContents.FORMAT, format);
	}

	/**
	 * Sets the formula for this parameter. It is not allowed to specify
	 * <i>null</i> for any of the formulas. Instead, if a term is unused, pass
	 * an empty string.
	 * 
	 * @param providerToConsumer
	 *            The term to convert the provider value into a consumer value.
	 * @param consumerToProvider
	 *            The term to convert the consumer value back into the provider
	 *            value.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	public void setFormula(String providerToConsumer, String consumerToProvider)
			throws NullPointerException {
		Assert.AssertNotNull(providerToConsumer, "providerToConsumer");
		Assert.AssertNotNull(consumerToProvider, "consumerToProvider");

		final String value = providerToConsumer + "\n" + consumerToProvider;
		setContent(GlowTags.ParameterContents.FORMULA, value);
	}

	/**
	 * Sets the identifier string of this parameter. This string must not start
	 * with a digit and must not contain the slash character.
	 * 
	 * @param identifier
	 *            The identifier string.
	 * @throws NullPointerException
	 *             Thrown if {@link identifier} is <i>null</i>.
	 */
	public void setIdentifier(String identifier) throws NullPointerException {
		Assert.AssertNotNull(identifier, "identifier");

		setContent(GlowTags.ParameterContents.IDENTIFIER, identifier);
	}

	/**
	 * Specifies the online state of this parameter.
	 * 
	 * @param isOnline
	 *            The new online state.
	 */
	public void setIsOnline(boolean isOnline) {
		setContent(GlowTags.ParameterContents.IS_ONLINE, isOnline);
	}

	/**
	 * Sets the maximum value for this parameter.
	 * 
	 * @param value
	 *            The new maximum value.
	 */
	public void setMaximum(double value) {
		setContent(GlowTags.ParameterContents.MAXIMUM, value);
	}

	/**
	 * Sets the maximum value for this parameter.
	 * 
	 * @param value
	 *            The new maximum value.
	 */
	public void setMaximum(long value) {
		setContent(GlowTags.ParameterContents.MAXIMUM, value);
	}

	/**
	 * Sets the minimum value for this parameter.
	 * 
	 * @param value
	 *            The new minimum value.
	 */
	public void setMinimum(double value) {
		setContent(GlowTags.ParameterContents.MINIMUM, value);
	}

	/**
	 * Sets the minimum value for this parameter.
	 * 
	 * @param value
	 *            The new minimum value.
	 */
	public void setMinimum(long value) {
		setContent(GlowTags.ParameterContents.MINIMUM, value);
	}

	/**
	 * Sets the step for this parameter.
	 * 
	 * @param value
	 *            The new step.
	 */
	@Deprecated
	public void setStep(int value) {
		setContent(GlowTags.ParameterContents.STEP, value);
	}

	/**
	 * Sets the stream descriptor. This property is only used when one stream
	 * identifier is shared across several parameters. In that case, the values
	 * of several parameters are transmitted in a single byte array. The
	 * descriptor of each parameter specifies the encoding and the location
	 * within that array.
	 * 
	 * @param format
	 *            The {@link StreamFormat} within the encoded byte array.
	 * @param offset
	 *            The offset within the encoded byte array.
	 */
	public void setStreamDescriptor(StreamFormat format, int offset) {
		final GlowStreamDescriptor descriptor = new GlowStreamDescriptor(
				GlowTags.ParameterContents.STREAM_DESCRIPTOR, format, offset);
		setContent(descriptor);
	}

	/**
	 * Sets the stream identifier for this parameter. This property is only used
	 * when the parameter changes its value frequently and an explicit
	 * subscription is required in order to receive value notifications. Also,
	 * parameters providing a stream identifier notify their value updates
	 * within a {@link GlowStreamCollection} instead of the common way.
	 * 
	 * @param value
	 *            The stream identifier.
	 */
	public void setStreamIdentifier(int value) {
		setContent(GlowTags.ParameterContents.STREAM_IDENTIFIER, value);
	}

	/**
	 * Specifies the {@link ParameterType} of this parameter. This property is
	 * usually not required and only used as type hint, if no value is
	 * available. Only if the parameter is used as a trigger,
	 * {@link ParameterType.TRIGGER} must be specified.
	 * 
	 * @param type
	 *            The {@link ParameterType} of this parameter.
	 * @throws NullPointerException
	 *             Thrown if {@link type} is <i>null</i>.
	 */
	public void setType(ParameterType type) throws NullPointerException {
		Assert.AssertNotNull(type, "type");

		setContent(GlowTags.ParameterContents.TYPE, type.value());
	}

	/**
	 * Sets the new value for this parameter.
	 * 
	 * @param value
	 *            The new parameter value.
	 */
	public void setValue(boolean value) {
		setContent(GlowTags.ParameterContents.VALUE, value);
	}

	/**
	 * Sets the new value for this parameter.
	 * 
	 * @param value
	 *            The new parameter value.
	 */
	public void setValue(double value) {
		setContent(GlowTags.ParameterContents.VALUE, value);
	}

	/**
	 * Sets the new value for this parameter.
	 * 
	 * @param value
	 *            The new parameter value.
	 */
	public void setValue(long value) {
		setContent(GlowTags.ParameterContents.VALUE, value);
	}

	/**
	 * Sets the new value for this parameter.
	 * 
	 * @param value
	 *            The new parameter value.
	 * @throws NullPointerException
	 *             Thrown if {@link value} is <i>null</i>.
	 */
	public void setValue(String value) throws NullPointerException {
		Assert.AssertNotNull(value, "value");

		setContent(GlowTags.ParameterContents.VALUE, value);
	}

	/**
	 * gets the step of this parameter.
	 * 
	 * @return The step of this parameter or <i>null</i>, if the property does
	 *         not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	@Deprecated
	public Integer step() throws UnsupportedOperationException {
		final Value value = getContentValue(GlowTags.ParameterContents.STEP);

		if (value != null)
			return value.toInt();
		else
			return null;
	}

	/**
	 * Gets the {@link GlowStreamDescriptor} of this parameter.
	 * 
	 * @return The {@link GlowStreamDescriptor} or <i>null</i>, if the property
	 *         does not exist.
	 */
	public GlowStreamDescriptor streamDescriptor() {
		return (GlowStreamDescriptor) getContent(GlowTags.ParameterContents.STREAM_DESCRIPTOR);
	}

	/**
	 * Gets the stream identifier of this parameter.
	 * 
	 * @return The stream identifier or <i>null</i>, if the property does not
	 *         exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer streamIdentifier() throws UnsupportedOperationException {
		final Value value = getContentValue(GlowTags.ParameterContents.STREAM_IDENTIFIER);

		if (value != null)
			return value.toInt();
		else
			return null;
	}

	/**
	 * Gets the {@link ParameterType} of this parameter.
	 * 
	 * @return The type information of this parameter.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public ParameterType type() throws UnsupportedOperationException {
		final Value value = getContentValue(GlowTags.ParameterContents.TYPE);

		if (value != null)
			return ParameterType.valueOf(value.toInt());
		else
			return null;
	}

	/**
	 * Gets the {@link Value} of this parameter.
	 * 
	 * @return The {@link Value} or <i>null</i>, if the property does not exist.
	 */
	public Value value() {
		return getContentValue(GlowTags.ParameterContents.VALUE);
	}
}
