package org.catmint.context.database.metadata.dialect;

import com.google.common.base.Strings;
import lombok.Getter;
import org.catmint.context.database.metadata.DataSourceMetaData;
import org.catmint.core.tools.exception.CatmintException;
import org.catmint.core.tools.exception.ExceptionEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:Mysql 数据源源数据实现</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @params
 * @return
 * @throws
 * @date 2021/3/3 下午10:42
 */
@Getter
public final class MySQLDataSourceMetaData implements DataSourceMetaData {
    private static final int DEFAULT_PORT = 3306;
    private final String hostName;
    private final int port;
    private final String catalog;
    private final String schema;
    private final Pattern pattern = Pattern.compile( "jdbc:(mysql|mysqlx)(:loadbalance|:replication)?:(\\w*:)?//([\\w\\-\\.]+):?([0-9]*),?.*?/([\\w\\-]+);?\\S*", Pattern.CASE_INSENSITIVE );

    public MySQLDataSourceMetaData(final String url) {
        Matcher matcher = pattern.matcher( url );
        if (!matcher.find()) {
            throw new CatmintException( ExceptionEnum.METADATA_URL_ERROR.getMessage(), url );
        }
        hostName = matcher.group( 4 );
        port = Strings.isNullOrEmpty( matcher.group( 5 ) ) ? DEFAULT_PORT : Integer.parseInt( matcher.group( 5 ) );
        catalog = matcher.group( 6 );
        schema = null;
    }
}
