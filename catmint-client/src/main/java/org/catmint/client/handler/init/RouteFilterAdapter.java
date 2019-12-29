package org.catmint.client.handler.init;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title:路由策略适配MAP</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class RouteFilterAdapter {
    //初始化
    private static final ConcurrentHashMap<String, RouteFilter> ROUTE_HANDLER = new ConcurrentHashMap( 5 ) {
        {
            put( RouteFilter.class.getName(), new RouteHandler() );
        }
    };

    //添加route过滤器实现
    public static void addRouteFilter(RouteFilter routeFilter) {
        ROUTE_HANDLER.put( RouteFilter.class.getName(), routeFilter );
    }

    public static RouteFilter getRouteFilter(){
        return ROUTE_HANDLER.get( RouteFilter.class.getName() );
    }
}
