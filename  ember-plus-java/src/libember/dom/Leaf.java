package libember.dom;

import libember.ber.Encoding;
import libember.ber.Length;
import libember.ber.Tag;
import libember.ber.Value;
import libember.util.Assert;
import libember.util.OutputStream;

/**
 * This class represents a leaf within an ber tree. A {@link Leaf} must not
 * contain any children, but a value instead. This class accepts instance of
 * {@link Value}, which is a type erased value that is encodable. The value this
 * class is initialized with is immutable.
 */
public class Leaf extends Node {
	private final Value value;
	private int cachedLength;

	/**
	 * Initializes a new instance of the {@link Leaf} class.
	 * 
	 * @param applicationTag
	 *            The application tag of this node.
	 * @param value
	 *            The type erased {@link Value} this leaf stores.
	 * @throws NullPointerException
	 *             Thrown if either {@link applicationTag} or {@link value} is
	 *             <i>null</i>.
	 */
	public Leaf(Tag applicationTag, Value value) throws NullPointerException {
		super(applicationTag);

		Assert.AssertNotNull(value, "value");

		this.value = value;
	}

	@Override
	protected int encodedLengthImpl() {
		return cachedLength;
	}

	@Override
	protected void encodeImpl(OutputStream output) {
		final Tag innerTag = typeTag();
		final int innerTagLength = Encoding.encodedLength(innerTag);
		final int payloadLength = value.encodedLength();
		final int innerLength = innerTagLength
				+ Encoding.encodedLength(new Length(payloadLength))
				+ payloadLength;

		Encoding.encode(output, applicationTag().toContainer());
		Encoding.encode(output, new Length(innerLength));
		Encoding.encode(output, innerTag);
		Encoding.encode(output, new Length(payloadLength));

		value.encode(output);
	}

	@Override
	protected Tag typeTagImpl() {
		return value.universalTag();
	}

	@Override
	protected void updateImpl() {
		final int innerTagLength = Encoding.encodedLength(typeTag());
		final int payloadLength = value.encodedLength();
		final int innerLength = innerTagLength
				+ Encoding.encodedLength(new Length(payloadLength))
				+ payloadLength;

		final int outerTagLength = Encoding.encodedLength(applicationTag()
				.toContainer());
		final int outerLength = outerTagLength
				+ Encoding.encodedLength(new Length(innerLength)) + innerLength;

		cachedLength = outerLength;
	}

	/**
	 * Gets the {@link Value} this instance stores.
	 * 
	 * @return The {@link Value} this instance stores.
	 */
	public Value value() {
		return value;
	}
}
