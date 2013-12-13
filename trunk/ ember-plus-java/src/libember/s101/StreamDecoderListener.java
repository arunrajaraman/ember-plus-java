package libember.s101;

import java.util.Iterator;

/**
 * Public interface which is used to handle decoded s101 packets. Instances of
 * this class are passed to the constructor of {@link StreamDecoder}, which
 * invokes the {@link StreamDecoderListener.messageDecoded} method whenever a
 * S101 packet has been decoded.
 */
public interface StreamDecoderListener {
	/**
	 * This method is called by the {@link StreamDecoder} class when a s101
	 * packet has been decoded completely.
	 * 
	 * @param i
	 *            An {@link Iterator} to loop through the decoded s101 packet.
	 */
	void messageDecoded(Iterator<Integer> i);
}
