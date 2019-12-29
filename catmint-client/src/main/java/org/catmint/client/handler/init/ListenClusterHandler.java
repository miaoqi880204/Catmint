package org.catmint.client.handler.init;

import org.catmint.client.model.Cluster;
import org.catmint.client.spi.RegisterWatcher;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * <p>Title:初始化监听节点信息的handler</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class ListenClusterHandler extends InitHandler {
    private InitHandler initHandler;

    @Override
    public void resolve(Cluster cluster) {
        ServiceLoader<RegisterWatcher> serviceRegistries = ServiceLoader.load( RegisterWatcher.class, null );
        Iterator<RegisterWatcher> iterator = serviceRegistries.iterator();
        while(iterator.hasNext()){
            if(iterator.next().registerWatcher()) break;
        }
        //交由下一个行为
        if(null !=  this.initHandler) this.initHandler.resolve( cluster );
    }

    @Override
    public void setNextHandler(InitHandler initHandler) {
        this.initHandler = initHandler;
    }
}
