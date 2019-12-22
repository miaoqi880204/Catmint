package org.catmint.exception;


/**
 * <p>Title:系统异常枚举类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public enum ExceptionEnum {
    /**
     * 系统级别异常
     */
    STAND_ALONE( "当前单机模式执行，未找到可用的注册中心地址" ),
    ZK_INIT_ERROR( "zookeeper 初始化失败" );

    /**
     * 描述
     */
    private String message;

    ExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}