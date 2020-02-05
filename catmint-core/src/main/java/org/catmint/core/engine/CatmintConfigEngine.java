package org.catmint.core.engine;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.catmint.common.utilities.CatmintStringUtils;
import org.catmint.config.SchemaDataNode;
import org.catmint.config.Schemas;
import org.catmint.config.ServerXML;
import org.catmint.config.constant.Constant;
import org.catmint.config.spi.local.AggregationConfig;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title:获取本地XML配置相关方法</p>
 * <p>Description:配置获取引擎</p>
 *
 * @author QIQI
 * @date
 */
public final class CatmintConfigEngine {
    private static final Map<String, Object> CONFIG_ENGINE = new ConcurrentHashMap<>();
    private static final String METHOD_SCHEMA = "getSchemaConfToString";
    private static final String METHOD_DATANODE = "getSchemaDatabaseNodeToString";
    private static final String METHOD_USER = "getServerConf";


    /**
     * <p>Title:获取基础配置信息</p>
     * <p>Description:</p>
     *
     * @return T
     * @throws
     * @author QIQI
     * @params []
     * @date 05/02/2020 10:56
     */
    public static <T extends ServerXML> T getBaseConf() {
        return (T) AggregationConfig.LOCAL_CONFIG.get( Constant.SERVER );
    }

    /**
     * <p>Title:获取基础配置信息</p>
     * <p>Description:</p>
     *
     * @return T
     * @throws
     * @author QIQI
     * @params []
     * @date 05/02/2020 10:56
     */
    public static ProxyUser getServerConfUser(final String userName) {
        ProxyUser proxyUser = (ProxyUser) CONFIG_ENGINE.get( Joiner.on( " | " ).join( userName, METHOD_USER ) );
        if (!Optional.ofNullable( proxyUser ).isPresent()) {
            ServerXML serverXML = getBaseConf();
            for(ServerXML.User user : serverXML.getUsers()){
                if (user.getName().equals( userName )) {
                    proxyUser = new ProxyUser( userName, user.getPassword(),
                            new HashSet<>( Splitter.on( "," ).splitToList( getSchemaDatabaseNodeToString( userName ) ) ) );
                }
            }
            CONFIG_ENGINE.put( Joiner.on( " | " ).join( userName, METHOD_USER ),proxyUser );
        }
        return proxyUser;
    }

    /**
     * <p>Title:获取schema信息,字符串形式</p>
     * <p>Description:</p>
     *
     * @return java.lang.String  返回schema信息，逗号拼接
     * @throws
     * @author QIQI
     * @params [userName - 传入数据库用户名]
     * @date 05/02/2020 11:25
     */
    public static String getSchemaConfToString(final String userName) {
        StringBuilder stringBuilder = new StringBuilder();
        String resultCatch = (String) CONFIG_ENGINE.get( Joiner.on( " | " ).join( userName, METHOD_SCHEMA ) );
        if (StringUtils.isBlank( resultCatch )) {
            Optional.ofNullable( userName ).ifPresent( v -> {
                ServerXML serverXML = getBaseConf();
                if (!serverXML.getUsers().isEmpty()) {
                    for (ServerXML.User user : serverXML.getUsers()) {
                        if (user.getName().equals( userName )) {
                            CONFIG_ENGINE.put( Joiner.on( " | " ).join( userName, METHOD_SCHEMA ), user.getSchemas() );
                            stringBuilder.append( user.getSchemas() );
                        }
                    }
                }
            } );
        } else stringBuilder.append( resultCatch );
        return stringBuilder.toString();
    }

    /**
     * <p>Title:获取schema信息，对象形式</p>
     * <p>Description:key-数据库用户名   val=ServerXML.User 对象信息</p>
     *
     * @return java.lang.String  返回schema-list<对象>
     * @throws
     * @author QIQI
     * @params [userName - 传入数据库用户名]
     * @date 05/02/2020 11:25
     */
    public static <T extends Schemas.Schema> List<T> getSchemaConfToObject(final String userName) {
        String schemaStr = getSchemaConfToString( userName );
        Schemas schemas = (Schemas) AggregationConfig.LOCAL_CONFIG.get( Constant.SCHEMA );
        List<T> returnList = new LinkedList<>();
        Optional.ofNullable( schemas ).ifPresent( v -> {
            Arrays.stream( schemaStr.split( "," ) ).forEach( str -> {
                schemas.getSchemas().forEach( schema -> {
                    if (str.equals( schema.getName() )) {
                        returnList.add( (T) schema );
                    }
                } );
            } );
        } );
        return returnList;
    }

    /**
     * <p>Title:传入数据库用户名，获取物理库名称，支持通配符格式配置解析 *</p>
     * <p>Description:返回字符串</p>
     *
     * @return java.lang.String  物理库名字逗号拼接返回
     * @throws
     * @author QIQI
     * @para
     * @date 05/02/2020 11:52
     */
    public static String getSchemaDatabaseNodeToString(final String userName) {
        StringBuilder stringBuilder = new StringBuilder();
        String resultCatch = (String) CONFIG_ENGINE.get( Joiner.on( " | " ).join( userName, METHOD_DATANODE ) );
        if (StringUtils.isBlank( resultCatch )) {
            SchemaDataNode schemaDataNode = (SchemaDataNode) AggregationConfig.LOCAL_CONFIG.get( Constant.SCHEMA_DATANODE );
            List<Schemas.Schema> schemas = getSchemaConfToObject( userName );
            if (schemas != null && !schemas.isEmpty()) {
                schemas.forEach( schema -> {
                    schemaDataNode.getDataNodes().forEach( dataNode -> {
                        if (schema.getDataNode().equals( dataNode.getName() )) {
                            stringBuilder.append( (String) CatmintStringUtils.wildcardDatabaseInfo( dataNode.getDatabase(), 1 ) );
                        }
                    } );
                } );
                CONFIG_ENGINE.put( Joiner.on( " | " ).join( userName, METHOD_DATANODE ), stringBuilder.toString() );
            }
        } else stringBuilder.append( resultCatch );
        return stringBuilder.toString();
    }


    /**
     * <p>Title:传入数据库用户名，获取物理库名称 支持通配符格式配置解析 *</p>
     * <p>Description:返回对象</p>
     *
     * @return java.util.List<T>  返回List对象
     * @throws
     * @author QIQI
     * @params [userName]
     * @date 05/02/2020 14:05
     */
    public static <T extends SchemaDataNode.DataNode> List<T> getSchemaDatabaseNodeToObject(final String userName) {
        List<T> list = new LinkedList<>();
        SchemaDataNode schemaDataNode = (SchemaDataNode) AggregationConfig.LOCAL_CONFIG.get( Constant.SCHEMA_DATANODE );
        List<Schemas.Schema> schemas = getSchemaConfToObject( userName );
        if (schemas != null && !schemas.isEmpty()) {
            schemas.forEach( schema -> {
                schemaDataNode.getDataNodes().forEach( dataNode -> {
                    if (schema.getDataNode().equals( dataNode.getName() )) {
                        list.addAll( CatmintStringUtils.wildcardDatabaseInfo( dataNode.getDatabase(), 2 ) );
                    }
                } );
            } );
        }
        return list;
    }
}
