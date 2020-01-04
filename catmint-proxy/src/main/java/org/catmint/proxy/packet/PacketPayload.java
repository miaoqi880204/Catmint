package org.catmint.proxy.packet;

import io.netty.buffer.ByteBuf;

/**
 * 报文
 *
 * @author Shuo Xiang
 */
public interface PacketPayload extends AutoCloseable {

    /**
     * 获取 ByteBuf
     *
     * @return
     */
    ByteBuf getByteBuf();
}
