package org.catmint.io.protocol.mysql;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.catmint.io.utilities.ByteBufUtils;

/**
 * MySQL协议 握手包<br />
 * <a href="https://dev.mysql.com/doc/internals/en/connection-phase-packets.html#packet-Protocol::Handshake">MySQL 官方文档</a>
 *
 * @author Shuo Xiang
 */
@EqualsAndHashCode(callSuper = true)
public class HandshakePacket extends MySQLPacket {

    private static final byte DEFAULT_SEQUENCE_ID = 0;
    private static final byte[] DEFAULT_AUTH_PLUGIN_NAME = "mysql_native_password".getBytes();
    private static final byte[] FILLER_10 = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private byte protocolVersion;
    private byte[] serverVersion;
    private long connectionId;
    private byte[] seedPart1;
    private int capabilities;
    private byte charsetId;
    private int statusFlag;
    private byte[] seedPart2;
    private byte[] authPluginName;

    private HandshakePacket(int payloadLength, byte sequenceId, byte protocolVersion,
                            byte[] serverVersion, long connectionId, byte[] seedPart1,
                            int capabilities, byte charsetId, int statusFlag,
                            byte[] seedPart2, byte[] authPluginName) {
        super(payloadLength, sequenceId);
        this.protocolVersion = protocolVersion;
        this.serverVersion = serverVersion;
        this.connectionId = connectionId;
        this.seedPart1 = seedPart1;
        this.capabilities = capabilities;
        this.charsetId = charsetId;
        this.statusFlag = statusFlag;
        this.seedPart2 = seedPart2;
        this.authPluginName = authPluginName;
    }

    public void write(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();

        ByteBufUtils.writeUpper3(byteBuf, super.getPayloadLength());
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

    public static HandshakePacketBuilder builder() {
        return new HandshakePacketBuilder();
    }

    public static final class HandshakePacketBuilder {

        private byte protocolVersion = Versions.PROTOCOL_VERSION;
        private byte[] serverVersion = Versions.SERVER_VERSION;
        private long connectionId;
        private byte[] seedPart1;
        private int capabilities;
        private byte charsetId;
        private int statusFlag = StatusFlags.AUTO_COMMIT.getFlag();
        private byte[] seedPart2;
        private byte[] authPluginName = DEFAULT_AUTH_PLUGIN_NAME;

        private HandshakePacketBuilder() {
        }

        public HandshakePacketBuilder protocolVersion(byte protocolVersion) {
            this.protocolVersion = protocolVersion;
            return this;
        }

        public HandshakePacketBuilder serverVersion(byte[] serverVersion) {
            this.serverVersion = serverVersion;
            return this;
        }

        public HandshakePacketBuilder connectionId(long connectionId) {
            this.connectionId = connectionId;
            return this;
        }

        public HandshakePacketBuilder seedPart1(byte[] seedPart1) {
            this.seedPart1 = seedPart1;
            return this;
        }

        public HandshakePacketBuilder capabilities(int capabilities) {
            this.capabilities = capabilities;
            return this;
        }

        public HandshakePacketBuilder charsetId(byte charsetId) {
            this.charsetId = charsetId;
            return this;
        }

        public HandshakePacketBuilder statusFlag(int statusFlag) {
            this.statusFlag = statusFlag;
            return this;
        }

        public HandshakePacketBuilder seedPart2(byte[] seedPart2) {
            this.seedPart2 = seedPart2;
            return this;
        }

        public HandshakePacketBuilder authPluginName(byte[] authPluginName) {
            this.authPluginName = authPluginName;
            return this;
        }

        public HandshakePacket build() {
            int payloadLength = calcPayloadLength();
            return new HandshakePacket(
                    payloadLength, DEFAULT_SEQUENCE_ID, protocolVersion,
                    serverVersion, connectionId, seedPart1,
                    capabilities, charsetId, statusFlag,
                    seedPart2, authPluginName);
        }

        private int calcPayloadLength() {
            // TODO 根据文档整理
            int payloadLength = 1; // protocol version
            payloadLength += (serverVersion.length + 1); // server version
            payloadLength += 4; // connection id
            payloadLength += seedPart1.length;
            payloadLength += 1; // [00] filler
            payloadLength += 2; // capability flags (lower 2 bytes)
            payloadLength += 1; // character set
            payloadLength += 2; // status flags
            payloadLength += 2; // capability flags (upper 2 bytes)
            payloadLength += 1;
            payloadLength += 10; // reserved (all [00])
            if ((capabilities & Capabilities.CLIENT_SECURE_CONNECTION) != 0) {
                // restOfScrambleBuff.length always to be 12
                if (seedPart2.length <= 13) {
                    payloadLength += 13;
                } else {
                    payloadLength += seedPart2.length;
                }
            }
            if ((capabilities & Capabilities.CLIENT_PLUGIN_AUTH) != 0) {
                payloadLength += (authPluginName.length + 1); // auth-plugin name
            }
            return payloadLength;
        }

    }
}
