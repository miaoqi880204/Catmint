package org.catmint.init;

import org.catmint.beanfactory.BeanFactory;
import org.catmint.config.model.ProxyConfig;
import org.catmint.core.config.InitConfig;
import org.junit.Test;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class InitConfigTest {
    private ProxyConfig catmintConnectConfig;

    @Test
    public void testInitConfig(){
        catmintConnectConfig = new ProxyConfig( "admin","123",false,3306,"127.9.9.9",false,"127.0.0.1","999.99.99.99",8799 );
        InitConfig initConfig = BeanFactory.getBeanSingleton( InitConfig.class );
        initConfig.configRegisterCluster();
    }
}
