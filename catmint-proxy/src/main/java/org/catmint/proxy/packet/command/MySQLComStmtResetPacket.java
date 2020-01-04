package org.catmint.proxy.packet.command;

import lombok.Getter;
import org.catmint.proxy.packet.MySQLCommandPacketType;
import org.catmint.proxy.packet.MySQLPacketPayload;

/**
 * COM_STMT_RESET command packet for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-stmt-reset.html">COM_STMT_RESET</a>
 */
@Getter
public final class MySQLComStmtResetPacket extends MySQLCommandPacket {

    private final int statementId;

    public MySQLComStmtResetPacket(final MySQLPacketPayload payload) {
        super(MySQLCommandPacketType.COM_STMT_RESET);
        statementId = payload.readInt4();
    }
}
