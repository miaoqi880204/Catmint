package org.catmint.proxy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.catmint.proxy.net.CatmintServer;

/**
 * Catmint Server 启动类
 *
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Bootstrap {

    public static void main(String[] args) {

        CatmintServer.getInstance().start(13306);
    }
}
