package org.catmint.proxy.packet;

import io.netty.channel.ChannelHandlerContext;
import org.catmint.proxy.execution.CommandExecutor;
import org.catmint.proxy.execution.QueryCommandExecutor;
import org.catmint.proxy.jdbc.connection.JDBCConnection;
import java.sql.SQLException;
import java.util.Optional;

/**
* <p>Title:命令执行引擎</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/8 下午3:52 
*/
public interface CommandExecuteEngine {

    /**
     * Get command packet type.
     *
     * @param packetPayload packet payload
     * @return command packet type
     */
    CommandPacketType getCommandPacketType(PacketPayload packetPayload);

    /**
     * Get command packet.
     *
     * @param payload        packet payload
     * @param type           command packet type
     * @param jdbcConnection backend connection
     * @return command packet
     * @throws SQLException SQL exception
     */
    DatabasePacket getCommandPacket(PacketPayload payload, CommandPacketType type, JDBCConnection jdbcConnection) throws SQLException;

    /**
     * Get command executor.
     *
     * @param type           command packet type
     * @param packet         command packet
     * @param jdbcConnection backend connection
     * @return command executor
     * @throws SQLException SQL exception
     */
    CommandExecutor getCommandExecutor(CommandPacketType type, DatabasePacket packet, JDBCConnection jdbcConnection) throws SQLException;

    /**
     * Get error packet.
     *
     * @param cause cause of error
     * @return error packet
     */
    DatabasePacket<?> getErrorPacket(Exception cause);

    /**
     * Get other packet.
     *
     * @return other packet
     */
    Optional<DatabasePacket<?>> getOtherPacket();

    /**
     * Write query data.
     *
     * @param context              channel handler context
     * @param jdbcConnection       backend connection
     * @param queryCommandExecutor query command executor
     * @param headerPackagesCount  count of header packages
     * @throws SQLException SQL exception
     */
    void writeQueryData(ChannelHandlerContext context, JDBCConnection jdbcConnection, QueryCommandExecutor queryCommandExecutor, int headerPackagesCount) throws SQLException;
}
