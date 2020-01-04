package org.catmint.proxy.packet;

/**
 * 数据库报文
 *
 * @author Shuo Xiang
 */
public interface DatabasePacket<T extends PacketPayload> {

    /**
     * 写入 ByteBuf
     *
     * @param payload
     */
    void write(T payload);
}