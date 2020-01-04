package org.catmint.config.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Data
public class BaseConf implements Serializable {
    private static final long serialVersionUID = -2586101034379285665L;
    //最大线程数
    @JacksonXmlProperty(localName = "max-thread")
    private int maxThread;
    //最大返回结果集
    @JacksonXmlProperty(localName = "max-result-conut")
    private int maxResultCount;
    //zk地址  逗号分割
    @JacksonXmlProperty(localName = "zookeeper-address")
    private String zookeeperAddress;
    //使用的cpu核数
    @JacksonXmlProperty(localName = "processors")
    private int processors;
    //sql解析最大长度
    @JacksonXmlProperty(localName = "max-string-literal-length")
    private int maxStringLiteralLength;
    //字符集
    @JacksonXmlProperty(localName = "charset")
    private String charset;
    //主键生成策略
    @JacksonXmlProperty(localName = "sequnce-create-type")
    private int sequnceCreateType;
    //可服务监听地址
    @JacksonXmlProperty(localName = "bind-ip")
    private String bindIp;
}
