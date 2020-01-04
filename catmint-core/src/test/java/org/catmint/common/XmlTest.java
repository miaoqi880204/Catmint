package org.catmint.common;

import org.catmint.common.utilities.XmlUtils;
import org.catmint.config.model.ServerXML;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class XmlTest {
    @Test
    public void testXml(){
        ServerXML serverXML = XmlUtils.xmlParsingByPath( "server.xml", ServerXML.class );
        Assert.assertNotNull( serverXML );
    }
}
