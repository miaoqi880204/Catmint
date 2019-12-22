package org.catmint.io.protocol.mysql;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.catmint.io.utilities.ByteBufUtils;

/**
 * MySQL协议 握手包<br />
 * <a href="https://dev.mysql.com/doc/internals/en/connection-phase-packets.html#packet-Protocol::Handshake">MySQL 官方文档</a>
 *
 * @author Shuo Xiang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HandshakePacket extends MySQLPacket {

    private static final byte DEFAULT_PACKET_ID = 0;
    private static final byte[] DEFAULT_AUTH_PLUGIN_NAME = "mysql_native_password".getBytes();
    private static final byte[] FILLER_10 = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public HandshakePacket() {
        super.setSequenceId(DEFAULT_PACKET_ID);
    }

    private byte protocolVersion;
    private byte[] serverVersion;
    private long connectionId;
    private byte[] seedPart1;
    private int capabilities;
    private byte charsetId;
    private int statusFlag;
    private byte[] seedPart2;
    private byte[] authPluginName = DEFAULT_AUTH_PLUGIN_NAME;

    public void write(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();

        ByteBufUtils.writeUpper3(byteBuf, calcPacketSize());
        byteBuf.writeByte(getSequenceId());
        byteBuf.writeByte(protocolVersion);
        ByteBufUtils.writeWithNull(byteBuf, serverVersion);
        ByteBufUtils.writeUpper4(byteBuf, connectionId);
        byteBuf.writeBytes(seedPart1);
        byteBuf.writeByte((byte) 0);
        ByteBufUtils.writeUpper2(byteBuf, capabilities);
        byteBuf.writeByte(charsetId);
        ByteBufUtils.writeUpper2(byteBuf, statusFlag);
        ByteBufUtils.writeUpper2(byteBuf, (capabilities >> 16));
        if ((capabilities & Capabilities.CLIENT_PLUGIN_AUTH) != 0) {
            if (seedPart2.length <= 13) {
                byteBuf.writeByte((byte) (seedPart1.length + 13));
            } else {
                byteBuf.writeByte((byte) (seedPart1.length + seedPart2.length));
            }
        } else {
            byteBuf.writeByte((byte) 0);
        }
        byteBuf.writeBytes(FILLER_10);
        if ((capabilities & Capabilities.CLIENT_SECURE_CONNECTION) != 0) {
            byteBuf.writeBytes(seedPart2);
            // restOfScrambleBuff.length always to be 12
            if (seedPart2.length < 13) {
                for (int i = 13 - seedPart2.length; i > 0; i--) {
                    byteBuf.writeByte((byte) 0);
                }
            }
        }
        if ((capabilities & Capabilities.CLIENT_PLUGIN_AUTH) != 0) {
            ByteBufUtils.writeWithNull(byteBuf, authPluginName);
        }
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public int calcPacketSize() {
        int size = 1; // protocol version
        size += (serverVersion.length + 1); // server version
        size += 4; // connection id
        size += seedPart1.length;
        size += 1; // [00] filler
        size += 2; // capability flags (lower 2 bytes)
        size += 1; // character set
        size += 2; // status flags
        size += 2; // capability flags (upper 2 bytes)
        size += 1;
        size += 10; // reserved (all [00])
        if ((capabilities & Capabilities.CLIENT_SECURE_CONNECTION) != 0) {
            // restOfScrambleBuff.length always to be 12
            if (seedPart2.length <= 13) {
                size += 13;
            } else {
                size += seedPart2.length;
            }
        }
        if ((capabilities & Capabilities.CLIENT_PLUGIN_AUTH) != 0) {
            size += (authPluginName.length + 1); // auth-plugin name
        }
        return size;
    }
}
