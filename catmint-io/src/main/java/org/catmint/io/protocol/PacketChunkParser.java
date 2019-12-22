package org.catmint.io.protocol;

/**
 * 报文包分块解析器
 *
 * @author Shuo Xiang
 */
public interface PacketChunkParser<T extends PacketChunk> {

    /**
     * 根据协议，将报文包分块解析为单字节
     *
     * @param chunk
     * @return
     */
    byte parseByte(T chunk);

    /**
     * 根据协议，将报文包分块解析为多字节
     *
     * @param chunk
     * @return
     */
    byte[] parseBytes(T chunk);
}
