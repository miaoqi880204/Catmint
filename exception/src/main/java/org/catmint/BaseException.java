package org.catmint;


import java.text.MessageFormat;

/**
 * <p>Title:系统通用异常类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 7490145243360109272L;
    private ExceptionMessage exceptionMessage;

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }


    /**
     * <p>Title:指定详细描述构造通用异常</p>
     * <p>Description:</p>
     *
     * @return
     * @throws
     * @author QIQI
     * @params [detailedMessage]
     * @date 2019-10-25 14:20
     */
    public BaseException(ExceptionMessage co) {
        super( co.code() + co.message() );
        this.exceptionMessage = co;
        co.message();
    }

    /**
     * <p>Title:指定详细描述构造通用异常</p>
     * <p>Description:带占位符用法</p>
     * <p>{0}{1}</p>
     *
     * @return
     * @throws
     * @author QIQI
     * @params [detailedMessage]
     * @date 2019-10-25 14:20
     */
    public BaseException(ExceptionMessage co, Object...obj) {
        super( co.code() + MessageFormat.format(co.message(),obj) );
        this.exceptionMessage = co;
        co.message();
    }
}
