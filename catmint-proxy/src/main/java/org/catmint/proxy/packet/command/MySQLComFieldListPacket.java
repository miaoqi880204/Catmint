package org.catmint.proxy.packet.command;

import lombok.Getter;
import lombok.ToString;
import org.catmint.proxy.packet.MySQLCommandPacketType;
import org.catmint.proxy.packet.MySQLPacketPayload;

/**
 * COM_FIELD_LIST command packet for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-field-list.html">COM_FIELD_LIST</a>
 */
@Getter
@ToString
public final class MySQLComFieldListPacket extends MySQLCommandPacket {

    private final String table;

    private final String fieldWildcard;

    public MySQLComFieldListPacket(final MySQLPacketPayload payload) {
        super(MySQLCommandPacketType.COM_FIELD_LIST);
        table = payload.readStringNul();
        fieldWildcard = payload.readStringEOF();
    }

    @Override
    public void doWrite(final MySQLPacketPayload payload) {
        payload.writeStringNul(table);
        payload.writeStringEOF(fieldWildcard);
    }
}
