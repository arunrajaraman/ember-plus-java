package libember.s101;

import java.util.Iterator;
import java.util.Vector;

import libember.util.Assert;

/**
 * A S101 Stream decoder. The constructor of this class expects an instance of
 * type {@link StreamDecoderListener}, which will be notified when a new s101
 * packet has been decoded.
 */
public final class StreamDecoder {
	private final StreamDecoderListener listener;
	private final Vector<Integer> buffer = new Vector<Integer>();
	private int crc;
	private boolean escape;
	private boolean isInFrame;

	/**
	 * Initializes a new instance of the {@link StreamDecoder} class.
	 * 
	 * @param listener
	 *            The listener to notify when a s101 packet has been decoded.
	 */
	public StreamDecoder(StreamDecoderListener listener) {
		this.listener = listener;
	}

	/**
	 * Gets a value indicating whether the buffer is empty.
	 * 
	 * @return <i>true</i> if the input buffer is empty.
	 */
	private boolean isEmpty() {
		return buffer.isEmpty();
	}

	/**
	 * Notifies the registered listener about a decoded s101 packet.
	 * 
	 * @param i
	 *            An {@link Iterator} providing access to the decoded s101 data.
	 */
	private void notify(Iterator<Integer> i) {
		if (listener != null)
			listener.messageDecoded(i);
	}

	/**
	 * Resets the decoder state.
	 * 
	 * @param isInFrame
	 *            The value to set the {@link isInFrame} field to.
	 */
	private void reset(boolean isInFrame) {
		this.isInFrame = isInFrame;
		buffer.clear();
		escape = false;
		crc = 0xFFFF;
	}

	/**
	 * Parses a single byte.
	 * 
	 * @param input
	 *            The byte to parse.
	 */
	public void read(int input) {
		if (isInFrame == false && isEmpty()) {
			if (input == FrameByte.BOF.value()) {
				reset(true);
			}
		} else {
			if (escape) {
				final int b = ((input ^ FrameByte.XOR.value()) & 0xFF);

				escape = false;
				buffer.add(Integer.valueOf(b));
				crc = Crc16.sample(crc, b);
			} else {
				if (input == FrameByte.BOF.value()) {
					reset(true);
				} else if (input == FrameByte.EOF.value()) {
					if (crc == 0xF0B8) {
						notify(new ViewIterator<Integer>(buffer.iterator(),
								buffer.size() - 2));
					}
					reset(false);
				} else if (input == FrameByte.CE.value()) {
					escape = true;
				} else {
					buffer.add(input & 0xFF);
					crc = Crc16.sample(crc, input & 0xFF);
				}
			}
		}
	}

	/**
	 * Decodes a collection of integer values.
	 * 
	 * @param i
	 *            The collection to iterate.
	 * @throws NullPointerException
	 *             Thrown if {@link i} is <i>null</i>.
	 */
	public void read(Iterable<Integer> i) throws NullPointerException {
		Assert.AssertNotNull(i, "i");

		for (int input : i) {
			read(input);
		}
	}

	/**
	 * Traverses the provided {@link Iterator} and parses its values.
	 * 
	 * @param i
	 *            The iterator to traverse.
	 */
	public void read(Iterator<Integer> i) {
		while (i.hasNext()) {
			read(i.next());
		}
	}

	/**
	 * Resets the decoder state.
	 */
	public void reset() {
		reset(false);
	}
}
