package org.catmint.config.spi.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.catmint.config.ServiceRegistry;
import org.catmint.config.model.DBConnect;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ZkRegistyConfig implements ServiceRegistry, Watcher {
    @Autowired
    private ZkClient zkClient;

    @Override
    public void register(DBConnect dbConnect) {
        CuratorFramework curatorFramework = zkClient.getZKClient();
        if(null != curatorFramework){
            try {
                if (curatorFramework.checkExists().forPath( "/cluster" ) == null) {
                    curatorFramework.create().creatingParentContainersIfNeeded()
                            .withMode( CreateMode.PERSISTENT )
                            .withACL( ZooDefs.Ids.OPEN_ACL_UNSAFE )
                            .forPath( "/cluster" );
                }
            } catch (Exception e) {
                log.error( "zookeeper 初始化失败" );
                e.printStackTrace();
            }
            log.info( "zookeeper 初始化成功" );
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
