package org.catmint.proxy.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.PacketPayload;

import java.util.List;

/**
 * 数据报文编码引擎
 *
 * @author Shuo Xiang
 */
public interface DatabasePacketCodecEngine<T extends DatabasePacket> {

    /**
     * 校验是否有效的报文头
     *
     * @param readableBytes
     * @return
     */
    boolean isValidHeader(int readableBytes);

    /**
     * 解码
     *
     * @param context
     * @param in
     * @param out
     * @param readableBytes
     */
    void decode(ChannelHandlerContext context, ByteBuf in, List<Object> out, int readableBytes);

    /**
     * 编码
     *
     * @param context
     * @param message
     * @param out
     */
    void encode(ChannelHandlerContext context, T message, ByteBuf out);

    /**
     * 创建报文
     *
     * @param message
     * @return
     */
    PacketPayload createPacketPayload(ByteBuf message);
}
