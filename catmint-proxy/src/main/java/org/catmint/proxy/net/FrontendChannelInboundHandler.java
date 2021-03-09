package org.catmint.proxy.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.PacketPayload;
import org.catmint.proxy.packet.mysql.MySQLHandshakePacket;
import org.catmint.proxy.protocol.DatabaseProtocolEngine;
import org.catmint.proxy.support.ConnectionIdGenerator;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @params
 * @return
 * @throws
 * @date 2021/3/9 下午3:05
 */
@RequiredArgsConstructor
@Slf4j
public final class FrontendChannelInboundHandler extends ChannelInboundHandlerAdapter {
    private final DatabaseProtocolEngine databaseProtocolEngine;
    private volatile boolean authorized;

    @Override
    public void channelActive(final ChannelHandlerContext context) {
        int connectionId = ConnectionIdGenerator.getInstance().nextId();
        context.writeAndFlush( new MySQLHandshakePacket( connectionId, databaseProtocolEngine.getAuthEngine().getAuthPluginData() ) );
    }

    @Override
    public void channelRead(final ChannelHandlerContext context, final Object message) {
        if (!authorized) {
            authorized = auth( context, (ByteBuf) message );
            return;
        }
        try {
            Collection<DatabasePacket> databasePackets = databaseProtocolEngine.getCommandExecutionEngine().executeCommand( databaseProtocolEngine, (ByteBuf) message );
        } catch (SQLException e) {
            log.error( "fail to execute command", e );
        } catch (Throwable e) {
            log.error( "fail to execute command Throwable", e );
        }
    }

    private boolean auth(final ChannelHandlerContext context, final ByteBuf message) {
        PacketPayload payload = databaseProtocolEngine.getPacketPayload( message );
        return databaseProtocolEngine.getAuthEngine().auth( context, payload );
    }

    @Override
    @SneakyThrows
    public void channelInactive(final ChannelHandlerContext context) {
        context.fireChannelInactive();
    }

    @Override
    public void channelWritabilityChanged(final ChannelHandlerContext context) {
        System.out.println( 11 );
    }
}
