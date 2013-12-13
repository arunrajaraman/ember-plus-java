package libember.glow;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;

/**
 * This class represents the signal of a matrix. It serves as base class for
 * {@link GlowTarget} and {@link GlowSource}.
 */
public class GlowSignal extends GlowContainer {
	/**
	 * Initializes a new instance of the {@link GlowSignal} class.
	 * 
	 * @param type
	 *            The concrete type of this instance, either
	 *            {@link GlowType.SOURCE} or {@link GlowType.TARGET}.
	 * @param number
	 *            The number identifying this signal.
	 * @throws NullPointerException
	 *             Thrown if {@link type} is <i>null</i>.
	 */
	protected GlowSignal(GlowType type, int number) throws NullPointerException {
		super(InsertMode.DEFAULT, type);

		this.insert(new Leaf(GlowTags.Signal.NUMBER, new Value(number)));
	}

	/**
	 * Initializes a new instance of the {@link GlowSignal} class.
	 * 
	 * @param type
	 *            The concrete type of this instance, either
	 *            {@link GlowType.SOURCE} or {@link GlowType.TARGET}.
	 * @param tag
	 *            The application tag of this instance.
	 * @throws NullPointerException
	 *             Thrown if either {@link type} or {@link tag} is <i>null</i>.
	 */
	protected GlowSignal(GlowType type, Tag tag) throws NullPointerException {
		super(InsertMode.DEFAULT, type, tag);
	}

	/**
	 * Initializes a new instance of the {@link GlowSignal} class.
	 * 
	 * @param type
	 *            The concrete type of this instance, either
	 *            {@link GlowType.SOURCE} or {@link GlowType.TARGET}.
	 * @param tag
	 *            The application tag of this instance.
	 * @param number
	 *            The number identifying this signal.
	 * @throws NullPointerException
	 *             Thrown if any of the arguments is <i>null</i>.
	 */
	protected GlowSignal(GlowType type, Tag tag, int number) {
		super(InsertMode.DEFAULT, type, tag);

		this.insert(new Leaf(GlowTags.Signal.NUMBER, new Value(number)));
	}

	/**
	 * Gets the number idetifying this signal.
	 * 
	 * @return The number of this signal or <i>null</i>, if the property does
	 *         not exist.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Integer number() throws UnsupportedOperationException {
		final Leaf leaf = this.findType(GlowTags.Signal.NUMBER);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}
}
