package org.catmint.config.spi.zk;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.catmint.config.Constant;

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
    * @author QIQI
    * @params []
    * @return org.apache.curator.framework.CuratorFramework
    * @throws 
    * @date 2019-12-22 20:17 
    */
    public static CuratorFramework getCuratorFrameworkFactory() {
        if (null != curatorFramework || StringUtils.isBlank( ZkParameterInit.ZK_ADDRESS )) {
            return curatorFramework;
        }
        //创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry( Constant.ZOOKEEPER_BASE_SLEEP_TIMEMS, Constant.ZOOKEEPER_MAX_RETRIES );
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString( ZkParameterInit.ZK_ADDRESS )
                .sessionTimeoutMs( Constant.ZOOKEEPER_SESSION_TIMEOUT )
                .connectionTimeoutMs( Constant.ZOOKEEPER_CONNECT_TIMEOUT )
                .retryPolicy( retryPolicy )
                .namespace( Constant.ZK_NAMESPACE )
                .build();
        curatorFramework.start();
        return curatorFramework;
    }
}
