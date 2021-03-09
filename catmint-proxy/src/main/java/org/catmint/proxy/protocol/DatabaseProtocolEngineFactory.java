package org.catmint.proxy.protocol;

import lombok.SneakyThrows;
import org.catmint.core.common.utils.CatmintConfigUtils;
import org.catmint.core.config.define.DialectEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public final class DatabaseProtocolEngineFactory {
    private static final Map<String,DatabaseProtocolEngine> DATABASE_PROTOCOL_ENGINE = new HashMap<>();

    static {
        DATABASE_PROTOCOL_ENGINE.put( DialectEnum.MYSQL.getName(),new MysqlDatabaseProtocol() );
    }

    @SneakyThrows
    public static DatabaseProtocolEngine newInstance(){
        return DATABASE_PROTOCOL_ENGINE.get( CatmintConfigUtils.getBaseConf().getDialect() );
    }
}
