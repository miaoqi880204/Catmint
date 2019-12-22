package org.catmint.config;

import lombok.extern.slf4j.Slf4j;
import org.catmint.config.model.DBConnect;
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
@Slf4j
@Service
public class InitConfig {

    public void initRegister(DBConnect dbConnect) {
        ServiceLoader<ServiceRegistry> nodeConfigs = ServiceLoader.load( ServiceRegistry.class );
        if (null != nodeConfigs && nodeConfigs.iterator().hasNext()) {
            for (ServiceRegistry serviceRegistry : nodeConfigs) {
                serviceRegistry.register( dbConnect );
            }
        } else {
            //单机模式
            log.info("当前单机模式执行，未找到可用的注册中心地址");
        }
    }
}
