package org.catmint.sql.parser.mysql;

import org.catmint.sql.parser.spi.SQLLexer;
import org.catmint.sql.parser.spi.SQLParser;
import org.catmint.sql.parser.spi.SQLParserSpecies;

/**
 * <p>Title:Mysql 解析器实现</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class MysqlSQLParserSpecies implements SQLParserSpecies {
    @Override
    public String getDatabaseType() {
        return "MySQL";
    }

    @Override public Class<? extends SQLLexer> getLexerClass() {
        return null;
    }

    @Override public Class<? extends SQLParser> getParserClass() {
        return null;
    }
}
