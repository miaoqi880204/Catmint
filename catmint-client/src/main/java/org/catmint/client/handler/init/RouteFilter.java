package org.catmint.client.handler.init;

import org.catmint.client.model.Cluster;

import java.util.List;

/**
 * <p>Title:路由器接口</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@FunctionalInterface
public interface RouteFilter {
    void shardRoute(List<Cluster> clusters);
}
