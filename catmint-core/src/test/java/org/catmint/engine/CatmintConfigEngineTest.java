package org.catmint.engine;

import org.catmint.config.spi.local.AggregationConfig;
import org.catmint.core.engine.CatmintConfigEngine;
import org.junit.Before;
import org.junit.Test;

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
        System.out.println(CatmintConfigEngine.getBaseConf().toString());
    }

    @Test
    public void getSchemaConfToStringTest(){
        System.out.println(CatmintConfigEngine.getSchemaConfToString("wh_shard_test").toString());
    }

    @Test
    public void getSchemaConfToObjectTest(){
        System.out.println(CatmintConfigEngine.getSchemaConfToObject("wh_shard_test").toString());
    }

    @Test
    public void getSchemaDatabaseNodeToStringTest(){
        System.out.println(CatmintConfigEngine.getSchemaDatabaseNodeToString("wh_shard_test").toString());
    }

    @Test
    public void getSchemaDatabaseNodeToObjectTest(){
        System.out.println(CatmintConfigEngine.getSchemaDatabaseNodeToObject("wh_shard_test").toString());
    }
}
