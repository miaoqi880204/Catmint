package org.catmint.proxy.packet.mysql.command;

import lombok.Getter;
import lombok.ToString;
import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;
import org.catmint.proxy.packet.mysql.MySQLPacketPayload;

/**
 * COM_INIT_DB command packet for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-init-db.html#packet-COM_INIT_DB">COM_INIT_DB</a>
 */
@Getter
@ToString
public final class MySQLComInitDbPacket extends MySQLCommandPacket {

    private final String schema;

    public MySQLComInitDbPacket(final MySQLPacketPayload payload) {
        super(MySQLCommandPacketType.COM_INIT_DB);
        schema = payload.readStringEOF();
    }

    @Override
    public void doWrite(final MySQLPacketPayload payload) {
        payload.writeStringEOF(schema);
    }
}
