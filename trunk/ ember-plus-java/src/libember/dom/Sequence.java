package libember.dom;

import libember.ber.Tag;
import libember.ber.UniversalType;

/**
 * This class implements the ber sequence. A {@link Sequence} is a collection of
 * objects of the same type, whose application tag is unused and usually set to
 * {@libember.ber.Class.Context} with the number zero.
 */
public class Sequence extends ListContainer {
	private final static Tag SEQUENCE_TAG = UniversalType
			.universalTag(UniversalType.SEQUENCE);

	/**
	 * Initializes a new instance of the {@link Sequence} class.
	 * 
	 * @param applicationTag
	 *            The application tag of this {@link Sequence}.
	 * @throws NullPointerException
	 *             Thrown if {@link applicationTag} is <i>null</i>.
	 */
	public Sequence(Tag applicationTag) throws NullPointerException {
		super(applicationTag);
	}

	@Override
	protected Tag typeTagImpl() {
		return SEQUENCE_TAG;
	}
}
