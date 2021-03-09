package org.catmint.proxy.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.catmint.context.database.DatabaseProtocolTypeFactory;
import org.catmint.proxy.net.codec.PacketCodec;
import org.catmint.proxy.protocol.DatabaseProtocolEngine;
import org.catmint.proxy.protocol.DatabaseProtocolEngineFactory;

/**
 * <p>Title:Netty Pipeline init</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @params
 * @return
 * @throws
 * @date 2021/3/4 上午11:02
 */
@RequiredArgsConstructor
public final class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(final SocketChannel socketChannel) {
        DatabaseProtocolTypeFactory.initializeDatabaseConfig();
        DatabaseProtocolEngine databaseProtocolEngine = DatabaseProtocolEngineFactory.newInstance();
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast( new PacketCodec( databaseProtocolEngine ) );
        pipeline.addLast( new FrontendChannelInboundHandler( databaseProtocolEngine ) );
    }
}
