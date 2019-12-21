package org.catmint.exception;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public interface ExceptionMessage {
    /**
    * <p>Title:异常code</p>
    * <p>Description:</p>
    * @author QIQI
    * @params []
    * @return java.lang.String
    * @throws 
    * @date 2019-10-29 16:36 
    */
    String code();

    /**
    * <p>Title:自定义message异常处理</p>
    * <p>Description:</p>
    * @author QIQI
    * @return java.lang.String
    * @throws
    * @date 2019-10-29 15:26
    */
    String message();
}
