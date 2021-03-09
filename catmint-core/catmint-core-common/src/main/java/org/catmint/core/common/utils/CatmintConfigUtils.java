package org.catmint.core.common.utils;

import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.catmint.core.config.define.*;
import org.catmint.core.common.spi.configcenter.AggregationConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <p>Title:XML配置解析工具</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public final class CatmintConfigUtils {
    //key=userName
    private static final Map<String, ProxyUser> proxyConfigMap = new ConcurrentHashMap<>();
    @Getter
    private static BaseConf baseConf;
    private static final String DT = ",";

    /**
     * <p>Title:获取catmint 系统基础配置信息</p>
     * <p>Description:</p>
     *
     * @return T
     * @throws
     * @author QIQI
     * @params []
     * @date 05/02/2020 10:56
     */
    public static <T extends BaseConf> T getBaseConf() {
        try {
            if (baseConf == null) {
                synchronized (CatmintConfigUtils.class) {
                    if (baseConf == null) {
                        baseConf = new BaseConf();
                        Server server = (Server) AggregationConfig.CONFIG.get( ConfigConstant.SERVER );
                        PropertyUtils.copyProperties( baseConf, server );
                    }
                }
            }
            return (T) baseConf;
        }catch (Exception ex){
            log.error( "CatmintConfigUtils.getBaseConf is error:",ex );
            return null;
        }
    }

    /**
     * <p>Title:获取数据源配置信息</p>
     * <p>Description:</p>
     *
     * @return T
     * @throws
     * @author QIQI
     * @params []
     * @date 05/02/2020 10:56
     */
    public static void newInstance() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (proxyConfigMap.isEmpty()) {
            Server server = (Server) AggregationConfig.CONFIG.get( ConfigConstant.SERVER );
            for (Server.User user : server.getUsers()) {
                proxyConfigMap.put( user.getName(), saveSchemaConfig( user ) );
            }
        }
    }

    public static LinkedList<String> getDatabases(final String userName){
        LinkedList<String> databaseList = new LinkedList<>();
        proxyConfigMap.get( userName ).getSchemaConfigLinkedHashMap().values().stream()
                .map( schemaConfig -> schemaConfig.getDataNodeConfig().getDatabases() ).collect( Collectors.toList() ).forEach( strings -> databaseList.addAll( strings ) );
        return databaseList;
    }

    public static Optional<ProxyUser> getProxyUser(final String userName){
        return Optional.ofNullable( proxyConfigMap.get( userName ) );
    }

    private static ProxyUser saveSchemaConfig(Server.User user) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ProxyUser proxyUser = new ProxyUser();
        LinkedHashMap<String, ProxyUser.SchemaConfig> configLinkedHashMap = new LinkedHashMap<>();
        PropertyUtils.copyProperties( proxyUser, user );
        Iterator<String> iterator = Splitter.on( DT ).trimResults().omitEmptyStrings().split( user.getSchemas() ).iterator();
        while (iterator.hasNext()) {
            String sehemaName = iterator.next();
            ProxyUser.SchemaConfig schemaConfig = new ProxyUser.SchemaConfig();
            saveSchema( sehemaName, schemaConfig );
            configLinkedHashMap.put( sehemaName, schemaConfig );
        }
        proxyUser.setSchemaConfigLinkedHashMap( configLinkedHashMap );
        return proxyUser;
    }

    private static void saveSchema(String sehemaName, ProxyUser.SchemaConfig schemaConfig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Schemas schemas = (Schemas) AggregationConfig.CONFIG.get( ConfigConstant.SCHEMA );
        for (Schemas.Schema schema : schemas.getSchemas()) {
            if (schema.getName().equals( sehemaName )) {
                PropertyUtils.copyProperties( schemaConfig, schema );
                saveSchemaNode( schema, schemaConfig );
                saveSchemaRule( schema, schemaConfig );
            }
        }
    }

    private static void saveSchemaRule(Schemas.Schema schema, ProxyUser.SchemaConfig schemaConfig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        SchemaRule schemaRule = (SchemaRule) AggregationConfig.CONFIG.get( ConfigConstant.SCHEMA_RULE );
        for (SchemaRule.Rule rule : schemaRule.getRules()) {
            if (rule.getName().equals( schema.getRule() )) {
                ProxyUser.RuleConfig ruleConfig = new ProxyUser.RuleConfig();
                PropertyUtils.copyProperties( ruleConfig, rule );
                schemaConfig.setRuleConfig( ruleConfig );
            }
        }
    }

    private static void saveSchemaNode(Schemas.Schema schema, ProxyUser.SchemaConfig schemaConfig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        SchemaDataNode schemaDataNode = (SchemaDataNode) AggregationConfig.CONFIG.get( ConfigConstant.SCHEMA_DATANODE );
        for (SchemaDataNode.DataNode dataNode : schemaDataNode.getDataNodes()) {
            if (dataNode.getName().equals( schema.getDataNode() )) {
                ProxyUser.DataNodeConfig dataNodeConfig = new ProxyUser.DataNodeConfig();
                PropertyUtils.copyProperties( dataNodeConfig, dataNode );
                dataNodeConfig.setDatabases( getDatabases( dataNode ) );
                SchemaRoute schemaRoute = (SchemaRoute) AggregationConfig.CONFIG.get( ConfigConstant.SCHEMA_ROUTE );
                for (SchemaRoute.Route route : schemaRoute.getRoutes()) {
                    if (route.getName().equals( dataNode.getRoute() )) {
                        dataNodeConfig.setRouteConfig( route );
                    }
                }
                schemaConfig.setDataNodeConfig( dataNodeConfig );
            }
        }
    }

    private static LinkedList<String> getDatabases(SchemaDataNode.DataNode dataNode) {
        LinkedList<String> databaseList = new LinkedList<>();
        if (dataNode.getDatabase().contains( "*" )) {
            String databaseName = dataNode.getDatabase().split( "\\*" )[0];
            String databaseNums = dataNode.getDatabase().split( "\\*" )[1];
            String[] databaseNum = databaseNums.split( "\\-" );
            for (int i = 0; i < Integer.parseInt( databaseNum[1] ); i++) {
                int y = i + 1;
                if (databaseNum[0].contains( "0" ) && i < 10) {
                    databaseList.add( databaseName + "0" + y );
                } else {
                    databaseList.add( databaseName + y );
                }
            }
        } else
            databaseList.add( dataNode.getDatabase() );
        return databaseList;
    }
}
