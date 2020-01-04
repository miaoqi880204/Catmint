package org.catmint.config.model;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * <p>Title:catmint-proxy初始化配置类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class ProxyConfig implements Serializable {
    private static final long serialVersionUID = 1463272221164238298L;
    //最大线程数
    @Getter
    private int maxThread;
    //最大返回结果集
    @Getter
    private int maxResultCount;
    //数据库链接信息
    @Getter
    private LinkedList<LogicDB> logicDBS;

    public ProxyConfig(@NonNull final int maxThread, @NonNull final int maxResultCount,
                       @NonNull LinkedList<LogicDB> logicDBS) {
        this.maxThread = maxThread;
        this.maxResultCount = maxResultCount;
        this.logicDBS = logicDBS;
    }
}
