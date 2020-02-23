package org.catmint.core.service.config.spi.local;

import org.catmint.core.config.*;
import org.catmint.core.tools.common.XmlUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title:聚合本地XML配置</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class AggregationConfig {
    private static final String SERVER_XML = "server.xml";
    private static final String SCHEMA_XML = "schema.xml";
    public static final Map<String, Object> LOCAL_CONFIG = new ConcurrentHashMap<>( 10 );

    /**
     * <p>Title:聚合获取xml信息</p>
     * <p>Description:</p>
     *
     * @return void
     * @throws
     * @author QIQI
     * @params []
     * @date 15/01/2020 00:16
     */
    public static final void excute() {
        LOCAL_CONFIG.put( Constant.SERVER, XmlUtils.xmlParsingByPath( SERVER_XML, ServerXML.class ) );
        LOCAL_CONFIG.put( Constant.SCHEMA, XmlUtils.xmlParsingByPath( SCHEMA_XML, Schemas.class ) );
        LOCAL_CONFIG.put( Constant.SCHEMA_DATANODE, XmlUtils.xmlParsingByPath( SCHEMA_XML, SchemaDataNode.class ) );
        LOCAL_CONFIG.put( Constant.SCHEMA_RULE, XmlUtils.xmlParsingByPath( SCHEMA_XML, SchemaRule.class ) );
        LOCAL_CONFIG.put( Constant.SCHEMA_ROUTE, XmlUtils.xmlParsingByPath( SCHEMA_XML, SchemaRoute.class ) );
    }
}
