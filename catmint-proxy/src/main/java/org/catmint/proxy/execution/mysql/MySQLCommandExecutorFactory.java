package org.catmint.proxy.execution.mysql;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.catmint.proxy.execution.CommandExecutor;
import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;
import org.catmint.proxy.packet.mysql.command.*;

/**
 * Command executor factory for MySQL.
 *
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class MySQLCommandExecutorFactory {

    /**
     * Create new instance of packet executor.
     *
     * @param commandPacketType command packet type for MySQL
     * @param commandPacket command packet for MySQL
     * @return command executor
     */
    public static CommandExecutor newInstance(final MySQLCommandPacketType commandPacketType, final MySQLCommandPacket commandPacket) {
        log.debug("Execute packet type: {}, value: {}", commandPacketType, commandPacket);
        switch (commandPacketType) {
            case COM_QUIT:
                return new MySQLComQuitExecutor();
            case COM_INIT_DB:
                return new MySQLComInitDbExecutor((MySQLComInitDbPacket) commandPacket);
            case COM_FIELD_LIST:
                return new MySQLComFieldListPacketExecutor((MySQLComFieldListPacket) commandPacket);
            case COM_QUERY:
                return new MySQLComQueryPacketExecutor((MySQLComQueryPacket) commandPacket);
            case COM_STMT_PREPARE:
                return new MySQLComStmtPrepareExecutor((MySQLComStmtPreparePacket) commandPacket);
            case COM_STMT_EXECUTE:
                return new MySQLComStmtExecuteExecutor((MySQLComStmtExecutePacket) commandPacket);
            case COM_STMT_RESET:
                return new MySQLComStmtResetExecutor((MySQLComStmtResetPacket) commandPacket);
            case COM_STMT_CLOSE:
                return new MySQLComStmtCloseExecutor((MySQLComStmtClosePacket) commandPacket);
            case COM_PING:
                return new MySQLComPingExecutor();
            default:
                return new MySQLUnsupportedCommandExecutor(commandPacketType);
        }
    }
}

