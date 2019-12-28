package org.catmint.client.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title:Zk-节点信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Data
public class ZookeeperNode implements Serializable {
    private static final long serialVersionUID = -3293264046443600636L;
    private List<Node> nodeList;

    @Data
    class Node{
        private String ip;
        private int port;
    }
}
