package org.catmint.proxy.execution;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.MySQLCommandPacketType;
import org.catmint.proxy.packet.MySQLPacketPayload;
import org.catmint.proxy.packet.command.MySQLCommandPacket;
import org.catmint.proxy.packet.command.MySQLCommandPacketFactory;
import org.catmint.proxy.packet.command.MySQLCommandPacketTypeLoader;

import java.sql.SQLException;
import java.util.Collection;

/**
 * MySQL 命令执行器
 *
 * @author Shuo Xiang
 */
public final class MySQLCommandExecutionEngine {

    /**
     * 执行SQL命令
     *
     * @param context
     * @param message
     * @throws SQLException
     */
    public void executeCommand(final ChannelHandlerContext context, final ByteBuf message) throws SQLException {
        MySQLPacketPayload payload = new MySQLPacketPayload(message);
        MySQLCommandPacketType packetType = MySQLCommandPacketTypeLoader.getCommandPacketType(payload);
        MySQLCommandPacket commandPacket = MySQLCommandPacketFactory.newInstance(packetType, payload);
        CommandExecutor commandExecutor = MySQLCommandExecutorFactory.newInstance(packetType, commandPacket);
        Collection<DatabasePacket> responsePackets = commandExecutor.execute();
        for (DatabasePacket each : responsePackets) {
            context.write(each);
        }
        context.flush();
    }
}
