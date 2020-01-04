package org.catmint.proxy.execution;

import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.command.MySQLComStmtExecutePacket;

import java.sql.SQLException;
import java.util.Collection;

/**
 * COM_STMT_EXECUTE command executor for MySQL.
 *
 * @author Shuo Xiang
 */
public final class MySQLComStmtExecuteExecutor implements QueryCommandExecutor {

    public MySQLComStmtExecuteExecutor(final MySQLComStmtExecutePacket comStmtExecutePacket) {
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

    @Override
    public Collection<DatabasePacket> execute() throws SQLException {
        // TODO
        return null;
    }
}

