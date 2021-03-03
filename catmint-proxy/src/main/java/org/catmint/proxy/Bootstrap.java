package org.catmint.proxy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.catmint.core.common.config.spi.RegisterConfig;
import org.catmint.proxy.net.CatmintServer;

/**
 * Catmint Server 启动类
 *
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Bootstrap {

    public static void main(String[] args) {
        RegisterConfig registerConfig = new RegisterConfig();
        registerConfig.initRegister();
        CatmintServer.getInstance().start(13306);
    }
}
