package org.catmint.config.spi.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.catmint.config.Constant;
import org.catmint.config.NodeConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title:node-config节点配置信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
@Configuration
public class ZkConfig implements NodeConfig {
    private CuratorFramework client = null;
    @Value("${org.catmint.zk.address:}")
    private String zkAddress;

    @Override
    public void clusterRegistration() {
        if (client != null || zkAddress == null) {
            return;
        }
        //创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry( Constant.ZOOKEEPER_BASE_SLEEP_TIMEMS, Constant.ZOOKEEPER_MAX_RETRIES );
        //创建zookeeper客户端
        client = CuratorFrameworkFactory.builder().connectString( zkAddress )
                .sessionTimeoutMs( Constant.ZOOKEEPER_CONNECT_TIMEOUT )
                .retryPolicy( retryPolicy )
                .namespace( "catmint" )
                .build();

        client.start();
        try {
            if (client.checkExists().forPath( "/cluster" ) == null) {
                client.create().creatingParentContainersIfNeeded()
                        .withMode( CreateMode.PERSISTENT )
                        .withACL( ZooDefs.Ids.OPEN_ACL_UNSAFE )
                        .forPath( "/cluster" );
                log.info( "zookeeper初始化成功" );

            }
        } catch (Exception e) {
            log.error( "zookeeper初始化失败" );
            e.printStackTrace();
        }
    }
}
