package org.catmint.sql.parse.engine;

import org.catmint.core.config.define.BaseConf;
import org.catmint.core.config.define.ProxyUser;

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

    public static SQLStatementParserEngine newInstance(final BaseConf baseConf) {
        if (sqlStatementParserEngine == null) {
            synchronized (CatmintSQLParserEngine.class) {
                if (sqlStatementParserEngine == null) {
                    sqlStatementParserEngine = new SQLStatementParserEngine( baseConf.getDialect() );
                }
            }
        }
        return sqlStatementParserEngine;
    }
}
