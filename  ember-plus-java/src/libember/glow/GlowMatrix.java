package libember.glow;

import javax.naming.OperationNotSupportedException;

import libember.ber.Tag;
import libember.ber.Value;
import libember.dom.Leaf;

/**
 * This class represents a matrix within the gadget tree. For a detailed
 * description of the matrix properties, please see {@link GlowMatrixBase}.
 */
public final class GlowMatrix extends GlowMatrixBase {
	/**
	 * Initializes a new instance of the {@link GlowMatrix} class.
	 * 
	 * @param tag
	 *            The application tag of this instance.
	 */
	GlowMatrix(Tag tag) {
		super(GlowType.MATRIX, tag, GlowTags.Matrix.CONTENTS,
				GlowTags.Matrix.CHILDREN, GlowTags.Matrix.TARGETS,
				GlowTags.Matrix.SOURCES, GlowTags.Matrix.CONNECTIONS);
	}

	/**
	 * Initializes a new instance of the {@link GlowMatrix} class.
	 * 
	 * @param parent
	 *            The {@link GlowNodeBase} to append this instance to.
	 * @param number
	 *            The number identifying this matrix.
	 */
	public GlowMatrix(GlowNodeBase parent, int number) {
		super(GlowType.MATRIX, GlowTags.ELEMENT_DEFAULT,
				GlowTags.Matrix.CONTENTS, GlowTags.Matrix.CHILDREN,
				GlowTags.Matrix.TARGETS, GlowTags.Matrix.SOURCES,
				GlowTags.Matrix.CONNECTIONS);

		insert(new Leaf(GlowTags.Matrix.NUMBER, new Value(number)));

		if (parent != null) {
			final GlowElementCollection children = parent.children(true);

			children.insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowMatrix} class.
	 * 
	 * @param parent
	 *            The {@link GlowRootElementCollection} to append this instance
	 *            to.
	 * @param number
	 *            The number identifying this matrix.
	 */
	public GlowMatrix(GlowRootElementCollection parent, int number) {
		super(GlowType.MATRIX, GlowTags.ELEMENT_DEFAULT,
				GlowTags.Matrix.CONTENTS, GlowTags.Matrix.CHILDREN,
				GlowTags.Matrix.TARGETS, GlowTags.Matrix.SOURCES,
				GlowTags.Matrix.CONNECTIONS);

		insert(new Leaf(GlowTags.Matrix.NUMBER, new Value(number)));

		if (parent != null) {
			parent.insert(this);
		}
	}

	/**
	 * Initializes a new instance of the {@link GlowMatrix} class.
	 * 
	 * @param number
	 *            The number identifying this matrix.
	 */
	public GlowMatrix(int number) {
		super(GlowType.MATRIX, GlowTags.ELEMENT_DEFAULT,
				GlowTags.Matrix.CONTENTS, GlowTags.Matrix.CHILDREN,
				GlowTags.Matrix.TARGETS, GlowTags.Matrix.SOURCES,
				GlowTags.Matrix.CONNECTIONS);

		insert(new Leaf(GlowTags.Matrix.NUMBER, new Value(number)));
	}

	/**
	 * Initializes a new instance of the {@link GlowMatrix} class.
	 * 
	 * @param number
	 *            The number identifying this matrix.
	 * @param tag
	 *            The application tag of this instance.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	public GlowMatrix(int number, Tag tag) throws NullPointerException {
		super(GlowType.MATRIX, tag, GlowTags.Matrix.CONTENTS,
				GlowTags.Matrix.CHILDREN, GlowTags.Matrix.TARGETS,
				GlowTags.Matrix.SOURCES, GlowTags.Matrix.CONNECTIONS);

		insert(new Leaf(GlowTags.Matrix.NUMBER, new Value(number)));
	}

	/**
	 * Gets the number identifying this instance.
	 * 
	 * @return The number identifying this matrix or <i>null</i>, if the value
	 *         is not specified.
	 * @throws OperationNotSupportedException
	 *             Thrown if the value is of a different type.
	 */
	public Integer number() throws OperationNotSupportedException {
		final Leaf leaf = this.findType(GlowTags.Matrix.NUMBER);

		if (leaf != null) {
			return leaf.value().toInt();
		} else {
			return null;
		}
	}
}
