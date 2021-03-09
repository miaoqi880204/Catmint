package org.catmint.core.config.define;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@JacksonXmlRootElement(localName = "catmint-schema")
public class SchemaRoute implements Serializable {
    private static final long serialVersionUID = -6507834333037005312L;
    @JacksonXmlProperty(localName = "routes")
    private LinkedList<Route> routes;

    @Getter
    @Setter
    public static class Route implements Serializable{
        private static final long serialVersionUID = -2453852312343969976L;
        @JacksonXmlProperty(localName = "name")
        private String name;
        @JacksonXmlProperty(localName = "max-connection")
        private int maxConnection;
        @JacksonXmlProperty(localName = "min-connection")
        private int minConnection;
        @JacksonXmlProperty(localName = "heart-beat")
        private String heartBeat;
        @JacksonXmlProperty(localName = "master-host")
        private MasterHost masterHost;

        @Getter
        @Setter
        public static class MasterHost implements Serializable{
            private static final long serialVersionUID = -1070201645257891590L;
            @JacksonXmlProperty(localName = "url")
            private String url;
            @JacksonXmlProperty(localName = "read-proportion")
            private int readProportion;
            @JacksonXmlProperty(localName = "slaves")
            private LinkedList<Slave> slaves;
        }

        @Getter
        @Setter
        public static class Slave implements Serializable{
            private static final long serialVersionUID = -6656231175824030126L;
            @JacksonXmlProperty(localName = "url")
            private String url;
            @JacksonXmlProperty(localName = "read-proportion")
            private int readProportion;
        }
    }
}
