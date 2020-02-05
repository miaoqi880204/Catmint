package org.catmint.core.service;

import lombok.extern.slf4j.Slf4j;
import org.catmint.config.spi.RegisterConfig;

/**
 * <p>Title:初始化获取配置</p>
 * <p>Description:通过SPI机制获取唯一实现</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class InitConfig {
    public void configRegister(){
        RegisterConfig registerConfig = new RegisterConfig();
        registerConfig.initRegister();
    }
}
