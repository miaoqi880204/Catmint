import org.catmint.core.config.define.*;
import org.catmint.core.tools.common.XmlUtils;
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
        Server serverXML = XmlUtils.xmlParsingByPath( "server.xml", Server.class );
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
