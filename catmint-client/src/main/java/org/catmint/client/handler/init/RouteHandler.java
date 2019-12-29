package org.catmint.client.handler.init;

import lombok.extern.slf4j.Slf4j;
import org.catmint.client.model.Cluster;

import java.util.List;

/**
 * <p>Title:初始化路由策略</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class RouteHandler extends InitHandler implements RouteFilter {
    private InitHandler initHandler;

    @Override
    public void resolve(Cluster cluster) {
        log.info( ">>>>>>>>>>路由策略执行" );
        RouteFilterAdapter.getRouteFilter().shardRoute( null );
        if (null != this.initHandler) this.initHandler.resolve( cluster );
    }

    @Override
    public void setNextHandler(InitHandler initHandler) {
        this.initHandler = initHandler;
    }

    @Override
    public void shardRoute(List<Cluster> clusters) {
        log.info( "<<<<<<<<<<路由策略加载" );
        RouteFilterAdapter.addRouteFilter( new RouteHandler() );
    }
}
