package org.catmint.proxy.packet.mysql.command;

import lombok.Getter;
import lombok.ToString;
import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;
import org.catmint.proxy.packet.mysql.MySQLPacketPayload;

/**
 * COM_STMT_PREPARE command packet for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-stmt-prepare.html">COM_STMT_PREPARE</a>
 */
@Getter
@ToString
public final class MySQLComStmtPreparePacket extends MySQLCommandPacket {

    private final String sql;

    public MySQLComStmtPreparePacket(final MySQLPacketPayload payload) {
        super(MySQLCommandPacketType.COM_STMT_PREPARE);
        sql = payload.readStringEOF();
    }

    @Override
    public void doWrite(final MySQLPacketPayload payload) {
        payload.writeStringEOF(sql);
    }
}

