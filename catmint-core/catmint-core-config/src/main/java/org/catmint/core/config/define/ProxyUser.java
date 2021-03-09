package org.catmint.core.config.define;

import lombok.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * <p>Title:用户配置详细信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Data
public final class ProxyUser implements Serializable{
    private static final long serialVersionUID = 3697028741040745073L;
    private String name;
    private String password;
    private int maxResultCount;
    private int sequnceCreateType;
    //key=schemaName
    private LinkedHashMap<String, SchemaConfig> schemaConfigLinkedHashMap;

    @Data
    public static class SchemaConfig implements Serializable {
        private static final long serialVersionUID = -9028741467869074685L;
        private String name;
        private boolean generalTable;
        private DataNodeConfig dataNodeConfig;
        private RuleConfig ruleConfig;
    }

    @Data
    public static class DataNodeConfig implements Serializable {
        private static final long serialVersionUID = 8536464528969091447L;
        private String name;
        private SchemaRoute.Route routeConfig;
        private LinkedList<String> databases;
    }

    @Data
    public static class RuleConfig implements Serializable {
        private static final long serialVersionUID = -2453852312343969976L;
        private String name;
        private String field;
        private int type;
    }
}
