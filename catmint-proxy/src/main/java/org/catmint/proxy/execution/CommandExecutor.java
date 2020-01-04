package org.catmint.proxy.execution;

import org.catmint.proxy.packet.DatabasePacket;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Command executor.
 *
 * @author Shuo Xiang
 */
public interface CommandExecutor {

    /**
     * Execute command.
     *
     * @return database packets to be sent
     * @throws SQLException SQL exception
     */
    Collection<DatabasePacket> execute() throws SQLException;
}

