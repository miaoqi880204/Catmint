package org.catmint.context.database;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.catmint.core.tools.exception.CatmintException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

/*
* <p>Title:数据库类型上下文缓存</p>
* <p>Description:
* 1.从配置XML读取
* 2.从ZK配置读取
* 3.从Consul配置读取
* 4.SPI获取
* </p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/3 下午10:13 
*/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DatabaseTypeRegistry {
    private static final Map<String, DatabaseType> DATABASE_TYPES = new HashMap<>();
    private static final String DEFAULT_DATABASE_TYPE = "MySQL";
    
    static {
        for (DatabaseType each : ServiceLoader.load(DatabaseType.class)) {
            DATABASE_TYPES.put(each.getName(), each);
        }
    }
    /**
     * Get actual database type.
     *
     * @param name database name 
     * @return actual database type
     */
    public static DatabaseType getActualDatabaseType(final String name) {
        return Optional.ofNullable(DATABASE_TYPES.get(name)).orElseThrow(() -> new CatmintException("Unsupported database:'%s'", name));
    }
    
    /**
     * Get database type by URL.
     * 
     * @param url database URL
     * @return database type
     */
    public static DatabaseType getDatabaseTypeByURL(final String url) {
        return DATABASE_TYPES.values().stream().filter(each -> matchURLs(url, each)).findAny().orElse(DATABASE_TYPES.get("SQL92"));
    }
    
    private static boolean matchURLs(final String url, final DatabaseType databaseType) {
        return databaseType.getJdbcUrlPrefixes().stream().anyMatch(url::startsWith);
    }
    
    /**
     * Get default database type.
     * 
     * @return default database type
     */
    public static DatabaseType getDefaultDatabaseType() {
        return DATABASE_TYPES.get(DEFAULT_DATABASE_TYPE);
    }
}
