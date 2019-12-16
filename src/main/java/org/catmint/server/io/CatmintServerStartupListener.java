package org.catmint.server.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;

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
    private static final String PROPERTIES_NAME = "environmentProperties";

    private int port = 13306;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        PropertySourcesPlaceholderConfigurer propertyConfig = event.getApplicationContext().getBean(PropertySourcesPlaceholderConfigurer.class);
        PropertySource<?> properties = propertyConfig.getAppliedPropertySources().get(PROPERTIES_NAME);
        Object portValue = properties.getProperty(PORT_KEY);
        if (portValue != null) {
            port = Integer.parseInt((String) portValue);
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) {
                            channel.pipeline().addLast(new FrontendAuthHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(port).sync(); // (7)

            log.info("Catmint Server started on prot: {}", port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupted();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
