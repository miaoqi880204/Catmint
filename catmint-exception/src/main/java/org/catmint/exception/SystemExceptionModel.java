package org.catmint.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title:实现自定义异常message接口</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class SystemExceptionModel implements ExceptionMessage{
    private String code;
    public static final Logger logger = LoggerFactory.getLogger( SystemExceptionModel.class );

    public SystemExceptionModel(String code){
        this.code = code;
    }

    @Override
    public String message() {
        for(ExceptionEm val : ExceptionEm.values()){
            if(code().equals( val.getCode() )){
                //logger.error( "SystemExceptionModel.message error is {}", val.getMessage());
                return val.getMessage();
            }
        }
        return ExceptionEm.UNSPECIFIED.getMessage();
    }

    @Override
    public String code() {
        return code;
    }
}
