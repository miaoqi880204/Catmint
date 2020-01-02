package org.catmint.config;

import org.catmint.common.utilities.PropertiesUtils;

/**
 * <p>Title:配置模块常量类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class ConstantConfig {
    //重试时间
    public static final int ZOOKEEPER_BASE_SLEEP_TIMEMS = 1000;
    //重试次数
    public static final int ZOOKEEPER_MAX_RETRIES = 5;
    //连接建立超时时间
    public static final int ZOOKEEPER_CONNECT_TIMEOUT = 10000;
    //会话超时时间
    public static final int ZOOKEEPER_SESSION_TIMEOUT = 60000;
    public static final String CONFIG_PROPERTIES_NAME = "config.properties";
    public static String ZK_ADDRESS = PropertiesUtils.readValue( CONFIG_PROPERTIES_NAME,"org.catmint.zk.address" );
}
