package org.catmint.io.protocol.mysql;

/**
 * MySQL 版本常量
 *
 * @author Shuo Xiang
 */
public final class Versions {
    private Versions() {}

    /** MySQL 协议版本 */
    public static final byte PROTOCOL_VERSION = 10;
    /** MySQL 服务版本 */
    public static final byte[] SERVER_VERSION = "5.7.27-catmint".getBytes();
}
