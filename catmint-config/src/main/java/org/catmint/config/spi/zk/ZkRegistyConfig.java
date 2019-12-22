package org.catmint.config.spi.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.catmint.exception.config.ConfigException;
import org.catmint.config.ServiceRegistryConfig;
import org.catmint.config.model.CatmintConnectConfig;
import org.catmint.config.model.ZookeeperConfigEnum;
import org.catmint.exception.config.ConfigExceptionEm;
import org.catmint.exception.config.ConfigExceptionMessage;

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
    public void register(CatmintConnectConfig catmintConnectConfig) {
        CuratorFramework curatorFramework = ZkClientFactory.getCuratorFrameworkFactory();
        try {
            if (curatorFramework.checkExists().forPath( ZookeeperConfigEnum.ZK_NODE_INFO.getVal() ) == null) {
                curatorFramework.create().creatingParentContainersIfNeeded()
                        .withMode( CreateMode.PERSISTENT )
                        .withACL( ZooDefs.Ids.OPEN_ACL_UNSAFE )
                        .forPath( "/cluster" );
            }
            log.info( "zookeeper 初始化成功" );
        } catch (Exception e) {
            throw new ConfigException( new ConfigExceptionMessage( ConfigExceptionEm.ZK_INIT_ERROR.getMessage() ) );
        }
    }
}
