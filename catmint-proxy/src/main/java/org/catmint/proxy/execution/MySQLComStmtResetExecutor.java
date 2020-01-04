package org.catmint.proxy.execution;

import lombok.RequiredArgsConstructor;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.MySQLOKPacket;
import org.catmint.proxy.packet.command.MySQLComStmtResetPacket;

import java.util.Collection;
import java.util.Collections;

/**
 * COM_STMT_RESET command executor for MySQL.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
public final class MySQLComStmtResetExecutor implements CommandExecutor {

    private final MySQLComStmtResetPacket packet;

    @Override
    public Collection<DatabasePacket> execute() {
        // TODO we should implement the stmt reset after supporting COM_STMT_SEND_LONG_DATA
        return Collections.<DatabasePacket>singletonList(new MySQLOKPacket(1));
    }
}
