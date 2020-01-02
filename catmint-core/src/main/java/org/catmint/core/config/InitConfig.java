package org.catmint.core.config;

import lombok.extern.slf4j.Slf4j;
import org.catmint.beanfactory.BeanFactory;
import org.catmint.config.ConstantConfig;
import org.catmint.config.RegisterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
@Configuration
public class InitConfig {
    @Value( "${org.catmint.zk.address:}" )
    private String zk_address;

    @PostConstruct
    public void zkParameterInit(){
        ConstantConfig.ZK_ADDRESS = zk_address;
    }

    @PostConstruct
    public void configRegisterCluster(){
        RegisterConfig registerConfig = BeanFactory.getBeanSingleton( RegisterConfig.class );
        registerConfig.initRegister();
    }
}
