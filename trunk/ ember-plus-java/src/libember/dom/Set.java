package libember.dom;

import libember.ber.Tag;
import libember.ber.UniversalType;

/**
 * This class implements the ber set. A {@link Set} contains elements in any
 * order and of arbitrary types, but each element must have an application tag
 * that is unique within the {@link Set}. Speaking in terms of C++, the
 * {@link Set} can be compared to a <code>struct</code>.
 */
public class Set extends ListContainer {
	private final static Tag SET_TAG = UniversalType
			.universalTag(UniversalType.SET);

	/**
	 * Initializes a new instance of the {@link Set} class.
	 * 
	 * @param applicationTag
	 *            The application tag of this {@link Set}.
	 * @throws NullPointerException
	 *             Thrown if {@link applicationTag} is <i>null</i>.
	 */
	public Set(Tag appTag) {
		super(appTag);
	}

	@Override
	protected Tag typeTagImpl() {
		return SET_TAG;
	}
}
