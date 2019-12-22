package org.catmint.config;

import org.catmint.config.model.DBConnectDTO;
import org.catmint.config.spi.zk.ZkRegistyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

/**
 * <p>Title:利用SPI机制替换原先ZK实现</p>
 * <p>Description:实现多平台注册中心集成</p>
 * <p>客户端链接catmint且校验成功时调用这个方法</p>
 *
 * @author QIQI
 * @date
 */
@Service
public class InitConfig {
    @Autowired
    private ZkRegistyConfig zkConfig;

    public void initRegister(DBConnectDTO dbConnectDTO){
        ServiceLoader<ServiceRegistry> nodeConfigs = ServiceLoader.load( ServiceRegistry.class );
        if(null != nodeConfigs && nodeConfigs.iterator().hasNext()) {
            for (ServiceRegistry serviceRegistry : nodeConfigs){
                serviceRegistry.register( dbConnectDTO );
            }
        }else{
            zkConfig.register(dbConnectDTO);
        }
    }
}
