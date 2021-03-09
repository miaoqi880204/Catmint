package org.catmint.proxy.protocol;

import io.netty.buffer.ByteBuf;
import org.catmint.core.config.define.BaseConf;
import org.catmint.core.config.define.ProxyUser;
import org.catmint.proxy.execution.CommandExecutionEngine;
import org.catmint.proxy.frontend.auth.AuthenticationEngine;
import org.catmint.proxy.net.codec.DatabasePacketCodecEngine;
import org.catmint.proxy.packet.PacketPayload;

import java.util.Optional;

/**
* <p>Title:初始化编码引擎、权限校验引擎、命令执行引擎</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/8 下午3:10 
*/
public interface DatabaseProtocolEngine{
    //报文解析
    PacketPayload getPacketPayload(final ByteBuf message);
    
    //编码引擎
    DatabasePacketCodecEngine getCodecEngine();
    
    //权限校验引擎
    AuthenticationEngine getAuthEngine();

    //执行器引擎
    CommandExecutionEngine getCommandExecutionEngine();

    //用户配置信息
    Optional<ProxyUser> getProxyUser(final String userName);

    //基础配置信息
    BaseConf getBaseConf();
}
