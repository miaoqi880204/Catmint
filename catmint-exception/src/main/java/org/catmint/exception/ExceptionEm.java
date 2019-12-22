package org.catmint.exception;


/**
 * <p>Title:系统异常枚举类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public enum ExceptionEm {
    /** 系统级别异常 */
    UNKNOWN("catmint-0", "系统异常"),
    STAND_ALONE("catmint-1", "当前单机模式执行，未找到可用的注册中心地址"),
    ZK_INIT_ERROR("catmint-2", "zookeeper 初始化失败");


    /** 错误码 */
    private final String code;

    /** 描述 */
    private final String message;

    private ExceptionEm(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}