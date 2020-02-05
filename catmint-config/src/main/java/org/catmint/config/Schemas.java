package org.catmint.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
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
@JacksonXmlRootElement(localName = "catmint-schema")
@ToString
public class Schemas implements Serializable {
    private static final long serialVersionUID = 1718560997014776158L;
    @JacksonXmlProperty(localName = "schemas")
    private LinkedList<Schema> schemas;

    @Getter
    @Setter
    @ToString
    public static class Schema implements Serializable{
        private static final long serialVersionUID = -9028741467869074685L;
        @JacksonXmlProperty(localName = "name")
        private String name;
        @JacksonXmlProperty(localName = "general-table")
        private boolean generalTable;
        @JacksonXmlProperty(localName = "data-node")
        private String dataNode;
        @JacksonXmlProperty(localName = "rule")
        private String rule;
    }
}
