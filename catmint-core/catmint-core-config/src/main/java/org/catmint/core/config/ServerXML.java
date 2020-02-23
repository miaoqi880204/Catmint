package org.catmint.core.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "catmint-server")
@ToString
public class ServerXML extends BaseConf implements Serializable {
    private static final long serialVersionUID = 1047183330517702479L;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "user")
    private LinkedList<User> users;

    @Getter
    @Setter
    @ToString
    public static class User implements Serializable{
        private static final long serialVersionUID = -2135515759865555507L;
        @JacksonXmlProperty(localName = "name")
        private String name;
        @JacksonXmlProperty(localName = "password")
        private String password;
        @JacksonXmlProperty(localName = "dialect")
        private String dialect;
        //最大返回结果集
        @JacksonXmlProperty(localName = "max-result-conut")
        private int maxResultCount;
        //主键生成策略
        @JacksonXmlProperty(localName = "sequnce-create-type")
        private int sequnceCreateType;
        @JacksonXmlProperty(localName = "schemas")
        private String schemas;
    }
}
