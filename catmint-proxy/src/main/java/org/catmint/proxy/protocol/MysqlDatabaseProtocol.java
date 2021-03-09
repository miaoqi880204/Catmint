package org.catmint.proxy.protocol;

import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;
import org.catmint.core.common.utils.CatmintConfigUtils;
import org.catmint.core.config.define.BaseConf;
import org.catmint.core.config.define.ProxyUser;
import org.catmint.proxy.execution.CommandExecutionEngine;
import org.catmint.proxy.execution.CommandExecutionFactory;
import org.catmint.proxy.frontend.auth.AuthenticationEngine;
import org.catmint.proxy.frontend.auth.mysql.MySQLAuthenticator;
import org.catmint.proxy.net.codec.DatabasePacketCodecEngine;
import org.catmint.proxy.net.codec.mysql.MySQLPacketCodecEngine;
import org.catmint.proxy.packet.PacketPayload;
import org.catmint.proxy.packet.mysql.MySQLPacketPayload;

import java.util.Optional;

/**
 * <p>Title:DatabaseProtocolEngine mysql 实现</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class MysqlDatabaseProtocol implements DatabaseProtocolEngine {
    private static final DatabasePacketCodecEngine DATABASE_PACKET_CODEC_ENGINE = new MySQLPacketCodecEngine();
    private static final AuthenticationEngine AUTHENTICATION_ENGINE = new MySQLAuthenticator();
    private static final CommandExecutionEngine COMMAND_EXECUTION_ENGINE = CommandExecutionFactory.getCommandExecutionEngine( CatmintConfigUtils.getBaseConf().getDialect() );

    @Override
    public PacketPayload getPacketPayload(final ByteBuf message) {
        return new MySQLPacketPayload( message );
    }

    @Override
    public DatabasePacketCodecEngine getCodecEngine() {
        return DATABASE_PACKET_CODEC_ENGINE;
    }

    @Override
    public AuthenticationEngine getAuthEngine() {
        return AUTHENTICATION_ENGINE;
    }

    @Override
    public CommandExecutionEngine getCommandExecutionEngine() {
        return COMMAND_EXECUTION_ENGINE;
    }

    @Override
    public Optional<ProxyUser> getProxyUser(final String userName) {
        return CatmintConfigUtils.getProxyUser( userName );
    }

    @Override
    @SneakyThrows
    public BaseConf getBaseConf() {
        return CatmintConfigUtils.getBaseConf();
    }
}
