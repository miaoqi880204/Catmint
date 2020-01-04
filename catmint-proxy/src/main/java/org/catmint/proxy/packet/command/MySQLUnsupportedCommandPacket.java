package org.catmint.proxy.packet.command;

import org.catmint.proxy.packet.MySQLCommandPacketType;

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
