package org.catmint.io.protocol.mysql;

/**
 * MySQL指令<br />
 * <a href="https://dev.mysql.com/doc/internals/en/text-protocol.html">MySQL 官方文档</a>
 *
 * @author Shuo Xiang
 */
public enum Commands {

    COM_SLEEP((byte) 0),
    /** 关闭连接 指令 */
    COM_QUIT((byte) 1),
    COM_INIT_DB((byte) 2),
    COM_QUERY((byte) 3),
    COM_FIELD_LIST((byte) 4),
    COM_CREATE_DB((byte) 5),
    COM_DROP_DB((byte) 6),
    COM_REFRESH((byte) 7),
    COM_SHUTDOWN((byte) 8),
    COM_STATISTICS((byte) 9),
    COM_PROCESS_INFO((byte) 10),
    COM_CONNECT((byte) 11),
    COM_PROCESS_KILL((byte) 12),
    COM_DEBUG((byte) 13),
    COM_PING((byte) 14),
    COM_TIME((byte) 15),
    COM_DELAYED_INSERT((byte) 16),
    COM_CHANGE_USER((byte) 17),
    COM_DAEMON((byte) 29),
    COM_RESET_CONNECTION((byte) 31);

    Commands(byte id) {
        this.id = id;
    }

    private byte id;

    public byte getId() {
        return id;
    }

    public static Commands parseId(byte id) {
        for (Commands each : values()) {
            if (each.id == id) {
                return each;
            }
        }
        return null;
    }
}
