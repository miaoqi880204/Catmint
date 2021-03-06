package org.catmint.sql.parser.spi;


/**
* <p>Title:各大厂商数据库解析器SPI接口</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/6 下午11:29 
*/
public interface SQLParserSpecies {
    
    /**
     * Get database type.
     *
     * @return database type
     */
    String getDatabaseType();
    
    /**
     * Get SQL lexer class type.
     *
     * @return SQL lexer class type
     */
    Class<? extends SQLLexer> getLexerClass();
    
    /**
     * Get SQL parser class type.
     * 
     * @return SQL parser class type
     */
    Class<? extends SQLParser> getParserClass();
}
