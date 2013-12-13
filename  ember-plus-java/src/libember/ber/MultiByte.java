package libember.ber;

import java.util.NoSuchElementException;

import libember.util.InputStream;
import libember.util.OutputStream;

/**
 * This class provides encoding and decoding methods for multibyte integer
 * types, which are used to decode an instance of type {@link Oid} and the
 * number of a {@link Tag}, for example. Since this class is not public, it does
 * not evaluate the provided arguments and assumes they are valid.
 */
final class MultiByte {
	/**
	 * Encodes a value of type <i>long</i>, but assumes it to be unsigned.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded data to.
	 * @param value
	 *            The value to encode.
	 * @param bitWords
	 *            The number of bit-words the value has. This value is computed
	 *            by dividing the number bits the value uses by seven, like:
	 *            {@code final int bitWords = (Long.SIZE / 7);}
	 */
	private static void encode(OutputStream output, long value, int bitWords) {
		for (int i = 0; i < bitWords; ++i) {
			final long wordPos = (7 * (bitWords - i));
			final long mask = ~((1 << wordPos) - 1);

			if ((value & mask) != 0) {
				final long word = ((value >> wordPos) & 0x7F);

				output.append((int) (word | 0x80));
			}
		}
		output.append((int) (value & 0x7F));
	}

	/**
	 * Computes the encoded length of a value of type <i>long</i>, but assumes
	 * it to be unsigned.
	 * 
	 * @param value
	 *            The value to get the encoded length for.
	 * @param bitWords
	 *            The number of bit-words the value has. This value is computed
	 *            by dividing the number bits the value uses by seven, like:
	 *            {@code final int bitWords = (Long.SIZE / 7);}
	 * @return The encoded length of the passed value.
	 */
	private static int encodedLength(long value, int bitWords) {
		int i = 1;

		while (i <= bitWords) {
			final long mask = ~((1 << (7 * i)) - 1);

			if ((value & mask) == 0) {
				break;
			}
			i += 1;
		}
		return i;
	}

	/**
	 * Decodes a value of type <i>long</i>, but assumes it to be unsigned.
	 * 
	 * @param input
	 *            The {@link OutputStream} to read the encoded data from.
	 * @return The decoded value.
	 * @exception NoSuchElementException
	 *                Thrown if {@link input} has no more elements.
	 */
	public static long decode(InputStream input) throws NoSuchElementException {
		long result = 0;
		long b = 0;

		do {
			b = input.peek();
			input.consume();
			result = (result << 7) | (b & 0x7F);
		} while ((b & 0x80) != 0);

		return result;
	}

	/**
	 * Encodes a value of type <i>char</i>, but assumes it to be unsigned.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded value to.
	 * @param value
	 *            The value to encode.
	 */
	public static void encode(OutputStream output, char value) {
		final int bitWords = (Character.SIZE / 7);

		encode(output, value, bitWords);
	}

	/**
	 * Encodes a value of type <i>int</i>, but assumes it to be unsigned.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded value to.
	 * @param value
	 *            The value to encode.
	 */
	public static void encode(OutputStream output, int value) {
		final int bitWords = (Integer.SIZE / 7);

		encode(output, value, bitWords);
	}

	/**
	 * Encodes a value of type <i>long</i>, but assumes it to be unsigned.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded value to.
	 * @param value
	 *            The value to encode.
	 */
	public static void encode(OutputStream output, long value) {
		final int bitWords = (Long.SIZE / 7);

		encode(output, value, bitWords);
	}

	/**
	 * Encodes a value of type <i>short</i>, but assumes it to be unsigned.
	 * 
	 * @param output
	 *            The {@link OutputStream} to write the encoded value to.
	 * @param value
	 *            The value to encode.
	 */
	public static void encode(OutputStream output, short value) {
		final int bitWords = (Short.SIZE / 7);

		encode(output, value, bitWords);
	}

	/**
	 * Determines the encoded length of a value of type <i>char</i>, but assumes
	 * it to be unsigned.
	 * 
	 * @param value
	 *            The value to determine the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(char value) {
		final int bitWords = (Character.SIZE / 7);

		return encodedLength(value, bitWords);
	}

	/**
	 * Determines the encoded length of a value of type <i>int</i>, but assumes
	 * it to be unsigned.
	 * 
	 * @param value
	 *            The value to determine the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(int value) {
		final int bitWords = (Integer.SIZE / 7);

		return encodedLength(value, bitWords);
	}

	/**
	 * Determines the encoded length of a value of type <i>long</i>, but assumes
	 * it to be unsigned.
	 * 
	 * @param value
	 *            The value to determine the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(long value) {
		final int bitWords = (Long.SIZE / 7);

		return encodedLength(value, bitWords);
	}

	/**
	 * Determines the encoded length of a value of type <i>short</i>, but
	 * assumes it to be unsigned.
	 * 
	 * @param value
	 *            The value to determine the encoded length for.
	 * @return The encoded length of the passed value.
	 */
	public static int encodedLength(short value) {
		final int bitWords = (Short.SIZE / 7);

		return encodedLength(value, bitWords);
	}
}
