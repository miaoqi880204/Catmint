package org.catmint.client.handler.init;

import org.catmint.config.ConstantConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Title:INIT-HANDLER 初始化链式判断器</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Configuration
public class HandlerInitChain {
    @Value( "${org.catmint.zk.address:}" )
    private String zk_address;

    @PostConstruct
    public void zkParameterInit(){
        ConstantConfig.ZK_ADDRESS = zk_address;
    }

    /**
     * <p>Title:获取initHandler 责任链表</p>
     * <p>Description:</p>
     *
     * @return org.catmint.client.handler.init.InitHandler
     * @throws
     * @author QIQI
     * @params []
     * @date 2019-12-29 18:26
     */
    @PostConstruct
    public void getInitHandlerList() {
        InitHandler listenClusterHandler = new ListenClusterHandler();
        InitHandler routeHandler = new RouteHandler();
        //路由器可动态扩展
        listenClusterHandler.setNextHandler( routeHandler );
        listenClusterHandler.resolve( null );
    }
}
