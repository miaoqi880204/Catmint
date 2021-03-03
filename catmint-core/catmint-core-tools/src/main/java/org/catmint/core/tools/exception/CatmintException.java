package org.catmint.core.tools.exception;

/**
* <p>Title:异常统一类</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/3 下午11:25 
*/
public class CatmintException extends RuntimeException {

    private static final long serialVersionUID = -1343739516839252250L;


    public CatmintException(final String errorMessage, final Object... args) {
        super(String.format(errorMessage, args));
    }


    public CatmintException(final String message, final Exception cause) {
        super(message, cause);
    }


    public CatmintException(final Exception cause) {
        super(cause);
    }
}
