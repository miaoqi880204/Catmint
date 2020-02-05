package org.catmint.config.spi.zk;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.catmint.common.utilities.IpUtils;
import org.catmint.common.utilities.ZkClientFactory;
import org.catmint.common.utilities.ZkClientUtils;
import org.catmint.config.ZookeeperConfigEnum;
import org.catmint.exception.config.ConfigException;
import org.catmint.config.spi.ServiceRegistryConfig;
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
        //ConstantConfig.ZK_ADDRESS = ConfigCommon.getLocalConf().getZookeeperAddress();
        CuratorFramework curatorFramework = ZkClientFactory.getCuratorFrameworkFactory();
        if (curatorFramework != null) {
            try {
                String path = Joiner.on("/").
                        join( ZookeeperConfigEnum.ZK_NODE_INFO.getVal(),IpUtils.getLocalIp() );
                if (curatorFramework.checkExists().forPath( path ) == null) {
                    ZkClientUtils.create( curatorFramework, path, IpUtils.getLocalIp().getBytes() );
                }
                log.info( "zookeeper 初始化成功 >>>>>>>> NodeIp is {}",IpUtils.getLocalIp() );
                return true;
            } catch (Exception e) {
                log.error( "Zookeeper register error ",e );
                throw new ConfigException( ExceptionEnum.ZK_INIT_ERROR.getMessage() );
            }
        }
        return false;
    }
}
