package org.catmint;


/**
 * <p>Title:系统异常枚举类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public enum ExceptionEm {
    /** 系统级别异常 */
    UNSPECIFIED("X1", "xxxx");


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