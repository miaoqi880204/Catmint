package org.catmint.config.init;

import org.catmint.config.model.CatmintConnectConfig;
import org.junit.Test;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class InitConfigTest {
    private CatmintConnectConfig catmintConnectConfig;

    @Test
    public void testInitConfig(){
        catmintConnectConfig = new CatmintConnectConfig( "admin","123",false,3306,"127.9.9.9",false,"127.0.0.1","999.99.99.99",8799 );
    }
}
