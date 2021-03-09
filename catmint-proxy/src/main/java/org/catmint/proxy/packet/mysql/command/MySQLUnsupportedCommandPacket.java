package org.catmint.proxy.packet.mysql.command;

import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;

/**
 * Unsupported command packet for MySQL.
 *
 * @author Shuo Xiang
 */
public final class MySQLUnsupportedCommandPacket extends MySQLCommandPacket {

    public MySQLUnsupportedCommandPacket(final MySQLCommandPacketType type) {
        super(type);
    }
}
