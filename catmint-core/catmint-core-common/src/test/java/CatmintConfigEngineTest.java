import org.catmint.core.common.spi.configcenter.AggregationConfig;
import org.catmint.core.common.utils.CatmintConfigUtils;
import org.catmint.core.config.define.*;
import org.catmint.core.tools.common.XmlUtils;
import org.catmint.core.tools.config.ConstantConfig;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Optional;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class CatmintConfigEngineTest {

    @Before
    public void before() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        AggregationConfig.CONFIG.put( ConfigConstant.SERVER, XmlUtils.xmlParsingByPath( ConstantConfig.SERVER_CONF, Server.class ) );
        AggregationConfig.CONFIG.put( ConfigConstant.SCHEMA, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, Schemas.class ) );
        AggregationConfig.CONFIG.put( ConfigConstant.SCHEMA_DATANODE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaDataNode.class ) );
        AggregationConfig.CONFIG.put( ConfigConstant.SCHEMA_RULE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaRule.class ) );
        AggregationConfig.CONFIG.put( ConfigConstant.SCHEMA_ROUTE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaRoute.class ) );
        CatmintConfigUtils.newInstance();
    }

    @Test
    public void getSchemaConfToStringTest(){
        System.out.println( CatmintConfigUtils.getProxyUser("wh_shard_test").toString());
    }

    @Test
    public void testOptional(){
        StringBuilder xx = new StringBuilder();
        String s = "null";
        String schema = Optional.ofNullable( s ).orElse( "ss" );
        System.out.println(schema);
        Optional.ofNullable( s ).ifPresent( v -> {
            xx.append( s );
        } );
        System.out.println(xx.toString());
        System.out.println(Optional.ofNullable( xx.toString() ).orElse( "ssss" ));

        HashMap<String,String> m = new HashMap<>();
        System.out.println(m.get( "s" ));
    }

    @Test
    public void getServerConfUserTest(){
        System.out.println( CatmintConfigUtils.getProxyUser("wh_shard_test").toString());
    }
}
