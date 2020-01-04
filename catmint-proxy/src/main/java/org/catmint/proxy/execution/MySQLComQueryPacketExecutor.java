package org.catmint.proxy.execution;

import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.MySQLOKPacket;
import org.catmint.proxy.packet.command.MySQLComQueryPacket;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

/**
 * COM_QUERY command packet executor for MySQL.
 *
 * @author zhangliang
 */
public final class MySQLComQueryPacketExecutor implements QueryCommandExecutor {

    public MySQLComQueryPacketExecutor(final MySQLComQueryPacket comQueryPacket) {
    }

    @Override
    public Collection<DatabasePacket> execute() throws SQLException {
        // TODO
        return Collections.singleton(new MySQLOKPacket(1, 0, 0));
    }

    @Override
    public boolean isQuery() {
        return false;
    }

    @Override
    public boolean next() throws SQLException {
        return false;
    }

    @Override
    public DatabasePacket getQueryData() throws SQLException {
        return null;
    }
}

