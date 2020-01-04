package org.catmint.config.model;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

/**
 * <p>Title:逻辑库关联实体库的链接信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class LogicDB implements Serializable {
    private static final long serialVersionUID = 234562585666638331L;
    //逻辑库名字
    @Getter
    private String logicName;
    @Getter
    private DatabaseConect databaseConect;

    public LogicDB(@NonNull  String logicName,@NonNull DatabaseConect databaseConect) {
        this.logicName = logicName;
        this.databaseConect = databaseConect;
    }
}
