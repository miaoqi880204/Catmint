package org.catmint.client.spi.zk;

import org.catmint.beanfactory.BeanFactory;
import java.util.Observable;

/**
 * <p>Title:ZK 信息被观察者</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class ClusterObservable extends Observable {


    public void getZkCusterList(){
        ClusterObservable clusterObserver = BeanFactory.getBeanSingleton( ClusterObservable.class );
        clusterObserver.addObserver( BeanFactory.getBeanSingleton( ClusterObserver.class ) );
        clusterObserver.setChanged();
        clusterObserver.notifyObservers( "aaa" );
    }
}
