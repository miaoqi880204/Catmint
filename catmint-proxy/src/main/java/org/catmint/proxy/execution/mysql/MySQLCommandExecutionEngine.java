package org.catmint.proxy.execution.mysql;

import io.netty.buffer.ByteBuf;
import org.catmint.proxy.execution.CommandExecutionEngine;
import org.catmint.proxy.execution.CommandExecutor;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;
import org.catmint.proxy.packet.mysql.MySQLPacketPayload;
import org.catmint.proxy.packet.mysql.command.MySQLCommandPacket;
import org.catmint.proxy.packet.mysql.command.MySQLCommandPacketFactory;
import org.catmint.proxy.packet.mysql.command.MySQLCommandPacketTypeLoader;
import org.catmint.proxy.protocol.DatabaseProtocolEngine;

import java.sql.SQLException;
import java.util.Collection;

/**
 * MySQL 命令执行器
 *
 * @author Shuo Xiang
 */
public final class MySQLCommandExecutionEngine implements CommandExecutionEngine {

    /*
     * <p>Title:执行SQL</p>
     * <p>Description:</p>
     * @author QIQI
     * @params [message]
     * @return java.util.Collection<org.catmint.proxy.packet.DatabasePacket>
     * @throws
     * @date 2021/3/9 下午4:47
     */
    public Collection<DatabasePacket> executeCommand(final DatabaseProtocolEngine databaseProtocolEngine,final ByteBuf message) throws SQLException {
        //生成数据载体
        MySQLPacketPayload payload = (MySQLPacketPayload) databaseProtocolEngine.getPacketPayload( message );
        //获取命令解析模式
        MySQLCommandPacketType packetType = MySQLCommandPacketTypeLoader.getCommandPacketType( payload );
        //解码获取SQL
        MySQLCommandPacket commandPacket = MySQLCommandPacketFactory.newInstance( packetType, payload );
        //选择执行器
        CommandExecutor commandExecutor = MySQLCommandExecutorFactory.newInstance( packetType, commandPacket );
        return commandExecutor.execute();
    }
}
