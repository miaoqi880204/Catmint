package org.catmint.proxy.execution;

import lombok.extern.slf4j.Slf4j;
import org.catmint.proxy.packet.DatabasePacket;
import org.catmint.proxy.packet.MySQLOKPacket;
import org.catmint.proxy.packet.command.MySQLComQueryPacket;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

/*
* <p>Title:Mysql 非预编译查询解析执行</p>
* <p>Description:COM_QUERY</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/3 下午9:11 
*/
@Slf4j
public final class MySQLComQueryPacketExecutor implements QueryCommandExecutor {

    public MySQLComQueryPacketExecutor(final MySQLComQueryPacket comQueryPacket) {
        log.info("sql is {}", comQueryPacket.getSql());
    }

    @Override
    public Collection<DatabasePacket> execute() throws SQLException {
        return Collections.singleton(new MySQLOKPacket(1, 0, 0));
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

