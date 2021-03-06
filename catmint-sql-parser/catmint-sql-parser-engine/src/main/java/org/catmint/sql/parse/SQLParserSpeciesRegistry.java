package org.catmint.sql.parse;

import org.catmint.sql.parser.spi.SQLParserSpecies;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * <p>Title:SQLParser 解析器种类注册器</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public final class SQLParserSpeciesRegistry {
    private static final SQLParserSpeciesRegistry REGISTRY = new SQLParserSpeciesRegistry();
    private final Map<String, SQLParserSpecies> species = new HashMap<>();

    private SQLParserSpeciesRegistry() {
        for (SQLParserSpecies each : ServiceLoader.load( SQLParserSpecies.class )) {
            species.put( each.getDatabaseType(), each );
        }
    }

    public static SQLParserSpeciesRegistry newInstance() {
        return REGISTRY;
    }

    public SQLParserSpecies getSQLParserSpecies(final String databaseType) {
        return Optional.ofNullable( species.get( databaseType ) ).orElseThrow( () -> new UnsupportedOperationException( String.format( "Cannot support database type '%s'", databaseType ) ) );
    }
}
