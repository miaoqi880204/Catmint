package org.catmint.client.spi.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.catmint.BeanFactory.BeanFactory;
import org.catmint.client.spi.RegisterWatcher;
import org.catmint.common.utilities.ZkClientFactory;
import org.catmint.common.utilities.ZkClientUtils;
import org.catmint.config.ZookeeperConfigEnum;

/**
 * <p>Title:监听ZK数据存活节点信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class PathCacheListenster implements RegisterWatcher {
    @Override
    public boolean registerWatcher(){
        CuratorFramework curatorFramework = ZkClientFactory.getCuratorFrameworkFactory();
        try{
            ZkClientUtils.addPathChildrenCacheListener( curatorFramework,
                    ZookeeperConfigEnum.ZK_NODE_INFO.getVal(), true,
                    (client, event) -> {
                        ClusterObservable clusterObservable = BeanFactory.getBeanSingleton( ClusterObservable.class );
                        clusterObservable.getZkCusterList();
                    }
            );
            log.info( "PathCacheListenster.addPathCacheListenster scuuess" );
            return true;
        }catch(Exception e){
            log.error( "PathCacheListenster.addPathCacheListenster error ",e );
        }
        return false;
    }
}
