package libember.glow;

import libember.ber.Oid;
import libember.ber.UniversalType;
import libember.ber.Value;

/**
 * This class contains the location of the parameters of a matrix signal. It may
 * either be a single integer or a relative object identifier.
 */
public final class ParametersLocation {
	private final ParametersLocationKind kind;
	private final Value value;

	/**
	 * Initializes a new instance of the {@link ParametersLocation} class.
	 * 
	 * @param value
	 *            The ber value to get the location and the location kind from.
	 */
	ParametersLocation(Value value) {
		this.value = value;
		this.kind = determineKind(value);
	}

	/**
	 * Initializes a new instance of the {@link ParametersLocation} class, with
	 * the kind set to {@link ParametersLocationKind.INLINE}.
	 * 
	 * @param inlineSubid
	 *            The number that contains the parameters.
	 */
	public ParametersLocation(int inlineSubid) {
		this.value = new Value(inlineSubid);
		this.kind = ParametersLocationKind.INLINE;
	}

	/**
	 * Initializes a new instance of the {@link ParametersLocation} class, with
	 * the kind set to {@link ParametersLocationKind.BASEPATH}.
	 * 
	 * @param oid
	 *            AN {@link Oid} specifying the location of the matrix
	 *            parameters.
	 */
	public ParametersLocation(Oid oid) {
		this.value = new Value(oid);
		this.kind = ParametersLocationKind.BASEPATH;
	}

	/**
	 * Determines the {@link ParametersLocationKind}, depending on the type of
	 * the provided {@link value}.
	 * 
	 * @param value
	 *            The value to extract the location kind from.
	 * @return {@link ParametersLocationKind.INLINE}, if the provided value is
	 *         of type {@link UniversalType.INTEGER},
	 *         {@link UniversalType.RELATIVEOID}, if the type if
	 *         {@link ParametersLocationKind.BASEPATH}.
	 */
	private ParametersLocationKind determineKind(Value value) {
		final UniversalType type = value.type();

		if (type == UniversalType.INTEGER) {
			return ParametersLocationKind.INLINE;
		} else if (type == UniversalType.RELATIVEOID) {
			return ParametersLocationKind.BASEPATH;
		} else {
			return ParametersLocationKind.NONE;
		}
	}

	/**
	 * If the {@link kind} method returns
	 * {@link ParametersLocationKind.BASEPATH}, this property returns the
	 * {@link Oid} which contains the location of the node containing the matrix
	 * parameters.
	 * 
	 * @return The path node containing the parameters.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public Oid basePath() throws UnsupportedOperationException {
		return this.value.toOid();
	}

	/**
	 * If the {@link kind} method returns {@link ParametersLocationKind.INLINE},
	 * this property returns the number of the subnode containing the matrix
	 * parameters.
	 * 
	 * @return The number of the matrix subnode containing the parameters.
	 * @throws UnsupportedOperationException
	 *             Thrown if the value is of a different type.
	 */
	public int inlineSubid() throws UnsupportedOperationException {
		return this.value.toInt();
	}

	/**
	 * Gets the location kind of this instance, which specifies whether the
	 * parameters are a direct subnode ({@link ParametersLocationKind.INLINE})
	 * or located at another position ({@link ParametersLocationKind.BASEPATH}).
	 * 
	 * @return The {@link ParametersLocationKind} of this instance.
	 */
	public ParametersLocationKind kind() {
		return this.kind;
	}
}
