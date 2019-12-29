package org.catmint.client.model;

import lombok.Data;

import java.util.List;

/**
 * <p>Title:可执行节点</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class Cluster {
    private static final long serialVersionUID = -3293264046443600636L;
    private List<Cluster.Node> nodeList;

    @Data
    class Node{
        private String ip;
        private int port;
    }
}
