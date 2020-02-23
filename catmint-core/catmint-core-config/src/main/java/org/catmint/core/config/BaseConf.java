package org.catmint.core.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Getter
@Setter
public class BaseConf implements Serializable {
    private static final long serialVersionUID = -2586101034379285665L;
    //最大线程数
    @JacksonXmlProperty(localName = "max-thread")
    private int maxThread;
    //zk地址  逗号分割
    @JacksonXmlProperty(localName = "zookeeper-address")
    private String zookeeperAddress;
    //sql解析最大长度
    @JacksonXmlProperty(localName = "max-string-literal-length")
    private int maxStringLiteralLength;
    //字符集
    @JacksonXmlProperty(localName = "charset")
    private String charset;
    //可服务监听地址
    @JacksonXmlProperty(localName = "bind-ip")
    private String bindIp;
}
