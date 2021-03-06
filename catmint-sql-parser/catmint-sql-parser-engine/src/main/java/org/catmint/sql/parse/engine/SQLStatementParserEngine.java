package org.catmint.sql.parse.engine;

import org.catmint.sql.parse.executor.SQLStatementParserExecutor;
/**
 * <p>Title:SQL Statement 解析引擎入口</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public final class SQLStatementParserEngine {
    private static volatile SQLStatementParserExecutor sqlStatementParserExecutor;

    public SQLStatementParserEngine(final String databaseType) {
        sqlStatementParserExecutor = new SQLStatementParserExecutor(databaseType);
    }

    public String parse(final String sql) {
        return sqlStatementParserExecutor.parse(sql);
    }
}
