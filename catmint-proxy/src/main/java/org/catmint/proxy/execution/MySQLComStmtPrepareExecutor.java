package org.catmint.proxy.execution;

import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.MySQLEofPacket;
import org.catmint.proxy.packet.command.MySQLComStmtPreparePacket;

import java.util.Collection;
import java.util.Collections;

/**
 * COM_STMT_PREPARE command executor for MySQL.
 *
 * @author Shuo Xiang
 */
public final class MySQLComStmtPrepareExecutor implements CommandExecutor {

    public MySQLComStmtPrepareExecutor(final MySQLComStmtPreparePacket packet) {
    }

    @Override
    public Collection<DatabasePacket> execute() {
        // TODO
        return Collections.singleton(new MySQLEofPacket(1));
    }
}
