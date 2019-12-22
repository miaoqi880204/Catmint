package org.catmint.config;

import lombok.extern.slf4j.Slf4j;
import org.catmint.config.model.CatmintConnectConfig;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void initRegister(CatmintConnectConfig catmintConnectConfig) {
        List<ServiceRegistry> serviceRegistries = SpringFactoriesLoader.loadFactories( ServiceRegistry.class, null );
        if (null != serviceRegistries && !serviceRegistries.isEmpty()) {
            //只需要第一个实现被加载执行即可
            serviceRegistries.stream().findFirst().get().register( catmintConnectConfig );
        } else {
            //单机模式
            log.info( "当前单机模式执行，未找到可用的注册中心地址" );
        }
    }
}
