package org.catmint.proxy.frontend.auth;

import io.netty.channel.ChannelHandlerContext;
import org.catmint.proxy.packet.PacketPayload;
import org.catmint.proxy.packet.mysql.MySQLAuthPluginData;

/**
 * <p>Title:登录校验引擎</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public interface AuthenticationEngine {
    //登录验证
    boolean auth(ChannelHandlerContext context, PacketPayload payload);

    MySQLAuthPluginData getAuthPluginData();
}
