package org.catmint.io.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.catmint.io.codec.PacketDecoder;
import org.catmint.io.frontend.handler.AuthHandler;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Catmint启动监听器
 *
 * @author Shuo Xiang
 * @since 15 12月 2019
 */
@Slf4j
public class CatmintServerStartupListener implements Runnable, ApplicationListener<ApplicationStartedEvent> {

    // TODO 统一配置
    private static final String PORT_KEY = "org.catmint.server.port";

    private static final int DEFAULT_PORT = 13306;

    private int port;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        port = parsePort(event);
        new Thread(this).start();
    }

    private int parsePort(ApplicationStartedEvent event) {
        String port = event.getApplicationContext().getEnvironment().getProperty(PORT_KEY);
        if (port != null) {
            return Integer.parseInt(port);
        }
        return DEFAULT_PORT;
    }

    @Override
    public void run() {
        boolean useEpoll = useEpoll();

        EventLoopGroup bossGroup = useEpoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        EventLoopGroup workerGroup = useEpoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(useEpoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) {
                            channel.pipeline()
                                    .addLast(new PacketDecoder())
                                    .addLast(new AuthHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .option(ChannelOption.SO_TIMEOUT, 600);

            ChannelFuture future = bootstrap.bind(port).sync();

            log.info("Catmint Server started on prot: {}", port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupted();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private boolean useEpoll() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            log.info("epoll is active in os[{}]", os);
            return true;
        }

        return false;
    }
}
