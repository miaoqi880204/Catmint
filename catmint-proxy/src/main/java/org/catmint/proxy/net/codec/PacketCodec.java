package org.catmint.proxy.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.protocol.DatabaseProtocolEngine;

import java.util.List;

/**
 * 报文 解码器
 *
 * @author Shuo Xiang
 */
@Slf4j
@RequiredArgsConstructor
public class PacketCodec extends ByteToMessageCodec<DatabasePacket> {

    private final DatabaseProtocolEngine databaseProtocolEngine;

    @Override
    protected void decode(final ChannelHandlerContext context, final ByteBuf in, final List<Object> out) {
        int readableBytes = in.readableBytes();
        if (!databaseProtocolEngine.getCodecEngine().isValidHeader( readableBytes )) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug( "Read from client {} : \n {}", context.channel().id().asShortText(), ByteBufUtil.prettyHexDump( in ) );
        }
        databaseProtocolEngine.getCodecEngine().decode( context, in, out, readableBytes );
    }

    @Override
    protected void encode(final ChannelHandlerContext context, final DatabasePacket message, final ByteBuf out) {
        databaseProtocolEngine.getCodecEngine().encode( context, message, out );
        if (log.isDebugEnabled()) {
            log.debug( "Write to client {} : \n {}", context.channel().id().asShortText(), ByteBufUtil.prettyHexDump( out ) );
        }
    }
}
