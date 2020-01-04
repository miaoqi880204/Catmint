package org.catmint.core.config;

import lombok.extern.slf4j.Slf4j;
import org.catmint.beanfactory.BeanFactory;
import org.catmint.config.RegisterConfig;
import org.catmint.config.model.ProxyConfig;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class InitConfig {

    public ProxyConfig configRegister(){
        RegisterConfig registerConfig = BeanFactory.getBeanSingleton( RegisterConfig.class );
        return registerConfig.initRegister();
    }
}
