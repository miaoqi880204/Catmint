package org.catmint.core.config;

import lombok.extern.slf4j.Slf4j;
import org.catmint.beanfactory.BeanFactory;
import org.catmint.config.RegisterConfig;

import javax.annotation.PostConstruct;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class InitConfig {

    @PostConstruct
    public void configRegisterCluster(){
        RegisterConfig registerConfig = BeanFactory.getBeanSingleton( RegisterConfig.class );
        registerConfig.initRegister();
    }
}
