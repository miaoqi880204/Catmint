package org.catmint.proxy.frontend.auth;

import com.google.common.base.Strings;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.catmint.core.engine.CatmintConfigEngine;
import org.catmint.core.engine.ProxyUser;
import org.catmint.proxy.packet.*;
import org.catmint.proxy.utilities.DigestUtils;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * MySQL 鉴权
 *
 * @author Shuo Xiang
 */
@Getter
public final class MySQLAuthenticator {

    private final MySQLAuthPluginData authPluginData = new MySQLAuthPluginData();

    public boolean auth(final ChannelHandlerContext context,  final PacketPayload payload) {
        MySQLHandshakeResponse41Packet response41 = new MySQLHandshakeResponse41Packet((MySQLPacketPayload) payload);
        if (!Strings.isNullOrEmpty(response41.getDatabase()) && !CatmintConfigEngine.getSchemaDatabaseNodeToString(response41.getUsername()).contains( response41.getDatabase() )) {
            context.writeAndFlush(new MySQLErrPacket(response41.getSequenceId() + 1, MySQLServerErrorCode.ER_BAD_DB_ERROR, response41.getDatabase()));
            return true;
        }
        Optional<MySQLServerErrorCode> errorCode = login(response41);
        if (errorCode.isPresent()) {
            context.writeAndFlush(getMySQLErrPacket(errorCode.get(), context, response41));
        } else {
            context.writeAndFlush(new MySQLOKPacket(response41.getSequenceId() + 1));
        }
        return true;
    }

    private Optional<MySQLServerErrorCode> login(final MySQLHandshakeResponse41Packet response41) {
        Optional<ProxyUser> user = Optional.ofNullable( CatmintConfigEngine.getServerConfUser( response41.getUsername() ) );
        if (!user.isPresent() || !isPasswordRight(user.get().getPassword(), response41.getAuthResponse())) {
            return Optional.of(MySQLServerErrorCode.ER_ACCESS_DENIED_ERROR);
        }
        if (!isAuthorizedSchema(user.get().getDatabase(), response41.getDatabase())) {
            return Optional.of(MySQLServerErrorCode.ER_DBACCESS_DENIED_ERROR);
        }
        return Optional.empty();
    }

    private boolean isPasswordRight(final String password, final byte[] authResponse) {
        return Strings.isNullOrEmpty(password) || Arrays.equals(getAuthCipherBytes(password), authResponse);
    }

    private boolean isAuthorizedSchema(final Collection<String> authorizedSchemas, final String schema) {
        return Strings.isNullOrEmpty(schema) || CollectionUtils.isEmpty(authorizedSchemas) || authorizedSchemas.contains(schema);
    }

    private byte[] getAuthCipherBytes(final String password) {
        byte[] sha1Password = DigestUtils.sha1(password);
        byte[] doubleSha1Password = DigestUtils.sha1(sha1Password);
        byte[] concatBytes = new byte[authPluginData.getAuthPluginData().length + doubleSha1Password.length];
        System.arraycopy(authPluginData.getAuthPluginData(), 0, concatBytes, 0, authPluginData.getAuthPluginData().length);
        System.arraycopy(doubleSha1Password, 0, concatBytes, authPluginData.getAuthPluginData().length, doubleSha1Password.length);
        byte[] sha1ConcatBytes = DigestUtils.sha1(concatBytes);
        return xor(sha1Password, sha1ConcatBytes);
    }

    private byte[] xor(final byte[] input, final byte[] secret) {
        final byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; ++i) {
            result[i] = (byte) (input[i] ^ secret[i]);
        }
        return result;
    }

    private MySQLErrPacket getMySQLErrPacket(final MySQLServerErrorCode errorCode, final ChannelHandlerContext context, final MySQLHandshakeResponse41Packet response41) {
        if (MySQLServerErrorCode.ER_DBACCESS_DENIED_ERROR == errorCode) {
            return new MySQLErrPacket(response41.getSequenceId() + 1, MySQLServerErrorCode.ER_DBACCESS_DENIED_ERROR, response41.getUsername(), getHostAddress(context), response41.getDatabase());
        } else {
            return new MySQLErrPacket(response41.getSequenceId() + 1, MySQLServerErrorCode.ER_ACCESS_DENIED_ERROR, response41.getUsername(), getHostAddress(context),
                    0 == response41.getAuthResponse().length ? "NO" : "YES");
        }
    }

    private String getHostAddress(final ChannelHandlerContext context) {
        SocketAddress socketAddress = context.channel().remoteAddress();
        return socketAddress instanceof InetSocketAddress ? ((InetSocketAddress) socketAddress).getAddress().getHostAddress() : socketAddress.toString();
    }
}
