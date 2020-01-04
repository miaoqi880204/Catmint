package org.catmint.proxy.execution;

import lombok.RequiredArgsConstructor;
import org.catmint.proxy.packet.CommonErrorCode;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.MySQLCommandPacketType;
import org.catmint.proxy.packet.MySQLErrPacket;

import java.util.Collection;
import java.util.Collections;

/**
 * Unsupported command packet executor for MySQL.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
public final class MySQLUnsupportedCommandExecutor implements CommandExecutor {

    private final MySQLCommandPacketType type;

    @Override
    public Collection<DatabasePacket> execute() {
        return Collections.<DatabasePacket>singletonList(new MySQLErrPacket(1, CommonErrorCode.UNSUPPORTED_COMMAND, type));
    }
}

