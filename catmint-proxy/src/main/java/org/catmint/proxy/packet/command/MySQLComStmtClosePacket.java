package org.catmint.proxy.packet.command;

import lombok.Getter;
import lombok.ToString;
import org.catmint.proxy.packet.MySQLCommandPacketType;
import org.catmint.proxy.packet.MySQLPacketPayload;

/**
 * COM_STMT_CLOSE command packet for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-stmt-close.html">COM_STMT_CLOSE</a>
 */
@Getter
@ToString
public final class MySQLComStmtClosePacket extends MySQLCommandPacket {

    private final int statementId;

    public MySQLComStmtClosePacket(final MySQLPacketPayload payload) {
        super(MySQLCommandPacketType.COM_STMT_CLOSE);
        statementId = payload.readInt4();
    }

    /**
     * Remove cached statement.
     */
    public void removeCachedStatement() {
        // TODO
//        MySQLBinaryStatementRegistry.getInstance().remove(statementId);
    }
}
