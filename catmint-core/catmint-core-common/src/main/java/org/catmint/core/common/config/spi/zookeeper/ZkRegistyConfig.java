package org.catmint.core.common.config.spi.zookeeper;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.catmint.core.common.config.spi.ServiceRegistryConfig;
import org.catmint.core.tools.common.IpUtils;
import org.catmint.core.tools.common.ZkClientFactory;
import org.catmint.core.tools.common.ZkClientUtils;
import org.catmint.core.tools.config.ZookeeperConfigEnum;
import org.catmint.core.tools.exception.CatmintException;
import org.catmint.core.tools.exception.ExceptionEnum;

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
                        join( ZookeeperConfigEnum.ZK_NODE_INFO.getVal(), IpUtils.getLocalIp() );
                if (curatorFramework.checkExists().forPath( path ) == null) {
                    ZkClientUtils.create( curatorFramework, path, IpUtils.getLocalIp().getBytes() );
                }
                log.info( "zookeeper 初始化成功 >>>>>>>> NodeIp is {}",IpUtils.getLocalIp() );
                return true;
            } catch (Exception e) {
                log.error( "Zookeeper register error ",e );
                throw new CatmintException( ExceptionEnum.ZK_INIT_ERROR.getMessage() );
            }
        }
        return false;
    }
}
