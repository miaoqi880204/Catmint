package org.catmint.io.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.catmint.io.protocol.mysql.MySQLPacket;
import org.catmint.io.utilities.ByteBufUtils;

import java.util.List;

/**
 * 报文 解码器
 *
 * @author Shuo Xiang
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < MySQLPacket.PACKET_HEADER_SIZE) {
            return;
        }
        in.markReaderIndex();
        int packetLength = ByteBufUtils.readUpper3(in);
        // 过载保护
        if (packetLength > MySQLPacket.MAX_PACKET_SIZE) {
            throw new IllegalArgumentException("Packet size over the limit " + MySQLPacket.MAX_PACKET_SIZE);
        }
        byte packetId = in.readByte();
        if (in.readableBytes() < packetLength) {
            // 半包回溯
            in.resetReaderIndex();
            return;
        }
        BinaryPacket packet = new BinaryPacket();
        packet.packetLength = packetLength;
        packet.packetId = packetId;
        // data will not be accessed any more,so we can use this array safely
        packet.data = in.readBytes(packetLength).array();
        if (packet.data == null || packet.data.length == 0) {
            logger.error("get data errorMessage,packetLength=" + packet.packetLength);
        }
        out.add(packet);
    }
}
