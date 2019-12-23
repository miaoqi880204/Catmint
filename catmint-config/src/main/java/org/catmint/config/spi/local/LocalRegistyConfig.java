package org.catmint.config.spi.local;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.catmint.config.ServiceRegistryConfig;
import org.catmint.config.model.CatmintConnectConfig;
import org.catmint.config.model.ZookeeperConfigEnum;
import org.catmint.config.spi.zk.ZkClientFactory;
import org.catmint.exception.ExceptionEnum;
import org.catmint.exception.config.ConfigException;

/**
 * <p>Title:node-config节点配置信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class LocalRegistyConfig implements ServiceRegistryConfig {

    @Override
    public boolean register(CatmintConnectConfig catmintConnectConfig) {
        //单机模式
        log.info( ExceptionEnum.STAND_ALONE.getMessage() );
        return true;
    }
}
