package org.catmint.config;

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
public class SchemaDataNode implements Serializable {
    private static final long serialVersionUID = 5108692232095923309L;
    @JacksonXmlProperty(localName = "data-nodes")
    private LinkedList<DataNode> dataNodes;

    @Getter
    @Setter
    public static class DataNode implements Serializable{
        private static final long serialVersionUID = 8536464528969091447L;
        @JacksonXmlProperty(localName = "name")
        private String name;
        @JacksonXmlProperty(localName = "route")
        private String route;
        @JacksonXmlProperty(localName = "database")
        private String database;
    }
}
