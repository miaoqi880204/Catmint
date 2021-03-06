package org.catmint.sql.parse.engine;

import org.catmint.context.database.DatabaseType;

/**
 * <p>Title:sql 解析引擎入口</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @params
 * @return
 * @throws
 * @date 2021/3/5 下午6:04
 */
public final class CatmintSQLParserEngine {
    private static volatile SQLStatementParserEngine sqlStatementParserEngine;

    public static SQLStatementParserEngine newInstance(final DatabaseType databaseType) {
        if (sqlStatementParserEngine == null) {
            synchronized (CatmintSQLParserEngine.class) {
                if (sqlStatementParserEngine == null) {
                    sqlStatementParserEngine = new SQLStatementParserEngine( databaseType.getName() );
                }
            }
        }
        return sqlStatementParserEngine;
    }
}
