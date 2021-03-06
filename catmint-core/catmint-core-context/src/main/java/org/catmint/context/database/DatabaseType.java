package org.catmint.context.database;

import org.catmint.context.database.metadata.DataSourceMetaData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
* <p>Title:数据库类型</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/3 下午11:37 
*/
public interface DatabaseType {
    
    /**
     * Get database name.
     * 
     * @return database name
     */
    String getName();
    
    /**
     * Get alias of JDBC URL prefixes.
     * 
     * @return Alias of JDBC URL prefixes
     */
    Collection<String> getJdbcUrlPrefixes();
    
    /**
     * Get data source meta data.
     * 
     * @param url URL of data source
     * @param username username of data source
     * @return data source meta data
     */
    DataSourceMetaData getDataSourceMetaData(String url, String username);
    
    /**
     * Get schema.
     *
     * @param connection connection
     * @return schema
     */
    @SuppressWarnings("ReturnOfNull")
    default String getSchema(final Connection connection) {
        try {
            return connection.getSchema();
        } catch (final SQLException ignored) {
            return null;
        }
    }
    
    /**
     * Format table name pattern.
     *
     * @param tableNamePattern table name pattern
     * @return formatted table name pattern
     */
    default String formatTableNamePattern(final String tableNamePattern) {
        return tableNamePattern;
    }
}
