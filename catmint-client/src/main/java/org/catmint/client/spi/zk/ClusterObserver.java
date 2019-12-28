package org.catmint.client.spi.zk;


import java.util.Observable;
import java.util.Observer;

/**
 * <p>Title:ZK 客户端获取服务信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class ClusterObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }
}
