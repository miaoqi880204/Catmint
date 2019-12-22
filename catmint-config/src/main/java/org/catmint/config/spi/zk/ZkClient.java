package org.catmint.config.spi.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.catmint.config.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title:ZK 客户端</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Configuration
public class ZkClient {
    private CuratorFramework client;
    @Value("${org.catmint.zk.address:localhost:2181}")
    private String zkAddress;

    @Bean
    public CuratorFramework getZKClient() throws Exception {
        if (zkAddress == null) {
            return null;
        }
        //创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry( Constant.ZOOKEEPER_BASE_SLEEP_TIMEMS, Constant.ZOOKEEPER_MAX_RETRIES );
        //创建zookeeper客户端
        client = CuratorFrameworkFactory.builder().connectString( zkAddress )
                .sessionTimeoutMs( Constant.ZOOKEEPER_CONNECT_TIMEOUT )
                .retryPolicy( retryPolicy )
                .namespace( Constant.ZK_NAMESPACE )
                .build();
        client.start();
        return client;
    }
}
