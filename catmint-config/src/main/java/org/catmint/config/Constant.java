package org.catmint.config;

/**
 * <p>Title:配置模块常量类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class Constant {
    //重试时间
    public static final int ZOOKEEPER_BASE_SLEEP_TIMEMS = 1000;
    //重试次数
    public static final int ZOOKEEPER_MAX_RETRIES = 5;
    //连接建立超时时间
    public static final int ZOOKEEPER_CONNECT_TIMEOUT = 10000;
    public static final String ZK_PROPERTIES = "org.catmint";
    public static final String ZK_NAMESPACE = "catmint";
}
