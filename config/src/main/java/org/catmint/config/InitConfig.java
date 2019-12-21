package org.catmint.config;

import org.catmint.config.spi.ZkConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ServiceLoader;

/**
 * <p>Title:利用SPI机制替换原先ZK实现</p>
 * <p>Description:实现多平台注册中心集成</p>
 *
 * @author QIQI
 * @date
 */

public class InitConfig {
    @Autowired
    private ZkConfig zkConfig;

    @PostConstruct
    public void initRegister(){
        ServiceLoader<NodeConfig> nodeConfigs = ServiceLoader.load( NodeConfig.class );
        if(null != nodeConfigs) {
            nodeConfigs.forEach( v -> v.clusterRegistration() );
        }else{
            zkConfig.clusterRegistration();
        }
    }
}
