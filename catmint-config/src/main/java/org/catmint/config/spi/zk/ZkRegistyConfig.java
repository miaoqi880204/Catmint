package org.catmint.config.spi.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.catmint.exception.config.ConfigException;
import org.catmint.config.ServiceRegistryConfig;
import org.catmint.config.model.CatmintConnectConfig;
import org.catmint.config.model.ZookeeperConfigEnum;
import org.catmint.exception.ExceptionEnum;

/**
 * <p>Title:node-config节点配置信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class ZkRegistyConfig implements ServiceRegistryConfig {

    @Override
    public boolean register(CatmintConnectConfig catmintConnectConfig) {
        CuratorFramework curatorFramework = ZkClientFactory.getCuratorFrameworkFactory();
        if (curatorFramework != null) {
            try {
                if (curatorFramework.checkExists().forPath( ZookeeperConfigEnum.ZK_NODE_INFO.getVal() ) == null) {
                    curatorFramework.create().creatingParentContainersIfNeeded()
                            .withMode( CreateMode.PERSISTENT )
                            .withACL( ZooDefs.Ids.OPEN_ACL_UNSAFE )
                            .forPath( ZookeeperConfigEnum.ZK_NODE_INFO.getVal() );
                }
                log.info( "zookeeper 初始化成功" );
                return true;
            } catch (Exception e) {
                throw new ConfigException( ExceptionEnum.ZK_INIT_ERROR.getMessage() );
            }
        }
        return false;
    }
}
