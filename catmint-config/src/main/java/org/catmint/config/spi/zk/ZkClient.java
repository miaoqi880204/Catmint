package org.catmint.config.spi.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.catmint.config.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>Title:ZK 客户端</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Service
public class ZkClient {
    private static CuratorFramework client;
    @Value("${org.catmint.zk.address:}")
    private String zkAddress;

    public CuratorFramework getZKClient() {
        if (client != null || "".equals( zkAddress )) {
            return client;
        }
        //创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry( Constant.ZOOKEEPER_BASE_SLEEP_TIMEMS, Constant.ZOOKEEPER_MAX_RETRIES );
        //创建zookeeper客户端
        client = CuratorFrameworkFactory.builder().connectString( zkAddress )
                .sessionTimeoutMs( Constant.ZOOKEEPER_CONNECT_TIMEOUT )
                .retryPolicy( retryPolicy )
                .namespace( Constant.ZK_NAMESPACE )
                .build();
        return client;
    }
}
