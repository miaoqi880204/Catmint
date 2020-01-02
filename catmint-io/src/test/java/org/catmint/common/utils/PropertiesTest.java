package org.catmint.common.utils;

import org.catmint.common.utilities.PropertiesUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class PropertiesTest {
    @Test
    public void testProperties(){
        Assert.assertNotNull( PropertiesUtils.readValue( "config.properties","org.catmint.zk.address" ) );
    }
}
