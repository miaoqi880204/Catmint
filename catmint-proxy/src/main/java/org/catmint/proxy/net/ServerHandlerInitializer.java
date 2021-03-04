package org.catmint.proxy.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.catmint.proxy.net.codec.MySQLPacketCodecEngine;
import org.catmint.proxy.net.codec.PacketCodec;

/**
* <p>Title:Netty Pipeline init</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return
* @throws
* @date 2021/3/4 上午11:02
*/
public final class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(final SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new PacketCodec(new MySQLPacketCodecEngine()));
        pipeline.addLast(new FrontendChannelInboundHandler());
    }
}
