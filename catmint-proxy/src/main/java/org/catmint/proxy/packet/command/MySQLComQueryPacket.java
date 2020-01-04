package org.catmint.proxy.packet.command;

import lombok.Getter;
import lombok.ToString;
import org.catmint.proxy.packet.MySQLCommandPacketType;
import org.catmint.proxy.packet.MySQLPacketPayload;

/**
 * COM_QUERY command packet for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-query.html">COM_QUERY</a>
 */
@Getter
@ToString
public final class MySQLComQueryPacket extends MySQLCommandPacket {

    private final String sql;

    public MySQLComQueryPacket(final MySQLPacketPayload payload) {
        super(MySQLCommandPacketType.COM_QUERY);
        sql = payload.readStringEOF();
    }

    @Override
    public void doWrite(final MySQLPacketPayload payload) {
        payload.writeStringEOF(sql);
    }
}

