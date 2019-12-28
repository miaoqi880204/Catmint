package org.catmint.client.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * <p>Title:初始化服务治理监听器</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class RegisterWatcherSpi{

    public void initRegisterWatcher() {
        ServiceLoader<RegisterWatcher> serviceRegistries = ServiceLoader.load( RegisterWatcher.class, null );
        Iterator<RegisterWatcher> iterator = serviceRegistries.iterator();
        while(iterator.hasNext()){
            if(iterator.next().registerWatcher()) break;
        }

    }
}
