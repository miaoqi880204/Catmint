package org.catmint.io.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.catmint.exception.io.PacketException;
import org.catmint.io.protocol.mysql.MySQLPacket;
import org.catmint.io.protocol.mysql.WrapperPacket;
import org.catmint.io.utilities.ByteBufUtils;
import org.catmint.io.utilities.MessageFormatUtils;

import java.util.List;

/**
 * 报文 解码器
 *
 * @author Shuo Xiang
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < MySQLPacket.PACKET_HEADER_SIZE) {
            return;
        }
        in.markReaderIndex();
        int packetLength = ByteBufUtils.readUpper3(in);

        // 过载保护
        if (packetLength > MySQLPacket.MAX_PACKET_SIZE) {
            throw new PacketException(MessageFormatUtils.format("packet size is over the limit {}", MySQLPacket.MAX_PACKET_SIZE));
        }
        byte packetId = in.readByte();
        if (in.readableBytes() < packetLength) {
            // TODO 半包回溯
            in.resetReaderIndex();
            return;
        }
        WrapperPacket packet = new WrapperPacket();
        packet.setPayloadLength(packetLength);
        packet.setSequenceId(packetId);
        ByteBuf byteBuf = in.readBytes(packetLength);
        byte[] data = new byte[packetLength];
        byteBuf.readBytes(data);
        packet.setData(data);
        out.add(packet);
    }
}
