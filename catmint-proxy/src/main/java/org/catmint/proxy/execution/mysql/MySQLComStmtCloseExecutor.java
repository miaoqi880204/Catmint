package org.catmint.proxy.execution.mysql;

import lombok.RequiredArgsConstructor;
import org.catmint.proxy.execution.CommandExecutor;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.mysql.command.MySQLComStmtClosePacket;

import java.util.Collection;
import java.util.Collections;

/**
 * COM_STMT_CLOSE command executor for MySQL.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
public final class MySQLComStmtCloseExecutor implements CommandExecutor {

    private final MySQLComStmtClosePacket packet;

    @Override
    public Collection<DatabasePacket> execute() {
        //TODO we need to design the cache in future.
//        packet.removeCachedStatement();
        return Collections.emptyList();
    }
}
