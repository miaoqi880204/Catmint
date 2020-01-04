package org.catmint.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "server")
public class ServerXML extends BaseConf implements Serializable {
    private static final long serialVersionUID = 1047183330517702479L;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "databases")
    private LinkedList<Database> databases;

    @Data
    public static class Database implements Serializable{
        private static final long serialVersionUID = 6846700337135067672L;
        @JacksonXmlProperty(localName = "logic-dbname")
        private String logicName;
        @JacksonXmlProperty(localName = "user")
        private String user;
        @JacksonXmlProperty(localName = "password")
        private String password;
        @JacksonXmlProperty(localName = "dialect")
        private String dialect;
    }
}
