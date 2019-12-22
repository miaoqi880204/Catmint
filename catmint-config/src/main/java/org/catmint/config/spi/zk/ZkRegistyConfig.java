package org.catmint.config.spi.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.catmint.config.ServiceRegistry;
import org.catmint.config.model.CatmintConnectConfig;

/**
 * <p>Title:node-config节点配置信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class ZkRegistyConfig implements ServiceRegistry {

    @Override
    public void register(CatmintConnectConfig catmintConnectConfig) {
        CuratorFramework curatorFramework = ZkClient.getCuratorFrameworkFactory();
        try {
            if (curatorFramework.checkExists().forPath( "/cluster" ) == null) {
                curatorFramework.create().creatingParentContainersIfNeeded()
                        .withMode( CreateMode.PERSISTENT )
                        .withACL( ZooDefs.Ids.OPEN_ACL_UNSAFE )
                        .forPath( "/cluster" );
            }
            log.info( "zookeeper 初始化成功" );
        } catch (Exception e) {
            log.error( "zookeeper 初始化失败" );
            e.printStackTrace();
        }
    }
}
