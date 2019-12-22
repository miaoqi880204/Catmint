package org.catmint.io.protocol;

/**
 * 报文包分块高低位解析器
 *
 * @author Shuo Xiang
 */
public interface PacketChunkUBParser<T extends PacketChunk> extends PacketChunkParser<T> {

    /**
     * 根据协议解析报文包分块的高位
     *
     * @param chunk
     * @return
     */
    byte[] parseUpper(T chunk, int upper);

    /**
     * 根据协议解析报文包分块的低位
     *
     * @param chunk
     * @return
     */
    byte[] parseLower(T chunk, int lower);
}
