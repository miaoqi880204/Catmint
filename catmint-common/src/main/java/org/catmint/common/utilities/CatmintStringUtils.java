package org.catmint.common.utilities;

import com.google.common.base.Joiner;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>Title:字符串工具类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public final class CatmintStringUtils {
    /**
     * <p>Title:schema-xml database通配符信息解析</p>
     * <p>Description:</p>
     *
     * @return T
     * @throws
     * @author QIQI
     * @params [database=传入database字符串,type=1-字符串  2-list]
     * @date 05/02/2020 13:15
     */
    public static <T> T wildcardDatabaseInfo(final String database, final int type) {
        List<String> list = new LinkedList<>();
        if (database.contains( "*" )) {
            String[] s1 = database.split( "\\*" );
            String databasePrefix = s1[0];
            String[] s2 = s1[1].split( "-" );
            for (int i = Integer.parseInt( s2[0] ); i <= Integer.parseInt( s2[1] ); i++) {
                if (i < 10) {
                    list.add( Joiner.on( "0" ).join( databasePrefix, i ) );
                } else {
                    list.add( Joiner.on( "" ).join( databasePrefix, i ) );
                }
            }
        } else {
            list.add( database );
        }
        if (type == 1) return (T) Joiner.on( "," ).join( list );
        else return (T) list;
    }
}
