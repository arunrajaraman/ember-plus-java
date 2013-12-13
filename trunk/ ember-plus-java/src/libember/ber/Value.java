package libember.ber;

import libember.util.OutputStream;

/**
 * Represents a type erased value which also supports encoding methods. This
 * variant can be used for values of type <i>long</i>, <i>int</i>,
 * <i>double</i>, <i>float</i>, <i>String</i>, <i>boolean</i>, {@link Oid} and
 * {@link Octets}.
 */
public final class Value {
	/**
	 * Implementation of the {@link Payload} class for a value of type
	 * <i>boolean</i>.
	 */
	private final class BooleanPayload extends Payload {
		private final boolean value;

		/**
		 * Initializes a new instance of the {@link BooleanPayload} class.
		 * 
		 * @param value
		 *            The value to store.
		 */
		public BooleanPayload(boolean value) {
			super(UniversalType.BOOLEAN);
			this.value = value;
		}

		@Override
		public void encode(OutputStream output) {
			Encoding.encode(output, value);
		}

		@Override
		public int encodedLength() {
			return Encoding.encodedLength(value);
		}

		@Override
		public boolean toBoolean() {
			return value;
		}

		@Override
		public String toString() {
			return String.format("BooleanPayload (Value = %s)", value ? "tue"
					: "false");
		}
	}

	/**
	 * Implementation of the {@link Payload} class for a value of type
	 * <i>double</i>.
	 */
	private final class DoublePayload extends Payload {
		private final double value;

		/**
		 * Initializes a new instance of the {@link DoublePayload} class.
		 * 
		 * @param value
		 *            The value to store.
		 */
		public DoublePayload(double value) {
			super(UniversalType.REAL);
			this.value = value;
		}

		@Override
		public void encode(OutputStream output) {
			Encoding.encode(output, value);
		}

		@Override
		public int encodedLength() {
			return Encoding.encodedLength(value);
		}

		@Override
		public double toDouble() {
			return value;
		}

		@Override
		public String toString() {
			return String.format("DoublePayload (Value = %lf)", value);
		}
	}

	/**
	 * Implementation of the {@link Payload} class for a value of type
	 * <i>long</i>.
	 */
	private final class LongPayload extends Payload {
		private final long value;

		/**
		 * Initializes a new instance of the {@link LongPayload} class.
		 * 
		 * @param value
		 *            The value to store.
		 */
		public LongPayload(long value) {
			super(UniversalType.INTEGER);
			this.value = value;
		}

		@Override
		public void encode(OutputStream output) {
			Encoding.encode(output, value);
		}

		@Override
		public int encodedLength() {
			return Encoding.encodedLength(value);
		}

		@Override
		public long toLong() {
			return value;
		}

		@Override
		public String toString() {
			return String.format("LongPayload (Value = %d)", value);
		}
	}

	/**
	 * Implementation of null. The encoded length of an instance of this class
	 * is 0.
	 */
	private final class NullPayload extends Payload {
		/**
		 * Initializes a new instance of the {@link NullPayload} class.
		 */
		NullPayload() {
			super(UniversalType.NULL);
		}

		@Override
		public void encode(OutputStream output) {
		}

		@Override
		public int encodedLength() {
			return 0;
		}

		@Override
		public String toString() {
			return "Null";
		}
	}

	/**
	 * Implementation of the {@link Payload} class for a value of type
	 * {@link Octets}.
	 */
	private final class OctetsPayload extends Payload {
		private final Octets value;

		/**
		 * Initializes a new instance of the {@link OctetsPayload} class.
		 * 
		 * @param value
		 *            The value to store.
		 */
		public OctetsPayload(Octets value) {
			super(UniversalType.OCTETSTRING);
			this.value = value;
		}

		@Override
		public void encode(OutputStream output) {
			Encoding.encode(output, value);
		}

		@Override
		public int encodedLength() {
			return Encoding.encodedLength(value);
		}

		@Override
		public Octets toOctets() {
			return value;
		}

		@Override
		public String toString() {
			return String
					.format("OctetsPayload (Value = %s)", value.toString());
		}
	}

	/**
	 * Implementation of the {@link Payload} class for a value of type
	 * {@link Oid}.
	 */
	private final class OidPayload extends Payload {
		private final Oid value;

		/**
		 * Initializes a new instance of the {@link OidPayload} class.
		 * 
		 * @param value
		 *            The value to store.
		 */
		public OidPayload(Oid value) {
			super(UniversalType.RELATIVEOID);
			this.value = value;
		}

		@Override
		public void encode(OutputStream output) {
			Encoding.encode(output, value);
		}

		@Override
		public int encodedLength() {
			return Encoding.encodedLength(value);
		}

		@Override
		public Oid toOid() {
			return value;
		}

		@Override
		public String toString() {
			return String.format("OidPayload (Value = %s)", value.toString());
		}
	}

	/**
	 * Nested Payload base class.
	 */
	private abstract class Payload {
		private final UniversalType type;

		/**
		 * Initializes a new instance of the {@link Payload} class.
		 * 
		 * @param type
		 *            The {@link UniversalType} of the stored value.
		 */
		protected Payload(UniversalType type) {
			this.type = type;
		}

		/**
		 * Encodes the value to the provided output stream.
		 * 
		 * @param output
		 *            The {@link OutputStream} to write the encoded data to.
		 */
		public abstract void encode(OutputStream output);

		/**
		 * Gets the encoded length of the value.
		 * 
		 * @return The encoded length of the value.
		 */
		public abstract int encodedLength();

		/**
		 * If the stored value if of type <i>boolean</i>, this method returns
		 * the current value. Otherwise, an exception is thrown.
		 * 
		 * @return The stored value. If the concrete type is not <i>boolean</i>,
		 *         an <i>UnsupportedOperationException</i> will be thrown.
		 * @throws UnsupportedOperationException
		 *             Thrown if the nested type is not of type <i>boolean</i>.
		 */
		public boolean toBoolean() {
			throw new UnsupportedOperationException(
					"The value is not of type boolean.");
		}

		/**
		 * If the stored value if of type <i>double</i>, this method returns the
		 * current value. Otherwise, an exception is thrown.
		 * 
		 * @return The stored value. If the concrete type is not <i>double</i>,
		 *         an <i>UnsupportedOperationException</i> will be thrown.
		 * @throws UnsupportedOperationException
		 *             Thrown if the nested type is not of type <i>double</i>.
		 */
		public double toDouble() {
			throw new UnsupportedOperationException(
					"The value is not of type double.");
		}

		/**
		 * If the stored value if of type <i>long</i>, this method returns the
		 * current value. Otherwise, an exception is thrown.
		 * 
		 * @return The stored value. If the concrete type is not <i>long</i>, an
		 *         <i>UnsupportedOperationException</i> will be thrown.
		 * @throws UnsupportedOperationException
		 *             Thrown if the nested type is not of type <i>long</i>.
		 */
		public long toLong() {
			throw new UnsupportedOperationException(
					"The value is not of type long.");
		}

		/**
		 * If the stored value if of type Octets, this method returns the
		 * current value.
		 * 
		 * @return The stored boolean value. If the concrete type is not Octets,
		 *         an UnsupportedOperationException will be thrown.
		 * @throws UnsupportedOperationException
		 *             Throws an exception if the nested type is not Octets.
		 */
		public Octets toOctets() {
			throw new UnsupportedOperationException(
					"The value is not of type Octets.");
		}

		/**
		 * If the stored value if of type {@link Oid}, this method returns the
		 * current value. Otherwise, an exception is thrown.
		 * 
		 * @return The stored value. If the concrete type is not {@link Oid}, an
		 *         <i>UnsupportedOperationException</i> will be thrown.
		 * @throws UnsupportedOperationException
		 *             Thrown if the nested type is not of type {@link Oid}.
		 */
		public Oid toOid() {
			throw new UnsupportedOperationException(
					"The value is not of type Oid.");
		}

		/**
		 * If the stored value if of type <i>String</i>, this method returns the
		 * current value. Otherwise, an exception is thrown.
		 * 
		 * @return The stored value. If the concrete type is not <i>String</i>,
		 *         an <i>UnsupportedOperationException</i> will be thrown.
		 * @throws UnsupportedOperationException
		 *             Thrown if the nested type is not of type <i>String</i>.
		 */
		public String toUTF8String() {
			throw new UnsupportedOperationException(
					"The value is not of type String.");
		}

		/**
		 * Gets the {@link UniversalType} of the stored value.
		 * 
		 * @return The {@link UniversalType} of the stored value.
		 */
		public UniversalType type() {
			return type;
		}

		/**
		 * Gets a universal {@link Tag} that represents the type of this value.
		 * 
		 * @return A {@link Tag} representation of the stored value type.
		 */
		public Tag universalTag() {
			return UniversalType.universalTag(type);
		}
	}

	/**
	 * Implementation of the {@link Payload} class for a value of type
	 * <i>String</i>.
	 */
	private final class StringPayload extends Payload {
		private final String value;

		/**
		 * Initializes a new instance of the {@link StringPayload} class.
		 * 
		 * @param value
		 *            The value to store.
		 */
		public StringPayload(String value) {
			super(UniversalType.UTF8STRING);
			this.value = value;
		}

		@Override
		public void encode(OutputStream output) {
			Encoding.encode(output, value);
		}

		@Override
		public int encodedLength() {
			return Encoding.encodedLength(value);
		}

		@Override
		public String toString() {
			return String.format("StringPayload (Value = %s)", value);
		}

		@Override
		public String toUTF8String() {
			return value;
		}
	}

	private final Payload payload;

	/**
	 * Gets an instance of type {@link Value} which represents a <i>null</i>
	 * value.
	 */
	public final static Value NULL = new Value();

	/**
	 * Initializes a new instance of the {@link Value} class, with the
	 * {@link Payload} initialized to a new instance of {@link NullPayload}.
	 */
	private Value() {
		payload = new NullPayload();
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type <i>boolean</i>.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(boolean value) {
		payload = new BooleanPayload(value);
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type <i>double</i>.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(double value) {
		payload = new DoublePayload(value);
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type <i>double</i>.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(float value) {
		payload = new DoublePayload(value);
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type <i>long</i>.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(int value) {
		payload = new LongPayload(value);
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type <i>long</i>.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(long value) {
		payload = new LongPayload(value);
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type {@link Octets}.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(Octets value) {
		payload = new OctetsPayload(value);
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type {@link Oid}.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(Oid value) {
		payload = new OidPayload(value);
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type <i>long</i>.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(short value) {
		payload = new LongPayload(value);
	}

	/**
	 * Initializes a new instance of the {@link Value} class with a value of
	 * type <i>String</i>.
	 * 
	 * @param value
	 *            The value to store.
	 */
	public Value(String value) {
		payload = new StringPayload(value);
	}

	/**
	 * Encodes the value and writes it to the passed output stream.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 */
	public void encode(OutputStream output) {
		payload.encode(output);
	}

	/**
	 * Gets the encoded length of the value.
	 * 
	 * @return The encoded length of the value.
	 */
	public int encodedLength() {
		return payload.encodedLength();
	}

	/**
	 * Gets value indicating whether this instance contains a valid payload.
	 * 
	 * @return <i>true</i> when the payload does not contain any valid data.
	 */
	public boolean isNull() {
		return payload.type() == UniversalType.NULL;
	}

	/**
	 * Gets the value of type <i>boolean</i> or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.BOOLEAN}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public boolean toBoolean() throws UnsupportedOperationException {
		return payload.toBoolean();
	}

	/**
	 * Gets the value of type <i>boolean</i> or the provided default value if
	 * the value is of a different type and cannot be converted. This method
	 * must only be called if the {@link type} is {@link UniversalType.BOOLEAN}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public boolean toBoolean(boolean defaultValue) {
		if (type() == UniversalType.BOOLEAN) {
			return toBoolean();
		} else {
			return defaultValue;
		}
	}

	/**
	 * Gets the value of type <i>double</i> or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.REAL}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public double toDouble() {
		return payload.toDouble();
	}

	/**
	 * Gets the value of type <i>double</i> or the provided default value if the
	 * value is of a different type and cannot be converted. This method must
	 * only be called if the {@link type} is {@link UniversalType.INTEGER}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public double toDouble(double defaultValue) {
		if (type() == UniversalType.REAL) {
			return toDouble();
		} else {
			return defaultValue;
		}
	}

	/**
	 * Gets the value of type <i>float</i> or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.REAL}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public float toFloat() {
		return (float) payload.toDouble();
	}

	/**
	 * Gets the value of type <i>float</i> or the provided default value if the
	 * value is of a different type and cannot be converted. This method must
	 * only be called if the {@link type} is {@link UniversalType.REAL}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public float toFloat(float defaultValue) {
		return (float) toDouble(defaultValue);
	}

	/**
	 * Gets the value of type <i>int</i> or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.INTEGER}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public int toInt() {
		return (int) payload.toLong();
	}

	/**
	 * Gets the value of type <i>int</i> or the provided default value if the
	 * value is of a different type and cannot be converted. This method must
	 * only be called if the {@link type} is {@link UniversalType.INTEGER}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public int toInt(int defaultValue) {
		if (type() == UniversalType.INTEGER) {
			return toInt();
		} else {
			return defaultValue;
		}
	}

	/**
	 * Gets the value of type <i>long</i> or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.INTEGER}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public long toLong() throws UnsupportedOperationException {
		return payload.toLong();
	}

	/**
	 * Gets the value of type <i>long</i> or the provided default value if the
	 * value is of a different type and cannot be converted. This method must
	 * only be called if the {@link type} is {@link UniversalType.INTEGER}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public long toLong(long defaultValue) {
		if (type() == UniversalType.INTEGER) {
			return toLong();
		} else {
			return defaultValue;
		}
	}

	/**
	 * Gets the value of type {@link Octets} or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.OCTETSTRING}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public Octets toOctets() {
		return payload.toOctets();
	}

	/**
	 * Gets the value of type {@link Octets} or the provided default value if
	 * the value is of a different type and cannot be converted. This method
	 * must only be called if the {@link type} is
	 * {@link UniversalType.OCTETSTRING}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public Octets toOctets(Octets defaultValue) {
		if (type() == UniversalType.OCTETSTRING) {
			return toOctets();
		} else {
			return defaultValue;
		}
	}

	/**
	 * Gets the value of type {@link Oid} or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.RELATIVEOID}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public Oid toOid() {
		return payload.toOid();
	}

	/**
	 * Gets the value of type {@link Oid} or the provided default value if the
	 * value is of a different type and cannot be converted. This method must
	 * only be called if the {@link type} is {@link UniversalType.RELATIVEOID}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public Oid toOid(Oid defaultValue) {
		if (type() == UniversalType.RELATIVEOID) {
			return toOid();
		} else {
			return defaultValue;
		}
	}

	/**
	 * Gets the value of type <i>short</i> or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.INTEGER}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public short toShort() {
		return (short) payload.toLong();
	}

	/**
	 * Gets the value of type <i>short</i> or the provided default value if the
	 * value is of a different type and cannot be converted. This method must
	 * only be called if the {@link type} is {@link UniversalType.INTEGER}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public short toShort(short defaultValue) {
		return (short) toLong(defaultValue);
	}

	@Override
	public String toString() {
		return payload.toString();
	}

	/**
	 * Gets the value of type <i>String</i> or throws an
	 * <i>UnsupportedOperationException</i>, if the value is of a different
	 * type. This method must only be called if the {@link type} is
	 * {@link UniversalType.UTF8STRING}.
	 * 
	 * @return The nested value.
	 * @throws UnsupportedOperationException
	 *             Thrown if the nested value is of a different type.
	 */
	public String toUTF8String() {
		return payload.toUTF8String();
	}

	/**
	 * Gets the value of type <i>string</i> or the provided default value if the
	 * value is of a different type and cannot be converted. This method must
	 * only be called if the {@link type} is {@link UniversalType.UTF8STRING}.
	 * 
	 * @param defaultValue
	 *            The default value to return if the payload is of a different
	 *            type.
	 * @return The boolean value.
	 */
	public String toUTF8String(String defaultValue) {
		if (type() == UniversalType.UTF8STRING) {
			return toUTF8String();
		} else {
			return defaultValue;
		}
	}

	/**
	 * Gets the {@link UniversalType} of the stored value.
	 * 
	 * @return The {@link UniversalType} of the stored value.
	 */
	public UniversalType type() {
		return payload.type();
	}

	/**
	 * Gets the {@link Tag} representing the value type.
	 * 
	 * @return The {@link Tag} representing the value type.
	 */
	public Tag universalTag() {
		return payload.universalTag();
	}
}
