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
public class ProxyConfig extends BaseConf implements Serializable {
    private static final long serialVersionUID = 1463272221164238298L;
    //数据库链接信息
    @Getter
    private LinkedList<LogicDB> logicDBS;

    public ProxyConfig(@NonNull LinkedList<LogicDB> logicDBS) {
        this.logicDBS = logicDBS;
    }
}
