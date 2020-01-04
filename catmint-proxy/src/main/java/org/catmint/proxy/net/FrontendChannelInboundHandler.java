package org.catmint.proxy.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.catmint.proxy.execution.MySQLCommandExecutionEngine;
import org.catmint.proxy.frontend.MySQLAuthenticator;
import org.catmint.proxy.packet.MySQLHandshakePacket;
import org.catmint.proxy.packet.MySQLPacketPayload;
import org.catmint.proxy.support.ConnectionIdGenerator;

import java.sql.SQLException;

/**
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
@Slf4j
public final class FrontendChannelInboundHandler extends ChannelInboundHandlerAdapter {

    private final MySQLAuthenticator authenticator = new MySQLAuthenticator();

    private final MySQLCommandExecutionEngine commandExecutor = new MySQLCommandExecutionEngine();

    private volatile boolean authorized;

    @Override
    public void channelActive(final ChannelHandlerContext context) {
        int connectionId = ConnectionIdGenerator.getInstance().nextId();
        context.writeAndFlush(new MySQLHandshakePacket(connectionId, authenticator.getAuthPluginData()));
    }

    @Override
    public void channelRead(final ChannelHandlerContext context, final Object message) {
        if (!authorized) {
            authorized = auth(context, (ByteBuf) message);
            return;
        }
        try {
            commandExecutor.executeCommand(context, (ByteBuf) message);
        } catch (SQLException e) {
            log.error("fail to execute command", e);
            // TODO 异常处理
        }
    }

    private boolean auth(final ChannelHandlerContext context, final ByteBuf message) {
        MySQLPacketPayload payload = new MySQLPacketPayload(message);
        return authenticator.auth(context, payload);
    }

    @Override
    @SneakyThrows
    public void channelInactive(final ChannelHandlerContext context) {
        context.fireChannelInactive();
//        databaseProtocolFrontendEngine.release(backendConnection);
//        backendConnection.close(true);
//        ChannelThreadExecutorGroup.getInstance().unregister(context.channel().id());
    }

    @Override
    public void channelWritabilityChanged(final ChannelHandlerContext context) {
//        if (context.channel().isWritable()) {
//            backendConnection.getResourceSynchronizer().doNotify();
//        }
    }
}
