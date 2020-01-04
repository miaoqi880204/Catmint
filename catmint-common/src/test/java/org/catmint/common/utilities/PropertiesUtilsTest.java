package org.catmint.common.utilities;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class PropertiesUtilsTest {

    @Test
    public void readValue() {
        Assert.assertNotNull( PropertiesUtils.readValue( "config.properties","org.catmint.zk.address" ) );
    }
}