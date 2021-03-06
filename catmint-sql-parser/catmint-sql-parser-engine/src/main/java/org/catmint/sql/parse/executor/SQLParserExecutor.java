package org.catmint.sql.parse.executor;

import lombok.RequiredArgsConstructor;
import org.catmint.sql.parse.SQLParserFactory;

/**
* <p>Title:SQLParser 执行器</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/6 下午9:55 
*/
@RequiredArgsConstructor
public final class SQLParserExecutor {
    
    private final String databaseType;

    /**
     * Parse SQL.
     *
     * @param sql SQL to be parsed
     * @return parse tree
     */
    public String parse(final String sql) {
        return SQLParserFactory.newInstance( databaseType,sql ).parse();
    }
}
