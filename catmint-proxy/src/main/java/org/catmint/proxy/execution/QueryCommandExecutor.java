package org.catmint.proxy.execution;

import org.catmint.proxy.packet.DatabasePacket;

import java.sql.SQLException;

/**
 * Query command executor.
 *
 * @author Shuo Xiang
 */
public interface QueryCommandExecutor extends CommandExecutor {

    /**
     * Goto next result value.
     *
     * @return has more result value or not
     * @throws SQLException SQL exception
     */
    boolean next() throws SQLException;

    /**
     * Get query data.
     *
     * @return database packet of query data
     * @throws SQLException SQL exception
     */
    DatabasePacket getQueryData() throws SQLException;
}
