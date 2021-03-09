package org.catmint.proxy.execution.mysql;

import lombok.RequiredArgsConstructor;
import org.catmint.proxy.execution.CommandExecutor;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.mysql.MySQLOKPacket;
import org.catmint.proxy.packet.mysql.command.MySQLComInitDbPacket;

import java.util.Collection;
import java.util.Collections;

/**
 * COM_INIT_DB command executor for MySQL.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
public final class MySQLComInitDbExecutor implements CommandExecutor {

    private final MySQLComInitDbPacket packet;

    @Override
    public Collection<DatabasePacket> execute() {
        return Collections.<DatabasePacket>singletonList(new MySQLOKPacket(1));
    }
}
