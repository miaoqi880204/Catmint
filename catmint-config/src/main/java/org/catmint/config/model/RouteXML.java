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
@JacksonXmlRootElement(localName = "route")
public class RouteXML implements Serializable {
    private static final long serialVersionUID = 1718560997014776158L;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "logic-name")
    private LinkedList<Logicname> logicnames;

    @Data
    public static class Logicname implements Serializable{
        private static final long serialVersionUID = -9028741467869074685L;
        @JacksonXmlProperty(localName = "name")
        private String name;
        @JacksonXmlProperty(localName = "dbname")
        private String dbname;
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "master")
        private Master master;
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "slaves")
        private LinkedList<Slaves> slaves;
    }

    @Data
    public static class Master implements Serializable{
        private static final long serialVersionUID = -1903753478790083003L;
        @JacksonXmlProperty(localName = "ip")
        private String ip;
    }

    @Data
    public static class Slaves implements Serializable{
        private static final long serialVersionUID = 83104386188747431L;
        @JacksonXmlProperty(localName = "slave")
        private Slave slave;
    }

    @Data
    public static class Slave implements Serializable{
        private static final long serialVersionUID = -1903753478790083003L;
        @JacksonXmlProperty(localName = "ip")
        private String ip;
    }
}
