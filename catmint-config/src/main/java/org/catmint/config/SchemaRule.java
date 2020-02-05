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
public class SchemaRule implements Serializable {
    private static final long serialVersionUID = -6507834333037005312L;
    @JacksonXmlProperty(localName = "rules")
    private LinkedList<Rule> rules;

    @Getter
    @Setter
    public static class Rule implements Serializable{
        private static final long serialVersionUID = -2453852312343969976L;
        @JacksonXmlProperty(localName = "name")
        private String name;
        @JacksonXmlProperty(localName = "field")
        private String field;
        @JacksonXmlProperty(localName = "type")
        private int type;
    }
}
