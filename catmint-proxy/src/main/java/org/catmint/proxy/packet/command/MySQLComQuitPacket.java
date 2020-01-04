package org.catmint.proxy.packet.command;

import org.catmint.proxy.packet.MySQLCommandPacketType;

/**
 * COM_QUIT command packet for MySQL.
 *
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-quit.html">COM_QUIT</a>
 *
 * @author Shuo Xiang
 */
public final class MySQLComQuitPacket extends MySQLCommandPacket {

    public MySQLComQuitPacket() {
        super(MySQLCommandPacketType.COM_QUIT);
    }
}
