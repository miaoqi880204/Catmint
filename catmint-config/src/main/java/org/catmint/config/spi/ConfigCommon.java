package org.catmint.config.spi;

import net.sf.cglib.beans.BeanCopier;
import org.catmint.beanfactory.BeanFactory;
import org.catmint.common.utilities.XmlUtils;
import org.catmint.config.ConstantConfig;
import org.catmint.config.model.*;

import java.util.LinkedList;

/**
 * <p>Title:Config</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class ConfigCommon {
    /**
     * <p>Title:获取本地server.xml  route.xml 初始化配置信息</p>
     * <p>Description:</p>
     *
     * @return org.catmint.config.model.ProxyConfig
     * @throws
     * @author QIQI
     * @params []
     * @date 2020-01-04 21:59
     */
    public static ProxyConfig getLocalConf() {
        ProxyConfig proxyConfig = (ProxyConfig) BeanFactory.BeanInstantiation.get( ProxyConfig.class.getName() );
        if (proxyConfig == null) {
            ServerXML serverXML = XmlUtils.xmlParsingByPath( ConstantConfig.SERVER_CONF, ServerXML.class );
            RouteXML routeXML = XmlUtils.xmlParsingByPath( ConstantConfig.ROUTE_CONF, RouteXML.class );
            LinkedList<LogicDB> logicDBS = new LinkedList<>();
            LinkedList<ServerXML.Database> databases = serverXML.getDatabases();
            for (ServerXML.Database database : databases) {
                String logicNameTemp = database.getLogicName();
                LinkedList<RouteXML.Logicname> logicnames = routeXML.getLogicnames();
                for (RouteXML.Logicname logicname : logicnames) {
                    if (logicNameTemp.equals( logicname.getName() )) {
                        DatabaseConect.Ip ip = new DatabaseConect.Ip( logicname.getMaster().getIp(), logicname.getSlaves() );
                        DatabaseConect databaseConect = new DatabaseConect( logicname.getDbname(), ip, database.getUser(), database.getPassword(), database.getDialect() );
                        LogicDB logicDB = new LogicDB( logicNameTemp, databaseConect );
                        logicDBS.add( logicDB );
                    }
                }
            }
            proxyConfig = new ProxyConfig( logicDBS );
            BeanCopier beanCopier = BeanCopier.create( ServerXML.class,ProxyConfig.class,false );
            beanCopier.copy( serverXML,proxyConfig,null );
            BeanFactory.BeanInstantiation.put( ProxyConfig.class.getName(), proxyConfig );
        }
        return proxyConfig;
    }
}
