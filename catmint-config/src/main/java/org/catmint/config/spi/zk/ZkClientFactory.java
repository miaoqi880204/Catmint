package org.catmint.config.spi.zk;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.catmint.config.ConstantConfig;
import org.catmint.config.model.ZookeeperConfigEnum;

/**
 * <p>Title:ZK 初始化执行工具</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class ZkClientFactory {
    private static CuratorFramework curatorFramework = null;

    /**
     * <p>Title:单例获取</p>
     * <p>Description:</p>
     *
     * @return org.apache.curator.framework.CuratorFramework
     * @throws
     * @author QIQI
     * @params []
     * @date 2019-12-22 20:17
     */
    public static CuratorFramework getCuratorFrameworkFactory() {
        //ZK配置不为空
        if (StringUtils.isNoneBlank( ConstantConfig.ZK_ADDRESS )) {
            if (curatorFramework == null) {
                //创建重试策略
                RetryPolicy retryPolicy = new ExponentialBackoffRetry( ConstantConfig.ZOOKEEPER_BASE_SLEEP_TIMEMS, ConstantConfig.ZOOKEEPER_MAX_RETRIES );
                CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                        .connectString( ConstantConfig.ZK_ADDRESS )
                        .sessionTimeoutMs( ConstantConfig.ZOOKEEPER_SESSION_TIMEOUT )
                        .connectionTimeoutMs( ConstantConfig.ZOOKEEPER_CONNECT_TIMEOUT )
                        .retryPolicy( retryPolicy )
                        .namespace( ZookeeperConfigEnum.ZK_NAMESPACE.getVal() )
                        .build();
                curatorFramework.start();
                return curatorFramework;
            }
            return curatorFramework;
        }
        return null;
    }
}
