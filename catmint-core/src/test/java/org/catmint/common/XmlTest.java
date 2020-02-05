package org.catmint.common;

import org.catmint.common.utilities.XmlUtils;
import org.catmint.config.*;
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
    public void testServerXml(){
        ServerXML serverXML = XmlUtils.xmlParsingByPath( "server.xml", ServerXML.class );
        Assert.assertNotNull( serverXML );
    }

    @Test
    public void testSchemaXml(){
        Schemas schemaXML = XmlUtils.xmlParsingByPath( "schema.xml", Schemas.class );
        Assert.assertNotNull( schemaXML );
    }

    @Test
    public void testSchemaDataNodeXml(){
        SchemaDataNode schemaDataNode = XmlUtils.xmlParsingByPath( "schema.xml", SchemaDataNode.class );
        Assert.assertNotNull( schemaDataNode );
    }

    @Test
    public void testSchemaRuleXml(){
        SchemaRule schemaRule = XmlUtils.xmlParsingByPath( "schema.xml", SchemaRule.class );
        Assert.assertNotNull( schemaRule );
    }

    @Test
    public void testSchemaRouteXml(){
        SchemaRoute schemaRoute = XmlUtils.xmlParsingByPath( "schema.xml", SchemaRoute.class );
        Assert.assertNotNull( schemaRoute );
    }
}
