package org.catmint.proxy.packet.mysql.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;
import org.catmint.proxy.packet.mysql.MySQLPacketPayload;

import java.sql.SQLException;

/**
 * Command packet factory for MySQL.
 *
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MySQLCommandPacketFactory {

    /**
     * Create new instance of command packet.
     *
     * @param commandPacketType command packet type for MySQL
     * @param payload packet payload for MySQL
     * @return command packet for MySQL
     * @throws SQLException SQL exception
     */
    public static MySQLCommandPacket newInstance(final MySQLCommandPacketType commandPacketType, final MySQLPacketPayload payload) throws SQLException {
        switch (commandPacketType) {
            case COM_QUIT:
                return new MySQLComQuitPacket();
            case COM_INIT_DB:
                return new MySQLComInitDbPacket(payload);
            case COM_FIELD_LIST:
                return new MySQLComFieldListPacket(payload);
            case COM_QUERY:
                return new MySQLComQueryPacket(payload);
            case COM_STMT_PREPARE:
                return new MySQLComStmtPreparePacket(payload);
            case COM_STMT_EXECUTE:
                return new MySQLComStmtExecutePacket(payload);
            case COM_STMT_RESET:
                return new MySQLComStmtResetPacket(payload);
            case COM_STMT_CLOSE:
                return new MySQLComStmtClosePacket(payload);
            case COM_PING:
                return new MySQLComPingPacket();
            default:
                return new MySQLUnsupportedCommandPacket(commandPacketType);
        }
    }
}

