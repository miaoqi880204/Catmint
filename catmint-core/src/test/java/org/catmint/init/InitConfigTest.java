package org.catmint.init;

import org.catmint.core.service.InitConfig;
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
        InitConfig initConfig = new InitConfig();
        initConfig.configRegister();
    }
}
