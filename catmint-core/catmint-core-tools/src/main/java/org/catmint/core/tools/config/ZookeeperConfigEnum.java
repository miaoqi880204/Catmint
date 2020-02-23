package org.catmint.core.tools.config;


/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public enum ZookeeperConfigEnum {
    ZK_NAMESPACE("catmint"),
    ZK_NODE_INFO("/cluster");

    private String val;

    ZookeeperConfigEnum(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
