package libember.glow;

import java.util.ArrayList;
import java.util.Iterator;

import libember.ber.Class;
import libember.ber.Oid;
import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Node;
import libember.dom.Sequence;
import libember.util.Assert;

/**
 * This class represents a matrix within an Ember+ tree. A tree may contain
 * several matrices at any position. A matrix allows simple routing of sources
 * and targets, but may also associate parameters like labels or gains with
 * signals and even crosspoints.
 */
public class GlowMatrixBase extends GlowContentElement {
	private final Tag targetsTag;
	private final Tag sourcesTag;
	private final Tag connectionsTag;

	/**
	 * Initializes a new instance of the {@link GlowMatrixBase} class.
	 * 
	 * @param type
	 *            The concrete {@link GlowType} of this instance, either
	 *            {@link GlowType.MATRIX} or {@link GlowType.QUALIFIEDMATRIX}.
	 * @param tag
	 *            The application tag of this instance.
	 * @param contentsTag
	 *            This tag identifies the {@link Set} storing the contents of
	 *            this matrix.
	 * @param childrenTag
	 *            This tag identifies the {@link GlowElementCollection}
	 *            containing the children of this matrix.
	 * @param targetsTag
	 *            This tag identifies the target collection.
	 * @param sourcesTag
	 *            This tag identifies the source collection.
	 * @param connectionsTag
	 *            This tag identifies the connection collection.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	protected GlowMatrixBase(GlowType type, Tag tag, Tag contentsTag,
			Tag childrenTag, Tag targetsTag, Tag sourcesTag, Tag connectionsTag) {
		super(InsertMode.DEFAULT, type, tag, contentsTag, childrenTag);

		Assert.AssertNotNull(targetsTag, "targetsTag");
		Assert.AssertNotNull(sourcesTag, "sourcesTag");
		Assert.AssertNotNull(connectionsTag, "connectionsTag");

		this.targetsTag = targetsTag;
		this.sourcesTag = sourcesTag;
		this.connectionsTag = connectionsTag;
	}

	/**
	 * Gets the {@link MatrixAddressingMode} of this matrix.
	 * 
	 * @return The address mode or <i>null</i>, if the property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public MatrixAddressingMode addressingMode()
			throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.ADDRESSINGMODE);

		if (value != null) {
			return MatrixAddressingMode.valueOf(value.toInt());
		} else {
			return null;
		}
	}

	/**
	 * Gets a {@link Sequence} containing the connections of this matrix. Prefer
	 * using the {@link typedConnections} method instead to assure type safety.
	 * 
	 * @return A {@link Sequence} containing the connections of this matrix or
	 *         <i>null</i>, if the container does not exist.
	 */
	public Sequence connections() {
		return connections(false);
	}

	/**
	 * Gets a {@link Sequence} containing the connections of this matrix. Prefer
	 * using the {@link typedConnections} method instead to assure type safety.
	 * 
	 * @param create
	 *            Specifies whether the container shall be created or not, if it
	 *            doesn't already exist.
	 * @return A {@link Sequence} containing the connections of this matrix or
	 *         <i>null</i>, if the container does not exist and {@link create}
	 *         is <i>false</i>.
	 */
	public Sequence connections(boolean create) {
		final Node container = this.find(this.connectionsTag);

		if (container != null) {
			return (Sequence) container;
		} else if (create) {
			final Sequence connections = new Sequence(this.connectionsTag);
			this.insert(connections);
			return connections;
		} else {
			return null;
		}
	}

	/**
	 * This method tests whether the provided {@link MatrixProperty} exists.
	 * 
	 * @param property
	 *            The {@link MatrixProperty} to lookup.
	 * @return <i>true</i>, if the property is available. Otherwise, this method
	 *         returns <i>false</i>.
	 */
	public boolean contains(MatrixProperty property) {
		final Tag tag = new Tag(Class.CONTEXT, property.value());
		final boolean result = hasContent(tag);
		return result;
	}

	/**
	 * Gets the description string of this matrix.
	 * 
	 * @return The description or <i>null</i>, if the property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String description() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.DESCRIPTION);

		if (value != null) {
			return value.toUTF8String();
		} else {
			return null;
		}
	}

	/**
	 * Gets the string containing the schema identifiers of this matrix. The identifiers
	 * are separated with the linefeed character (0x0A, \n).
	 * @return The schema identifier or <i>null</i>, if the property is not set.
	 * @throws UnsupportedOperationException
	 * 			   Thrown if the value is of a different type.
	 */
	public String schemaIdentifiers() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.SCHEMAIDENTIFIERS);
		
		if (value != null) {
			return value.toUTF8String();
		} else {
			return null;
		}
	}

	/**
	 * Gets the number that identifies the gain parameter of a signal.
	 * 
	 * @return The number identifying the gain parameter or <i>null</i>, if the
	 *         property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer gainParameterNumber() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.GAINPARAMETERNUMBER);

		if (value != null) {
			return value.toInt();
		} else {
			return null;
		}
	}

	/**
	 * Gets the identifier string of this matrix.
	 * 
	 * @return The identifier or <i>null</i>, if the property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public String identifier() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.IDENTIFIER);

		if (value != null) {
			return value.toUTF8String();
		} else {
			return null;
		}
	}

	/**
	 * Gets a {@link Sequence} containing the label descriptions. The container
	 * usually consists of objects of type {@link GlowLabel}, which specify the
	 * available label types and their location within the Ember+ tree.
	 * 
	 * @return A {@link Sequence} containing the matrix label descriptions or
	 *         <i>null</i>, if the container does not exist.
	 */
	public Sequence labels() {
		return labels(false);
	}

	/**
	 * Gets a {@link Sequence} containing the label descriptions. The container
	 * usually consists of objects of type {@link GlowLabel}, which specify the
	 * available label types and their location within the Ember+ tree.
	 * 
	 * @param create
	 *            Specifies whether a new container shall be created, if it
	 *            doesn't already exist.
	 * @return A {@link Sequence} containing the matrix label descriptions or
	 *         <i>null</i>, if the container does not exist and {@link create}
	 *         is <i>false</i>.
	 */
	public Sequence labels(boolean create) {
		final Node container = this.getContent(GlowTags.MatrixContents.LABELS);

		if (container != null) {
			return (Sequence) container;
		} else if (create) {
			final Sequence labels = new Sequence(GlowTags.MatrixContents.LABELS);
			this.setContent(labels);

			return labels;
		} else {
			return null;
		}
	}

	/**
	 * Gets a value specifying the maximum number of sources a single target may
	 * have. This property is usually only used when the type is
	 * {@link MatrixType.N_TO_N}.
	 * 
	 * @return The maximum number of sources that may be connected to the same
	 *         target or <i>null</i>, if the property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer maximumConnectsPerTarget()
			throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.MAXIMUMCONNECTSPERTARGET);

		if (value != null) {
			return value.toInt();
		} else {
			return null;
		}
	}

	/**
	 * Gets a value specifying the maximum number of active crosspoints the
	 * matrix supports.
	 * 
	 * @return The maximum number of active crosspoints or <i>null</i>, if the
	 *         property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer maximumTotalConnects() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.MAXIMUMTOTALCONNECTS);

		if (value != null) {
			return value.toInt();
		} else {
			return null;
		}
	}

	/**
	 * Gets the {@link ParametersLocation} of this matrix.
	 * 
	 * @return The {@link ParametersLocation} or <i>null</i>, if the property is
	 *         not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public ParametersLocation parametersLocation()
			throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.PARAMETERSLOCATION);

		if (value != null) {
			return new ParametersLocation(value);
		} else {
			return null;
		}
	}

	/**
	 * Sets the addressing mode of this matrix, which is either
	 * {@link MatrixAddressingMode.LINEAR} or
	 * {@link MatrixAddressingMode.NONLINEAR}.
	 * 
	 * @param mode
	 *            The {@link MatrixAddressingMode} for this matrix.
	 */
	public void setAddressingMode(MatrixAddressingMode mode) {
		setContent(GlowTags.MatrixContents.ADDRESSINGMODE, mode.value());
	}

	/**
	 * Sets the description string of this matrix. User interfaces can use this
	 * property as display string.
	 * 
	 * @param description
	 *            The description string of this matrix.
	 * @throws NullPointerException
	 *             Thrown if {@link description} is <i>null</i>.
	 */
	public void setDescription(String description) throws NullPointerException {
		Assert.AssertNotNull(description, "description");

		setContent(GlowTags.MatrixContents.DESCRIPTION, description);
	}

	/**
	 * Sets the number of the gain parameter a signal may have. If specified,
	 * each signal should have a parameter with that number which is used to
	 * control the gain of that signal.
	 * 
	 * @param gainParameterNumber
	 *            The number of the gain parameter.
	 */
	public void setGainParameterNumber(int gainParameterNumber) {
		setContent(GlowTags.MatrixContents.GAINPARAMETERNUMBER,
				gainParameterNumber);
	}

	/**
	 * Sets the identifier string of this matrix. The identifier must not start
	 * with a number and must not contain the slash character. For the same
	 * object, it must not change.
	 * 
	 * @param identifier
	 *            The identifier of this matrix.
	 * @throws NullPointerException
	 *             Thrown if {@link identifier} is <i>null</i>.
	 */
	public void setIdentifier(String identifier) throws NullPointerException {
		Assert.AssertNotNull(identifier, "identifier");

		setContent(GlowTags.MatrixContents.IDENTIFIER, identifier);
	}
	
	/**
	 * Sets the string containing the schema identifiers of this matrix. This value must not be <i>null</i>.
	 * @param identifiers The identifier string to set.
	 * @throws NullPointerException
	 * 			   Thrown if {@link identifiers} is <i>null</i>.
	 */
	public void setSchemaIdentifiers(String identifiers) throws NullPointerException {
		Assert.AssertNotNull(identifiers, "identifiers");
		
		setContent(GlowTags.MatrixContents.SCHEMAIDENTIFIERS, identifiers);
	}

	/**
	 * Specifies how many active crosspoints the matrix may have. If no value is
	 * provided, a consumer may assume that there is no limit.
	 * 
	 * @param maximumTotalConnects
	 *            The maximum number of active crosspoints.
	 */
	public void setMaximumTotalConnects(int maximumTotalConnects) {
		setContent(GlowTags.MatrixContents.MAXIMUMTOTALCONNECTS,
				maximumTotalConnects);
	}

	/**
	 * Specifies the maximum number of sources a single target may have.
	 * Usually, this value is only of interest for n to n matrices. For all
	 * other matrix types, this property is not required.
	 * 
	 * @param maximumTotalConnectsPerTarget
	 *            The maximum number of sources per target.
	 */
	public void setMaximumTotalConnectsPerTarget(
			int maximumTotalConnectsPerTarget) {
		setContent(GlowTags.MatrixContents.MAXIMUMCONNECTSPERTARGET,
				maximumTotalConnectsPerTarget);
	}

	/**
	 * Sets the location of the parameters this matrix has. When specifying a
	 * single number, it must be a child node of this matrix.
	 * 
	 * @param inlineSubid
	 *            The number of the child node containing the matrix parameters.
	 */
	public void setParametersLocation(int inlineSubid) {
		setContent(GlowTags.MatrixContents.PARAMETERSLOCATION, inlineSubid);
	}

	/**
	 * Sets the location of the parameters this matrix has. When specifying an
	 * {@link Oid}, it may point to any location in the gadget tree.
	 * 
	 * @param basePath
	 *            The location of the subtree containing the matrix parameters.
	 * @throws NullPointerException
	 *             Thrown if {@link basePath} is <i>null</i>.
	 */
	public void setParametersLocation(Oid basePath) throws NullPointerException {
		Assert.AssertNotNull(basePath, "basePath");

		setContent(GlowTags.MatrixContents.PARAMETERSLOCATION, basePath);
	}

	/**
	 * Sets the number of sources this matrix can have.
	 * 
	 * @param sourceCount
	 *            The number of sources this matrix can have.
	 */
	public void setSourceCount(int sourceCount) {
		setContent(GlowTags.MatrixContents.SOURCECOUNT, sourceCount);
	}

	/**
	 * Sets the number of targets this matrix can have.
	 * 
	 * @param targetCount
	 *            The number of targets this matrix can have.
	 */
	public void setTargetCount(int targetCount) {
		setContent(GlowTags.MatrixContents.TARGETCOUNT, targetCount);
	}

	/**
	 * Sets the type of this matrix. The {@link MatrixType} defines the
	 * switching behavior of a matrix, like 1 to 1 or 1 to N.
	 * 
	 * @param type
	 *            The {@link MatrixType} for this matrix.
	 */
	public void setType(MatrixType type) {
		setContent(GlowTags.MatrixContents.TYPE, type.value());
	}

	/**
	 * Assigns a collection of {@link GlowConnection} objects to this matrix.
	 * Existing connections will be removed.
	 * 
	 * @param connections
	 *            An {@link Iterator} containing the connections to assign.
	 * @throws NullPointerException
	 *             Thrown if {@link connections} is <i>null</i>.
	 */
	public void setTypedConnections(Iterator<GlowConnection> connections)
			throws NullPointerException {
		Assert.AssertNotNull(connections, "connections");

		final Sequence container = this.connections(true);

		if (container != null) {
			container.clear();

			while (connections.hasNext()) {
				container.insert(connections.next());
			}
		}
	}

	/**
	 * Assigns a collection of {@link GlowLabel} objects to this matrix.
	 * Existing label information will be removed.
	 * 
	 * @param labels
	 *            An {@link Iterator} containing the labels descriptios for this
	 *            matrix.
	 * @throws NullPointerException
	 *             Thrown if {@link labels} is <i>null</i>.
	 */
	public void setTypedLabels(Iterator<GlowLabel> labels)
			throws NullPointerException {
		Assert.AssertNotNull(labels, "labels");

		final Sequence container = this.labels(true);

		if (container != null) {
			container.clear();

			while (labels.hasNext()) {
				container.insert(labels.next());
			}
		}
	}

	/**
	 * Assigns a collection of {@link GlowSource} objects of this instance.
	 * Exsiting sources will be removed.
	 * 
	 * @param sources
	 *            An {@link Iterator} containing the sources to assign.
	 * @throws NullPointerException
	 *             Thrown if {@link targets} is <i>null</i>.
	 */
	public void setTypedSources(Iterator<GlowSource> sources)
			throws NullPointerException {
		Assert.AssertNotNull(sources, "sources");

		final Sequence container = this.sources();

		if (container != null) {
			container.clear();

			while (sources.hasNext()) {
				container.insert(sources.next());
			}
		}
	}

	/**
	 * Assigns a collection of {@link GlowTarget} objects of this instance.
	 * Exsiting targets will be removed.
	 * 
	 * @param targets
	 *            An {@link Iterator} containing the targets to assign.
	 * @throws NullPointerException
	 *             Thrown if {@link targets} is <i>null</i>.
	 */
	public void setTypedTargets(Iterator<GlowTarget> targets)
			throws NullPointerException {
		Assert.AssertNotNull(targets, "targets");

		final Sequence container = this.targets();

		if (container != null) {
			container.clear();

			while (targets.hasNext()) {
				container.insert(targets.next());
			}
		}
	}

	/**
	 * Gets a value specifying the number of sources the matrix has.
	 * 
	 * @return The number of sources this matrix has or <i>null</i>, if the
	 *         property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer sourceCount() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.SOURCECOUNT);

		if (value != null) {
			return value.toInt();
		} else {
			return null;
		}
	}

	/**
	 * Gets a {@link Sequence} containing the sources of this matrix. Prefer
	 * using the {@link typedSources} method instead to assure type safety.
	 * 
	 * @return A {@link Sequence} containing the sources of this matrix or
	 *         <i>null</i>, if the container does not exist.
	 */
	public Sequence sources() {
		return sources(false);
	}

	/**
	 * Gets a {@link Sequence} containing the sources of this matrix. Prefer
	 * using the {@link typedSources} method instead to assure type safety.
	 * 
	 * @param create
	 *            Specifies whether the container shall be created or not, if it
	 *            doesn't already exist.
	 * @return A {@link Sequence} containing the sources of this matrix or
	 *         <i>null</i>, if the container does not exist and {@link create}
	 *         is <i>false</i>.
	 */
	public Sequence sources(boolean create) {
		final Node container = this.find(this.sourcesTag);

		if (container != null) {
			return (Sequence) container;
		} else if (create) {
			final Sequence sources = new Sequence(this.sourcesTag);
			this.insert(sources);
			return sources;
		} else {
			return null;
		}
	}

	/**
	 * Gets a value specifying the number of targets the matrix has.
	 * 
	 * @return The number of targets this matrix has or <i>null</i>, if the
	 *         property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer targetCount() throws UnsupportedOperationException {
		final Value value = this
				.getContentValue(GlowTags.MatrixContents.TARGETCOUNT);

		if (value != null) {
			return value.toInt();
		} else
			return null;
	}

	/**
	 * Gets a {@link Sequence} containing the targets of this matrix. Prefer
	 * using the {@link typedTargets} method instead to assure type safety.
	 * 
	 * @return A {@link Sequence} containing the targets of this matrix or
	 *         <i>null</i>, if the container does not exist.
	 */
	public Sequence targets() {
		return targets(false);
	}

	/**
	 * Gets a {@link Sequence} containing the targets of this matrix. Prefer
	 * using the {@link typedTargets} method instead to assure type safety.
	 * 
	 * @param create
	 *            Specifies whether the container shall be created or not, if it
	 *            doesn't already exist.
	 * @return A {@link Sequence} containing the targets of this matrix or
	 *         <i>null</i>, if the container does not exist and {@link create}
	 *         is <i>false</i>.
	 */
	public Sequence targets(boolean create) {
		final Node container = this.find(this.targetsTag);

		if (container != null) {
			return (Sequence) container;
		} else if (create) {
			final Sequence targets = new Sequence(this.targetsTag);
			this.insert(targets);
			return targets;
		} else {
			return null;
		}
	}

	/**
	 * Gets the {@link MatrixType} of this matrix instance.
	 * 
	 * @return The type or <i>null</i>, if the property is not set.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public MatrixType type() throws UnsupportedOperationException {
		final Value value = this.getContentValue(GlowTags.MatrixContents.TYPE);

		if (value != null) {
			return MatrixType.valueOf(value.toInt());
		} else {
			return null;
		}
	}

	/**
	 * Gets an {@link Iterable} containing the connections of this matrix.
	 * Usually this method returns only the active crosspoints.
	 * 
	 * @return An {@link Iterable} containing the objects of type
	 *         {@link GlowConnection}.
	 */
	public Iterable<GlowConnection> typedConnections() {
		final ArrayList<GlowConnection> result = new ArrayList<GlowConnection>();
		final Sequence connections = this.connections();

		if (connections != null) {
			final Iterator<Node> it = connections.iterator();

			while (it.hasNext()) {
				final GlowConnection connection = (GlowConnection) it.next();

				if (connection != null) {
					result.add(connection);
				}
			}
		}

		return result;
	}

	/**
	 * Gets an {@link Iterable} containing the object of {@link GlowLabel}. This
	 * method shall be preferred over {@link labels}, since it is type-safe.
	 * 
	 * @return An {@link Iterable} providing access to the labels descriptions.
	 */
	public Iterable<GlowLabel> typedLabels() {
		final ArrayList<GlowLabel> result = new ArrayList<GlowLabel>();
		final Sequence labels = this.labels();

		if (labels != null) {
			final Iterator<Node> it = labels.iterator();

			while (it.hasNext()) {
				final GlowLabel label = (GlowLabel) it.next();

				if (label != null)
					result.add(label);
			}
		}

		return result;
	}

	/**
	 * Gets an {@link Iterable} containing the known sources of this matrix.
	 * This method shall be preferred over {@link sources}, since it is
	 * type-safe.
	 * 
	 * @return An {@link Iterable} containing the known sources of this matrix.
	 */
	public Iterable<GlowSource> typedSources() {
		final ArrayList<GlowSource> result = new ArrayList<GlowSource>();
		final Sequence sources = this.sources();

		if (sources != null) {
			final Iterator<Node> it = sources.iterator();

			while (it.hasNext()) {
				final GlowSource source = (GlowSource) it.next();

				if (sources != null) {
					result.add(source);
				}
			}
		}

		return result;
	}

	/**
	 * Gets an {@link Iterable} containing the known targets of this matrix.
	 * This method shall be preferred over {@link targets}, since it is
	 * type-safe.
	 * 
	 * @return An {@link Iterable} containing the known targets of this matrix.
	 */
	public Iterable<GlowTarget> typedTargets() {
		final ArrayList<GlowTarget> result = new ArrayList<GlowTarget>();
		final Sequence targets = this.targets();

		if (targets != null) {
			final Iterator<Node> it = targets.iterator();

			while (it.hasNext()) {
				final GlowTarget target = (GlowTarget) it.next();

				if (target != null) {
					result.add(target);
				}
			}
		}

		return result;
	}
}
