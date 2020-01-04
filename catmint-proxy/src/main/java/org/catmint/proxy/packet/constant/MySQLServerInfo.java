package org.catmint.proxy.packet.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MySQLServerInfo {

    /**
     * Protocol version is always 0x0A.
     */
    public static final int PROTOCOL_VERSION = 0x0A;

    /**
     * Server version.
     */
    public static final String SERVER_VERSION = "5.6.4-Catmint-Proxy 1.0";

    /**
     * Charset code 0x21 is utf8_general_ci.
     */
    public static final int CHARSET = 0x21;
}
