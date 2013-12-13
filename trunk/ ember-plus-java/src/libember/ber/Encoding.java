package libember.ber;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import libember.util.Assert;
import libember.util.InputStream;
import libember.util.OutputStream;

/**
 * This class provides encoding and decoding methods for all types supported by
 * this library. Additionally, it also provides methods to compute the encoded
 * length of a value. In order to be able to omit the type name in the decode
 * methods, they expect a default value which is simply used to specify the data
 * type. To simplify the usage, use the constants defined within this class,
 * like {@link Encoding.BOOLEAN}.
 * <code>final double result = Encoding.decode(stream, encodedLength, Encoding.DOUBLE);</code>
 */
public final class Encoding {
	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link boolean}.
	 */
	public static final boolean BOOLEAN = false;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link long}.
	 */
	public static final long LONG = 0;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link int}.
	 */
	public static final int INT = 0;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link short}.
	 */
	public static final short SHORT = 0;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link char}.
	 */
	public static final char CHAR = 0;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link double}.
	 */
	public static final double DOUBLE = 0.0;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link float}.
	 */
	public static final float FLOAT = 0.0F;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link String}.
	 */
	public static final String STRING = "";

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link Oid}.
	 */
	public static final Oid OID = Oid.EMPTY;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link Tag}.
	 */
	public static final Tag TAG = Tag.ZERO;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link Octets}.
	 */
	public static final Octets OCTETS = Octets.EMPTY;

	/**
	 * Pass this constant to the {@link decode} method in order to decode a
	 * value of type {@link Length}.
	 */
	public static final Length LENGTH = Length.INDEFINITE;

	/**
	 * Decodes an integer value.
	 * 
	 * @param input
	 *            The {@link InputStream} to read the encoded data from.
	 * @param length
	 *            The encoded length of the value to decode.
	 * @param isSigned
	 *            Indicates whether the encoded value is signed or unsigned.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 */
	private static long decodeLong(InputStream input, int length,
			boolean isSigned) throws NoSuchElementException {
		long value = 0;
		long read = 0;

		for (long byteCount = 0; byteCount < length; byteCount++) {
			read = input.peek();
			input.consume();

			if (byteCount == 0 && (read & 0x80) != 0 && isSigned) {
				read -= 0x100;
			}

			value = (value << 8) | read;
		}

		return value;
	}

	/**
	 * Determines the encoded length of an integer value that may either be
	 * signed or unsigned. This distinction is required when encoding parts of a
	 * real value.
	 * 
	 * @param value
	 *            The value to encode.
	 * @param isSigned
	 *            Indicates whether {@link value} is signed or unsigned.
	 * @return The encoded length of {@link value}.
	 */
	private static int encodedLength(long value, boolean isSigned) {
		long mask = isSigned ? 0xFF80000000000000L : 0xFF00000000000000L;
		int length = 8;

		if (value < 0) {
			for (; (value & mask) == mask && length > 1; mask >>= 8)
				length--;
		} else {
			long top = 0x8000000000000000L;

			for (; (value & mask) == 0 && length > 1; mask >>= 8) {
				top >>= 8;
				length--;
			}

			if ((value & top) != 0 && isSigned)
				length++;
		}

		return length;
	}

	/**
	 * Determines the encoded length of a singed integer value.
	 * 
	 * @param value
	 *            The value to encode.
	 * @param length
	 *            The number of bytes the value consumes when being encoded.
	 * @return The encoded length of {@link value}.
	 */
	private static int encodedLength(long value, int length) {
		final boolean isPositive = value >= 0;
		final long unsigned = Math.abs(value);

		long mask = (~((1L << (((length - 1L) * 8L) - 1L)) - 1L));

		while ((length > 1) && ((unsigned & mask) == (isPositive ? 0 : mask))) {
			length -= 1;
			mask >>= 8;
		}

		if (isPositive && ((unsigned >> (length * 8 - 1)) != 0)) {
			length += 1;
		}

		return length;
	}

	/**
	 * Encodes an integer value with a specified length.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded data to.
	 * @param value
	 *            The value to encode.
	 * @param length
	 *            The number of bytes to encode.
	 */
	private static void encodeLong(OutputStream output, long value, int length) {
		long bits = length * 8;

		while (bits > 0) {
			bits -= 8;
			output.append((int) ((value >> bits) & 0xFF));
		}
	}

	/**
		 * Decodes a value of type <i>boolean</i>. Since the length is not required, it must not be passed.
		 * @param input The {@link InputStream to decode the value from.
		 * @param type Since all methods use the same name, a default value must be passed to 
		 * 			determine the type of the value to decode. The {@link Encoding} class provides 
		 * 			constants for each type that is supported. The name of the constant equals
		 * 			the typename, and is written in capital letters.
		 * @return The decoded value.
		 * @throws NoSuchElementException Thrown if {@link input} contains no more elements.
		 * @throws NullPointerException Thrown if {@link input} is <i>null</i>. 
		 */
		public static boolean decode(InputStream input, boolean type)
				throws NoSuchElementException, NullPointerException {
			Assert.AssertNotNull(input, "input");
	
			final int value = input.peek();
	
			return value != 0;
		}

	/**
	 * Decodes a value of type <i>char</i>.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param encodedLength
	 *            The encoded length of this value.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static char decode(InputStream input, int encodedLength, char type)
			throws NoSuchElementException, NullPointerException {
		return (char) decode(input, encodedLength, LONG);
	}

	/**
	 * Decodes a value of type <i>double</i>.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param encodedLength
	 *            The encoded length of this value.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static double decode(InputStream input, int encodedLength,
			double type) throws NoSuchElementException, NullPointerException {
		Assert.AssertNotNull(input, "input");

		if (encodedLength == 0) {
			return 0.0;
		}

		final int preamble = input.peek();

		input.consume();

		if (encodedLength == 1) {
			if (preamble == 0x40) {
				return Double.POSITIVE_INFINITY;
			} else if (preamble == 0x41) {
				return Double.NEGATIVE_INFINITY;
			}
		}

		final int exponentLength = 1 + (preamble & 3);
		final int sign = (preamble & 0x40);
		final int ff = (preamble >> 2) & 3;

		long exponent = decodeLong(input, exponentLength, true);
		long mantissa = decodeLong(input, encodedLength - exponentLength - 1,
				false) << ff;

		while ((mantissa & 0x7FFFF00000000000L) == 0x0)
			mantissa <<= 8;

		while ((mantissa & 0x7FF0000000000000L) == 0x0)
			mantissa <<= 1;

		mantissa &= 0x0FFFFFFFFFFFFFL;

		final long bits = ((exponent + 1023) << 52) | mantissa
				| (sign != 0 ? 0x8000000000000000L : 0);

		return Double.longBitsToDouble(bits);
	}

	/**
	 * Decodes a value of type <i>float</i>.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param encodedLength
	 *            The encoded length of this value.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static float decode(InputStream input, int encodedLength, float type)
			throws NoSuchElementException, NullPointerException {
		return (float) decode(input, encodedLength, DOUBLE);
	}

	/**
	 * Decodes a value of type <i>int</i>.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param encodedLength
	 *            The encoded length of this value.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static int decode(InputStream input, int encodedLength, int type)
			throws NoSuchElementException, NullPointerException {
		return (int) decode(input, encodedLength, LONG);
	}

	/**
	 * Decodes a value of type <i>long</i>. Since the length is not required, it
	 * must not be passed.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static long decode(InputStream input, int encodedLength, long type)
			throws NoSuchElementException, NullPointerException {
		Assert.AssertNotNull(input, "input");

		long value = 0;

		for (int i = 0; i < encodedLength; i++) {
			final long part = input.peek();

			if ((i == 0) && (part == 0x80)) {
				value = part;
				value -= 0x100;
			} else {
				value = (value << 8) | part;
			}
			input.consume();
		}

		return value;
	}

	/**
	 * Decodes a value of type {@link Octets}.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param encodedLength
	 *            The encoded length of this value.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static Octets decode(InputStream input, int encodedLength,
			Octets type) throws NoSuchElementException, NullPointerException {
		Assert.AssertNotNull(input, "input");

		final Vector<Integer> data = new Vector<Integer>(encodedLength);

		for (int i = 0; i < encodedLength; ++i) {
			data.add(input.peek() & 0xFF);
			input.consume();
		}

		return new Octets(data);
	}

	/**
	 * Decodes a value of type {@link Oid}.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param encodedLength
	 *            The encoded length of this value.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static Oid decode(InputStream input, int encodedLength, Oid type)
			throws NoSuchElementException, NullPointerException {
		Assert.AssertNotNull(input, "input");

		final Vector<Integer> elements = new Vector<Integer>();

		while (encodedLength > 0) {
			final int number = (int) MultiByte.decode(input);
			final int numberLength = MultiByte.encodedLength(number);

			elements.add(Integer.valueOf(number));
			encodedLength -= numberLength;
		}

		return new Oid(elements);
	}

	/**
	 * Decodes a value of type <i>short</i>.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param encodedLength
	 *            The encoded length of this value.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static short decode(InputStream input, int encodedLength, short type)
			throws NoSuchElementException, NullPointerException {
		return (short) decode(input, encodedLength, LONG);
	}

	/**
	 * Decodes a value of type <i>String</i>.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param encodedLength
	 *            The encoded length of this value.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static String decode(InputStream input, int encodedLength,
			String type) throws NoSuchElementException, NullPointerException {
		Assert.AssertNotNull(input, "input");

		final byte[] encodedString = new byte[encodedLength];

		for (int i = 0; i < encodedLength; ++i) {
			encodedString[i] = (byte) input.peek();
			input.consume();
		}

		try {
			return new String(encodedString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * Decodes a value of type {@link Length}.
	 * 
	 * @param input
	 *            The {@link InputStream} to decode the value from.
	 * @param type
	 *            Since all methods use the same name, a default value must be
	 *            passed to determine the type of the value to decode. The
	 *            {@link Encoding} class provides constants for each type that
	 *            is supported. The name of the constant equals the typename,
	 *            and is written in capital letters.
	 * @return The decoded value.
	 * @throws NoSuchElementException
	 *             Thrown if {@link input} contains no more elements.
	 * @throws NullPointerException
	 *             Thrown if {@link input} is <i>null</i>.
	 */
	public static Length decode(InputStream input, Length type)
			throws NoSuchElementException, NullPointerException {
		Assert.AssertNotNull(input, "input");

		int length = input.peek();

		input.consume();

		if ((length & 0x80) != 0) {
			long bytes = length & 0x7F;

			if (bytes == 0) {
				return Length.INDEFINITE;
			} else {
				length = 0;

				for (/* Nothing */; bytes > 0; bytes--) {
					length = ((length << 8) | input.peek());
					input.consume();
				}
			}
		}

		return new Length(length);
	}

	/**
		 * Decodes a {@link Tag}. Since the length is not required, it must not be passed.
		 * @param input The {@link InputStream to decode the value from.
		 * @param type Since all methods use the same name, a default value must be passed to 
		 * 			determine the type of the value to decode. The {@link Encoding} class provides 
		 * 			constants for each type that is supported. The name of the constant equals
		 * 			the typename, and is written in capital letters.
		 * @return The decoded value.
		 * @throws NoSuchElementException Thrown if {@link input} contains no more elements.
		 * @throws NullPointerException Thrown if {@link input} is <i>null</i>. 
		 */
		public static Tag decode(InputStream input, Tag type)
				throws NoSuchElementException, NullPointerException {
			Assert.AssertNotNull(input, "input");
	
			final int peek = input.peek();
	
			input.consume();
	
			final int preamble = peek & 0xE0;
	
			int number = peek & 0x1F;
	
			if (number == 0x1F)
				number = (int) MultiByte.decode(input);
	
			return new Tag(preamble, number);
		}

	/**
	 * Encodes a value of type <i>boolean</i>.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>.
	 */
	public static void encode(OutputStream output, boolean value)
			throws NullPointerException {
		Assert.AssertNotNull(value, "value");

		output.append(value ? 0xFF : 0x00);
	}

	/**
	 * Encodes a value of type <i>char</i>.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>.
	 */
	public static void encode(OutputStream output, char value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");

		final long valueAsLong = value;

		encode(output, valueAsLong);
	}

	/**
	 * Encodes a value of type <i>double</i>.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>.
	 */
	public static void encode(OutputStream output, double value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");

		if (Double.POSITIVE_INFINITY == value) {
			output.append((byte) 0x40);
		} else if (Double.NEGATIVE_INFINITY == value) {
			output.append((byte) 0x41);
		} else {
			final long bits = Double.doubleToLongBits(value);

			if (bits != 0) {
				final long exponent = ((0x7FF0000000000000L & bits) >> 52L) - 1023;
				final int exponentLength = encodedLength(exponent, true);
				final int preamble = (0x80 | (exponentLength - 1) | (((bits & 0x8000000000000000L) != 0) ? 0x40
						: 0x00));

				long mantissa = (0x000FFFFFFFFFFFFFL & bits) | 0x0010000000000000L;

				while ((mantissa & 0xFF) == 0x00) {
					mantissa >>= 8;
				}

				while ((mantissa & 0x01) == 0x00) {
					mantissa >>= 1;
				}

				output.append(preamble);
				encodeLong(output, exponent, exponentLength);
				encodeLong(output, mantissa, encodedLength(mantissa, false));
			}
		}
	}

	/**
	 * Encodes a value of type <i>float</i>.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>.
	 */
	public static void encode(OutputStream output, float value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");

		final double valueAsDouble = value;

		encode(output, valueAsDouble);
	}

	/**
	 * Encodes a value of type <i>int</i>.
	 * 
	 * @param output
	 *            The {@link output} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>.
	 */
	public static void encode(OutputStream output, int value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");

		final long valueAsLong = value;

		encode(output, valueAsLong);
	}

	/**
	 * Encodes a value of type {@link Length}.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if either {@link output} or {@link value} is
	 *             <i>null</i>.
	 */
	public static void encode(OutputStream output, Length value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");
		Assert.AssertNotNull(value, "value");

		if (value.isIndefinite()) {
			output.append(0x80);
		} else {
			final int lengthValue = value.value();

			if (lengthValue <= 0x7F) {
				output.append(lengthValue);
			} else {
				output.append(0x80 | encodedLength(lengthValue));
				encode(output, lengthValue);
			}
		}
	}

	/**
	 * Encodes a value of type <i>long</i>.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>.
	 */
	public static void encode(OutputStream output, long value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");

		final int length = encodedLength(value);

		int bits = length * 8;

		while (bits > 0) {
			bits -= 8;
			output.append((int) ((value >> bits) & 0xFF));
		}
	}

	/**
	 * Encodes a value of type {@link Octets}.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if either {@link output} or {@link value} is
	 *             <i>null</i>.
	 */
	public static void encode(OutputStream output, Octets value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");
		Assert.AssertNotNull(value, "value");

		for (Iterator<Integer> it = value.iterator(); it.hasNext(); /* Nothing */) {
			output.append(it.next().byteValue());
		}
	}

	/**
	 * Encodes a value of type {@link Oid}.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if either {@link output} or {@link value} is
	 *             <i>null</i>.
	 */
	public static void encode(OutputStream output, Oid value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");
		Assert.AssertNotNull(value, "value");

		for (Iterator<Integer> it = value.iterator(); it.hasNext(); /* Nothing */) {
			final int number = it.next().intValue();

			MultiByte.encode(output, number);
		}
	}

	/**
	 * Encodes a value of type <i>short</i>.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if {@link output} is <i>null</i>.
	 */
	public static void encode(OutputStream output, short value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");

		final long valueAsLong = value;

		encode(output, valueAsLong);
	}

	/**
	 * Encodes a value of type {@link String}.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if either {@link output} or {@link value} is null.
	 */
	public static void encode(OutputStream output, String value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");
		Assert.AssertNotNull(value, "value");

		try {
			final byte[] encodedBytes = value.getBytes("UTF-8");

			for (int i = 0; i < encodedBytes.length; i++) {
				output.append(encodedBytes[i]);
			}
		} catch (UnsupportedEncodingException _) {
		}
	}

	/**
	 * Encodes a value of type {@link Tag}.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the value to.
	 * @param value
	 *            The value to encode.
	 * @throws NullPointerException
	 *             Thrown if either {@link output} or {@link value} is
	 *             <i>null</i>.
	 */
	public static void encode(OutputStream output, Tag value)
			throws NullPointerException {
		Assert.AssertNotNull(output, "output");
		Assert.AssertNotNull(value, "value");

		final int preamble = value.preamble() & 0xE0;
		final int number = value.number();

		if (number < 0x1F) {
			final int encodedTag = (preamble | (number & 0x1F));

			output.append(encodedTag);
		} else {
			output.append(preamble | 0x1F);
			MultiByte.encode(output, number);
		}
	}

/**
 * Determines the encoded length for a value of type <i>boolean</i>, which
 * is always 1.
 * 
 * @param value
 *            The value to get the encoded length for.
 * @return The encoded length of the passed value.
 */
public static int encodedLength(boolean value) {
	return 1;
}

/**
 * Determines the encoded length for a value of type <i>char</i>.
 * 
 * @param value
 *            The value to get the encoded length for.
 * @return The encoded length of the passed value.
 */
public static int encodedLength(char value) {
	final int length = Character.SIZE / 8;

	return encodedLength(value, length);
}

	/**
	 * Determines the encoded length for a value of type <i>double</i>.
	 * 
	 * @param value
	 *            The value to get the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(double value) {
		if (Double.isInfinite(value)) {
			return 1;
		} else {
			final long bits = Double.doubleToLongBits(value);
			int encodedLength = 0;

			if (bits != 0) {
				final long exponent = ((0x7FF0000000000000L & bits) >> 52L) - 1023;
				long mantissa = (0x000FFFFFFFFFFFFFL & bits) | 0x0010000000000000L;

				while ((mantissa & 0xFF) == 0xFF) {
					mantissa >>= 8;
				}

				while ((mantissa & 0x01) == 0x00) {
					mantissa >>= 1;
				}

				encodedLength = 1; // preamble
				encodedLength += Encoding.encodedLength(exponent, true);
				encodedLength += Encoding.encodedLength(mantissa, false);
			}

			return encodedLength;
		}
	}

	/**
	 * Determines the encoded length for a value of type <i>float</i>.
	 * 
	 * @param value
	 *            The value to get the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(float value) {
		final double valueAsDouble = value;

		return encodedLength(valueAsDouble);
	}

	/**
	 * Determines the encoded length for a value of type <i>int</i>.
	 * 
	 * @param value
	 *            The value to get the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(int value) {
		final int length = Integer.SIZE / 8;

		return encodedLength(value, length);
	}

	/**
	 * Determines the encoded length for a value of type {@link Length}, which
	 * is used when the content of a container is being encoded.
	 * 
	 * @param value
	 *            The value to get the encoded length for.
	 * @return The encoded length of the passed value.
	 * @throws NullPointerException
	 *             Thrown if {@link value} is <i>null</i>.
	 */
	public static int encodedLength(Length value) throws NullPointerException {
		Assert.AssertNotNull(value, "value");

		return 1 + ((value.value() < 0x80 || value.isIndefinite()) ? 0
				: encodedLength(value.value()));
	}

	/**
	 * Determines the encoded length for a value of type <i>long</i>.
	 * 
	 * @param value
	 *            The value to get the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(long value) {
		final int length = Long.SIZE / 8;

		return encodedLength(value, length);
	}

	/**
	 * Determines the encoded length for a value of type {@link Octets}.
	 * 
	 * @param value
	 *            The octets to get the encoded length for.
	 * @return The encoded length of the passed value.
	 * @throws NullPointerException
	 *             Thrown if {@link value} is <i>null</i>.
	 */
	public static int encodedLength(Octets value) throws NullPointerException {
		Assert.AssertNotNull(value, "value");

		return value.size();
	}

	/**
	 * Determines the encoded length for a value of type {@link Oid}.
	 * 
	 * @param value
	 *            The value to get the encoded length for.
	 * @return The encoded length of the passed value.
	 * @throws NullPointerException
	 *             Thrown if {@link value} is <i>null</i>.
	 */
	public static int encodedLength(Oid value) throws NullPointerException {
		Assert.AssertNotNull(value, "value");

		int length = 0;

		for (Iterator<Integer> it = value.iterator(); it.hasNext();) {
			final int number = it.next().intValue();
			length += MultiByte.encodedLength(number);
		}

		return length;
	}

	/**
	 * Determines the encoded length for a value of type <i>short</i>.
	 * 
	 * @param value
	 *            The value to get the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(short value) {
		final int length = Short.SIZE / 8;

		return encodedLength(value, length);
	}

	/**
	 * Determines the length for a value of type <i>String</i>. The string must
	 * be UTF-8 encoded.
	 * 
	 * @param value
	 *            The string to get the encoded length for.
	 * @return The encoded length of the passed value.
	 * @throws NullPointerException
	 *             Thrown if {@link value} is <i>null</i>.
	 */
	public static int encodedLength(String value) throws NullPointerException {
		Assert.AssertNotNull(value, "value");

		try {
			return value.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			return 0;
		}
	}

	/**
	 * Determines the encoded length for a value of type {@link Tag}.
	 * 
	 * @param value
	 *            The {@link Tag} to get the encoded length for.
	 * @return The encoded length of the passed {@link Tag}.
	 * @throws NullPointerException
	 *             Thrown if {@link tag} is <i>null</i>.
	 */
	public static int encodedLength(Tag value) throws NullPointerException {
		Assert.AssertNotNull(value, "value");

		return 1 + ((value.number() < 0x1F) ? 0 : MultiByte.encodedLength(value
				.number()));
	}
}
