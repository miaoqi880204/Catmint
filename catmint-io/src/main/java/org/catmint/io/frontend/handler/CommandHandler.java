package org.catmint.io.frontend.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.FormattedMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.catmint.io.protocol.mysql.*;

import java.io.UnsupportedEncodingException;

/**
 * 前端命令 处理器
 *
 * @author Shuo Xiang
 */
@Slf4j
public class CommandHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        WrapperPacket packet = (WrapperPacket) msg;
        byte commandId = packet.getData()[0];
        if (log.isDebugEnabled()) {
            log.debug("command [{}] received", commandId);
        }
        Commands command = Commands.parseId(commandId);
        switch (command) {
            case COM_QUIT:
                ctx.close();
                break;
            case COM_QUERY:
                try {
                    String sql = new String(packet.getData(), 1, packet.getData().length - 1, CharSets.UTF8.getName());
                    if (log.isDebugEnabled()) {
                        log.debug("sql [{}] received", sql);
                    }
                    // TODO 处理SQL
                } catch (UnsupportedEncodingException e) {
                    log.error("unsupported charset [{}]", CharSets.UTF8.getName());
                }
                ByteBuf byteBuf = ctx.alloc().buffer().writeBytes(OKPacket.OK);
                ctx.writeAndFlush(byteBuf);
                break;
            default:
                log.error("unknown command [{}]", commandId);
        }
//        BinaryPacket bin = (BinaryPacket) msg;
//        AuthPacket authPacket = new AuthPacket();
//        authPacket.read(bin);
//        // check password
//        if (!checkPassword(authPacket.password, authPacket.user)) {
//            failure(ErrorCode.ER_ACCESS_DENIED_ERROR, "Access denied for user '" + authPacket.user + "'");
//            return;
//        }
//        source.setUser(authPacket.user);
//        source.setSchema(authPacket.database);
//        source.setHost(((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress());
//        source.setPort(((InetSocketAddress) ctx.channel().remoteAddress()).getPort());
//        success(ctx);
    }
}
