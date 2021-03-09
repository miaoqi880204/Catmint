package org.catmint.proxy.packet.mysql.command;

import lombok.RequiredArgsConstructor;
import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;
import org.catmint.proxy.packet.mysql.MySQLPacket;
import org.catmint.proxy.packet.mysql.MySQLPacketPayload;

/**
 * Command packet for MySQL.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
public abstract class MySQLCommandPacket implements MySQLPacket {

    private final MySQLCommandPacketType type;

    @Override
    public final void write(final MySQLPacketPayload payload) {
        payload.writeInt1(type.getValue());
        doWrite(payload);
    }

    protected void doWrite(final MySQLPacketPayload payload) {
    }

    @Override
    public final int getSequenceId() {
        return 0;
    }
}
