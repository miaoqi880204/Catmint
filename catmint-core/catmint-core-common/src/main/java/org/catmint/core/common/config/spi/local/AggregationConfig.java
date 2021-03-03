package org.catmint.core.common.config.spi.local;

import lombok.Getter;
import org.catmint.core.config.define.*;
import org.catmint.core.tools.common.XmlUtils;
import org.catmint.core.tools.config.ConstantConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title:聚合本地XML配置</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Getter
public final class AggregationConfig {
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
        LOCAL_CONFIG.put( Constant.SERVER, XmlUtils.xmlParsingByPath( ConstantConfig.SERVER_CONF, ServerXML.class ) );
        LOCAL_CONFIG.put( Constant.SCHEMA, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, Schemas.class ) );
        LOCAL_CONFIG.put( Constant.SCHEMA_DATANODE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaDataNode.class ) );
        LOCAL_CONFIG.put( Constant.SCHEMA_RULE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaRule.class ) );
        LOCAL_CONFIG.put( Constant.SCHEMA_ROUTE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaRoute.class ) );
    }
}
