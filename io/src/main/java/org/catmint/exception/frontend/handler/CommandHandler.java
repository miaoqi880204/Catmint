package org.catmint.io.frontend.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

/**
 * 前端命令 处理器
 *
 * @author Shuo Xiang
 */
public class CommandHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        ctx.close();
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
