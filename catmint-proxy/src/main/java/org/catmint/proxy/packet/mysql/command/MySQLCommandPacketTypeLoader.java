package org.catmint.proxy.packet.mysql.command;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;
import org.catmint.proxy.packet.mysql.MySQLPacketPayload;

/**
 * Command packet type loader for MySQL.
 *
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MySQLCommandPacketTypeLoader {

    /**
     * Get command packet type.
     *
     * @param payload packet payload for MySQL
     * @return command packet type for MySQL
     */
    public static MySQLCommandPacketType getCommandPacketType(final MySQLPacketPayload payload) {
        Preconditions.checkArgument(0 == payload.readInt1(), "Sequence ID of MySQL command packet must be `0`.");
        return MySQLCommandPacketType.valueOf(payload.readInt1());
    }
}

