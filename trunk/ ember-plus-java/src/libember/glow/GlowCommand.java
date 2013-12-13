package libember.glow;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;
import libember.util.Assert;

/**
 * This class represents a command. A {@link GlowCommand} can be used to query
 * the children of a node or matrix, to subscribe to a parameter, to unsubscribe
 * from a node or a parameter, or to invoke a function.
 */
public final class GlowCommand extends GlowElement {
	/**
	 * Initializes a new instance of the {@link GlowCommand} class.
	 * 
	 * @param tag
	 *            Tag application tag.
	 */
	GlowCommand(Tag tag) {
		super(InsertMode.SORTED, GlowType.COMMAND, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowCommand} class.
	 * 
	 * @param type
	 *            The concrete {@link CommandType}.
	 * @throws NullPointerException
	 *             Thrown if {@link type} is <i>null</i>.
	 */
	public GlowCommand(CommandType type) throws NullPointerException {
		super(InsertMode.SORTED, GlowType.COMMAND);

		Assert.AssertNotNull(type, "type");

		insert(new Leaf(GlowTags.Command.NUMBER, new Value(type.value())));
	}

	/**
	 * Gets the {@link DirFieldMask}
	 * 
	 * @return The {@link DirFieldMask}. If the mask is not specified, this
	 *         method returns {@link DirFieldMask.ALL}.
	 */
	public DirFieldMask dirFieldMask() {
		final Leaf node = findType(GlowTags.Command.DIRFIELD_MASK);

		if (node != null) {
			final int value = node.value().toInt();

			return DirFieldMask.valueOf(value);
		} else {
			return DirFieldMask.ALL;
		}
	}

	/**
	 * Gets the {@link CommandType}.
	 * 
	 * @return The {@link CommandType} of this instance. If the command is not
	 *         specified, {@link CommandType.UNKNOWN} is being returned.
	 */
	public CommandType number() {
		final Leaf node = this.findType(GlowTags.Command.NUMBER);

		if (node != null) {
			final int value = node.value().toInt();
			return CommandType.valueOf(value);
		} else {
			return CommandType.UNKNOWN;
		}
	}

	/**
	 * Sets the {@link DirFieldMask} for this command. This property is only
	 * used by a request of type {@link CommandType.GETDIRECTORY}. If this
	 * property is not set, the provider must handle the command as if
	 * {@link DirFieldMask.DEFAULT} is set.
	 * 
	 * @param mask
	 *            The mask containing the properties to query.
	 * @throws NullPointerException
	 *             Thrown if {@link mask} is <i>null</i>.
	 */
	public void setDirFieldMask(DirFieldMask mask) throws NullPointerException {
		replace(new Leaf(GlowTags.Command.DIRFIELD_MASK,
				new Value(mask.value())));
	}
}
