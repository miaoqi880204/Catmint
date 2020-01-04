package org.catmint.proxy.execution;

import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.MySQLOKPacket;

import java.util.Collection;
import java.util.Collections;

/**
 * COM_QUIT executor for MySQL.
 *
 * @author Shuo Xiang
 */
public final class MySQLComQuitExecutor implements CommandExecutor {

    @Override
    public Collection<DatabasePacket> execute() {
        return Collections.<DatabasePacket>singletonList(new MySQLOKPacket(1));
    }
}
