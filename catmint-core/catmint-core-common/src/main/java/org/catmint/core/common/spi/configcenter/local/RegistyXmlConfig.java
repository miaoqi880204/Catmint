package org.catmint.core.common.spi.configcenter.local;

import lombok.extern.slf4j.Slf4j;
import org.catmint.core.common.spi.configcenter.AggregationConfig;
import org.catmint.core.common.spi.configcenter.ServiceRegistryConfig;
import org.catmint.core.config.define.*;
import org.catmint.core.tools.common.XmlUtils;
import org.catmint.core.tools.config.ConstantConfig;
import org.catmint.core.tools.exception.ExceptionEnum;

/**
 * <p>Title:node-config节点配置信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class RegistyXmlConfig implements ServiceRegistryConfig {

    @Override
    public boolean register() {
        if (AggregationConfig.CONFIG.isEmpty()) {
            AggregationConfig.CONFIG.put( ConfigConstant.SERVER, XmlUtils.xmlParsingByPath( ConstantConfig.SERVER_CONF, Server.class ) );
            AggregationConfig.CONFIG.put( ConfigConstant.SCHEMA, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, Schemas.class ) );
            AggregationConfig.CONFIG.put( ConfigConstant.SCHEMA_DATANODE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaDataNode.class ) );
            AggregationConfig.CONFIG.put( ConfigConstant.SCHEMA_RULE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaRule.class ) );
            AggregationConfig.CONFIG.put( ConfigConstant.SCHEMA_ROUTE, XmlUtils.xmlParsingByPath( ConstantConfig.SCHEMA_CONF, SchemaRoute.class ) );
            //xml模式
            log.info( ExceptionEnum.STAND_ALONE.getMessage() );
            return true;
        }
        return false;
    }
}
