package org.catmint.config.spi.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.catmint.common.utilities.ZkClientFactory;
import org.catmint.common.utilities.ZkClientUtils;
import org.catmint.config.ZookeeperConfigEnum;
import org.catmint.exception.config.ConfigException;
import org.catmint.config.ServiceRegistryConfig;
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
    public boolean register() {
        CuratorFramework curatorFramework = ZkClientFactory.getCuratorFrameworkFactory();
        if (curatorFramework != null) {
            try {
                if (curatorFramework.checkExists().forPath( ZookeeperConfigEnum.ZK_NODE_INFO.getVal() ) == null) {
                    ZkClientUtils.create( curatorFramework,ZookeeperConfigEnum.ZK_NODE_INFO.getVal(),null );
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
