package org.catmint.io.frontend.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.catmint.io.protocol.mysql.*;
import org.catmint.io.utilities.RandomUtils;

/**
 * 前台权限 处理器
 *
 * @author Shuo Xiang
 * @since 15 12月 2019
 */
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    private byte[] seed;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 生成认证数据
        byte[] part1 = RandomUtils.randomBytes(8);
        byte[] part2 = RandomUtils.randomBytes(12);

        // 保存认证数据
        byte[] seed = new byte[part1.length + part2.length];
        System.arraycopy(part1, 0, seed, 0, part1.length);
        System.arraycopy(part2, 0, seed, part1.length, part2.length);
        this.seed = seed;

        HandshakePacket packet = new HandshakePacket();
        packet.setProtocolVersion(Versions.PROTOCOL_VERSION);
        packet.setServerVersion(Versions.SERVER_VERSION);
        log.info("connection id is {}", ctx.channel().id());
        packet.setConnectionId(ctx.channel().id().hashCode());  // TODO 获取连接ID
        packet.setSeedPart1(part1);
        packet.setCapabilities(getServerCapabilities());
        packet.setCharsetId((byte) (CharSets.UTF8.getId() & 0xff));    // TODO 默认 UTF-8
        packet.setStatusFlag(StatusFlags.AUTO_COMMIT.getFlag());
        packet.setSeedPart2(part2);

        packet.write(ctx);
    }

    private int getServerCapabilities() {
        int flag = 0;
        flag |= Capabilities.CLIENT_LONG_PASSWORD;
        flag |= Capabilities.CLIENT_FOUND_ROWS;
        flag |= Capabilities.CLIENT_LONG_FLAG;
        flag |= Capabilities.CLIENT_CONNECT_WITH_DB;
        // TODO 压缩标识
//        boolean usingCompress= MycatServer.getInstance().getConfig().getSystem().getUseCompression()==1 ;
//        if (usingCompress) {
//            flag |= Capabilities.CLIENT_COMPRESS;
//        }
        flag |= Capabilities.CLIENT_ODBC;
        flag |= Capabilities.CLIENT_LOCAL_FILES;
        flag |= Capabilities.CLIENT_IGNORE_SPACE;
        flag |= Capabilities.CLIENT_PROTOCOL_41;
        flag |= Capabilities.CLIENT_INTERACTIVE;
        flag |= Capabilities.CLIENT_IGNORE_SIGPIPE;
        flag |= Capabilities.CLIENT_TRANSACTIONS;
        flag |= Capabilities.CLIENT_SECURE_CONNECTION;
        flag |= Capabilities.CLIENT_MULTI_STATEMENTS;
        flag |= Capabilities.CLIENT_MULTI_RESULTS;
        flag |= Capabilities.CLIENT_PLUGIN_AUTH;
        return flag;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ctx.pipeline().replace(this, "frontendCommandHandler", new CommandHandler());

        ByteBuf byteBuf = ctx.alloc().buffer().writeBytes(new byte[]{7, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0});
        ctx.writeAndFlush(byteBuf);
    }
}
