package org.catmint.sql.parse.executor;

import lombok.RequiredArgsConstructor;

/**
 * <p>Title:SQLStatement 解析执行器</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@RequiredArgsConstructor
public final class SQLStatementParserExecutor {
    private final SQLParserExecutor sqlParserExecutor;

    public SQLStatementParserExecutor(final String databaseType) {
        this.sqlParserExecutor = new SQLParserExecutor( databaseType );
    }

    public String parse(final String sql) {
        return sqlParserExecutor.parse( sql );
    }
}
