package org.catmint.proxy.execution;

import io.netty.buffer.ByteBuf;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.protocol.DatabaseProtocolEngine;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <p>Title:全局执行器接口</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public interface CommandExecutionEngine {
    Collection<DatabasePacket> executeCommand(final DatabaseProtocolEngine databaseProtocolEngine, final ByteBuf message) throws SQLException;
}
