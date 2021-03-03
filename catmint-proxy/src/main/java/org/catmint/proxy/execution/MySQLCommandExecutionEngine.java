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
        //生成数据载体
        MySQLPacketPayload payload = new MySQLPacketPayload(message);
        //获取命令解析模式
        MySQLCommandPacketType packetType = MySQLCommandPacketTypeLoader.getCommandPacketType(payload);
        //解码获取SQL
        MySQLCommandPacket commandPacket = MySQLCommandPacketFactory.newInstance(packetType, payload);
        //选择执行器
        CommandExecutor commandExecutor = MySQLCommandExecutorFactory.newInstance(packetType, commandPacket);
        Collection<DatabasePacket> responsePackets = commandExecutor.execute();
        for (DatabasePacket each : responsePackets) {
            context.write(each);
        }
        context.flush();
    }
}
