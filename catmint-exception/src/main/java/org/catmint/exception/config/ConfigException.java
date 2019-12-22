package org.catmint.exception.config;

import org.catmint.exception.BaseException;
import org.catmint.exception.ExceptionMessage;

/**
 * <p>Title:配置模块自定义异常</p>
 * <p>Description:继承基础异常模块</p>
 *
 * @author QIQI
 * @date
 */
public class ConfigException extends BaseException {

    public ConfigException(ExceptionMessage co) {
        super( co );
    }
}
