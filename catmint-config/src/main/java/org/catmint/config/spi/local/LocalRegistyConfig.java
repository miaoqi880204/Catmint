package org.catmint.config.spi.local;

import lombok.extern.slf4j.Slf4j;
import org.catmint.config.ServiceRegistryConfig;
import org.catmint.exception.ExceptionEnum;

/**
 * <p>Title:node-config节点配置信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class LocalRegistyConfig implements ServiceRegistryConfig {

    @Override
    public boolean register() {
        //单机模式
        log.info( ExceptionEnum.STAND_ALONE.getMessage() );
        return true;
    }
}
