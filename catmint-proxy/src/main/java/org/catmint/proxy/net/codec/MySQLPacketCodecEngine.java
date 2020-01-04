package org.catmint.proxy.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.catmint.proxy.packet.MySQLPacket;
import org.catmint.proxy.packet.MySQLPacketPayload;

import java.util.List;

/**
 * @author Shuo Xiang
 */
public final class MySQLPacketCodecEngine implements DatabasePacketCodecEngine<MySQLPacket> {

    @Override
    public boolean isValidHeader(final int readableBytes) {
        return readableBytes > MySQLPacket.PAYLOAD_LENGTH + MySQLPacket.SEQUENCE_LENGTH;
    }

    @Override
    public void decode(final ChannelHandlerContext context, final ByteBuf in, final List<Object> out, final int readableBytes) {
        int payloadLength = in.markReaderIndex().readMediumLE();
        int realPacketLength = payloadLength + MySQLPacket.PAYLOAD_LENGTH + MySQLPacket.SEQUENCE_LENGTH;
        if (readableBytes < realPacketLength) {
            in.resetReaderIndex();
            return;
        }
        out.add(in.readRetainedSlice(payloadLength + MySQLPacket.SEQUENCE_LENGTH));
    }

    @Override
    public void encode(final ChannelHandlerContext context, final MySQLPacket message, final ByteBuf out) {
        try (MySQLPacketPayload payload = new MySQLPacketPayload(context.alloc().buffer())) {
            message.write(payload);
            out.writeMediumLE(payload.getByteBuf().readableBytes());
            out.writeByte(message.getSequenceId());
            out.writeBytes(payload.getByteBuf());
        }
    }

    @Override
    public MySQLPacketPayload createPacketPayload(final ByteBuf message) {
        return new MySQLPacketPayload(message);
    }
}
