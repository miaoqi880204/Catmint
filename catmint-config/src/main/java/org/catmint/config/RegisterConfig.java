package org.catmint.config;

import org.catmint.config.model.ProxyConfig;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * <p>Title:利用SPI机制</p>
 * <p>Description:实现多平台注册中心集成</p>
 * <p>客户端链接catmint时调用这个方法</p>
 *
 * @author QIQI
 * @date
 */
public class RegisterConfig {

    public ProxyConfig initRegister() {
        ServiceLoader<ServiceRegistryConfig> serviceRegistries = ServiceLoader.load( ServiceRegistryConfig.class );
        ProxyConfig proxyConfig = null;
        if (null != serviceRegistries) {
            Iterator<ServiceRegistryConfig> serviceRegistryConfigIterator = serviceRegistries.iterator();
            while (serviceRegistryConfigIterator.hasNext()) {
                proxyConfig = serviceRegistryConfigIterator.next().register();
                if (proxyConfig != null) break;
            }
        }
        return proxyConfig;
    }
}
