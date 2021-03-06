package org.catmint.context.database.type.dialect;

import org.catmint.context.database.DatabaseType;
import org.catmint.context.database.metadata.dialect.MySQLDataSourceMetaData;

import java.util.Arrays;
import java.util.Collection;

/**
* <p>Title:Mysql数据库类型</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/5 下午5:40 
*/
public final class MySQLDatabaseType implements DatabaseType {
    
    @Override
    public String getName() {
        return "MySQL";
    }
    
    @Override
    public Collection<String> getJdbcUrlPrefixes() {
        return Arrays.asList("jdbc:mysql:", "jdbc:mysqlx:");
    }
    
    @Override
    public MySQLDataSourceMetaData getDataSourceMetaData(final String url, final String username) {
        return new MySQLDataSourceMetaData(url);
    }
}
