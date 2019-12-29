package org.catmint.client.handler.init;

import org.catmint.client.model.Cluster;

/**
 * <p>Title:项目启动初始化</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public abstract class InitHandler {
    //处理
    public abstract void resolve(Cluster cluster);
    //下一行为
    public abstract void setNextHandler(InitHandler initHandler);
}
