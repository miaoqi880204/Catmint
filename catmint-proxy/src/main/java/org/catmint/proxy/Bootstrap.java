package org.catmint.proxy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.catmint.beanfactory.BeanFactory;
import org.catmint.config.RegisterConfig;
import org.catmint.config.model.ProxyConfig;
import org.catmint.proxy.net.CatmintServer;

/**
 * Catmint Server 启动类
 *
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Bootstrap {

    public static void main(String[] args) {
        RegisterConfig registerConfig = BeanFactory.getBeanSingleton(RegisterConfig.class);
        ProxyConfig proxyConfig = registerConfig.initRegister();
        CatmintServer.getInstance().start(13306);
    }
}
