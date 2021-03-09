package org.catmint.proxy.packet.mysql.command;

import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;

/**
 * COM_PING command packet for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-ping.html">COM_PING</a>
 */
public final class MySQLComPingPacket extends MySQLCommandPacket {

    public MySQLComPingPacket() {
        super(MySQLCommandPacketType.COM_PING);
    }
}
