package org.catmint.io.protocol;

/**
 * @author Shuo Xiang
 */
public class PacketChunkParserAdapter<T extends PacketChunk> implements PacketChunkParser<T> {

    @Override
    public byte parseByte(T chunk) {
        return 0;
    }

    @Override
    public byte[] parseBytes(T chunk) {
        return new byte[0];
    }
}
