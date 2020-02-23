package org.catmint.core.tools.exception.config;


/**
 * <p>Title:配置模块自定义异常</p>
 * <p>Description:继承基础异常模块</p>
 *
 * @author QIQI
 * @date
 */
public class ConfigException extends RuntimeException {

    public ConfigException() {
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
