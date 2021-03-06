package org.catmint.sql.parse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.antlr.v4.runtime.*;
import org.catmint.sql.parser.spi.SQLLexer;
import org.catmint.sql.parser.spi.SQLParser;
import org.catmint.sql.parser.spi.SQLParserSpecies;

import java.nio.CharBuffer;

/**
 * <p>Title:sql 解析工厂创建类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @params
 * @return
 * @throws
 * @date 2021/3/6 下午9:54
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SQLParserFactory {

    /**
     * <p>Title:工厂类初始化执行器</p>
     * <p>Description:</p>
     *
     * @return java.lang.String
     * @throws
     * @author QIQI
     * @params [databaseType]
     * @date 2021/3/6 下午10:57
     */
    public static SQLParser newInstance(final String databaseType, final String sql) {
        SQLParserSpecies sqlParserSpecies = SQLParserSpeciesRegistry.newInstance().getSQLParserSpecies( databaseType );
        return createSQLParser( createTokenStream( sql, sqlParserSpecies.getLexerClass() ), sqlParserSpecies.getParserClass() );
    }

    @SneakyThrows(ReflectiveOperationException.class)
    private static SQLParser createSQLParser(final TokenStream tokenStream, final Class<? extends SQLParser> parserClass) {
        return parserClass.getConstructor( TokenStream.class ).newInstance( tokenStream );
    }

    @SneakyThrows(ReflectiveOperationException.class)
    private static TokenStream createTokenStream(final String sql, final Class<? extends SQLLexer> lexerClass) {
        Lexer lexer = (Lexer) lexerClass.getConstructor( CharStream.class ).newInstance( getSQLCharStream( sql ) );
        return new CommonTokenStream( lexer );
    }

    private static CharStream getSQLCharStream(final String sql) {
        CodePointBuffer buffer = CodePointBuffer.withChars( CharBuffer.wrap( sql.toCharArray() ) );
        return CodePointCharStream.fromBuffer( buffer );
    }
}
