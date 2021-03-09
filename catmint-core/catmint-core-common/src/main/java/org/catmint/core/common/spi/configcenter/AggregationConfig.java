package org.catmint.core.common.spi.configcenter;

import lombok.Getter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title:聚合XML配置</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Getter
public final class AggregationConfig {
    public static Map<String, Object> CONFIG = new ConcurrentHashMap<>( 10 );
}
