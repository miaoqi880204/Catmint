package org.catmint;


import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title:实现自定义异常message接口</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class SystemExceptionModel implements ExceptionMessage{
    private String code;

    public SystemExceptionModel(String code){
        this.code = code;
    }

    @Override
    public String message() {
        for(ExceptionEm val : ExceptionEm.values()){
            if(code().equals( val.getCode() )){
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
