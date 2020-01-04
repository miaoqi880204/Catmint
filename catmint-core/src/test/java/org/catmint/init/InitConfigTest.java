package org.catmint.init;

import org.catmint.beanfactory.BeanFactory;
import org.catmint.config.model.*;
import org.catmint.core.config.InitConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class InitConfigTest {

    @Test
    public void testInitConfig() {
        InitConfig initConfig = BeanFactory.getBeanSingleton( InitConfig.class );
        ProxyConfig proxyConfig = initConfig.configRegister();
        Assert.assertNotNull( proxyConfig );
    }
}
