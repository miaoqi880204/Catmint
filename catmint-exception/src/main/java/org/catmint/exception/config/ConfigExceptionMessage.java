package org.catmint.exception.config;


import org.catmint.exception.ExceptionMessage;

/**
 * <p>Title:实现自定义异常message接口</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class ConfigExceptionMessage implements ExceptionMessage{
    private String message;

    public ConfigExceptionMessage(String message){
        this.message = message;
    }

    @Override
    public String message() {
        return ConfigExceptionEm.valueOf( message ).getMessage();
    }

    public String getMessage() {
        return message;
    }
}
