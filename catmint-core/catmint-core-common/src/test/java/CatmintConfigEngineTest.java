import org.catmint.core.common.config.spi.local.AggregationConfig;
import org.catmint.core.common.engine.CatmintConfigUtils;
import org.junit.Before;
import org.junit.Test;

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
    public void before(){
        AggregationConfig.excute();
    }

    @Test
    public void getBaseConfTest(){
        System.out.println( CatmintConfigUtils.getBaseConf().toString());
    }

    @Test
    public void getSchemaConfToStringTest(){
        System.out.println( CatmintConfigUtils.getSchemaConfToString("wh_shard_test").toString());
    }

    @Test
    public void getSchemaConfToObjectTest(){
        System.out.println( CatmintConfigUtils.getSchemaConfToObject("wh_shard_test").toString());
    }

    @Test
    public void getSchemaDatabaseNodeToStringTest(){
        System.out.println( CatmintConfigUtils.getSchemaDatabaseNodeToString("wh_shard_test").toString());
    }

    @Test
    public void getSchemaDatabaseNodeToObjectTest(){
        System.out.println( CatmintConfigUtils.getSchemaDatabaseNodeToObject("wh_shard_test").toString());
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
        System.out.println( CatmintConfigUtils.getServerConfUser("wh_shard_test").toString());
    }
}
