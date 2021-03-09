package org.catmint.proxy.packet.mysql;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.catmint.proxy.packet.constant.MySQLCapabilityFlag;
import org.catmint.proxy.packet.constant.MySQLServerInfo;
import org.catmint.proxy.packet.constant.MySQLStatusFlag;

/**
 * @author Shuo Xiang
 */
public final class MySQLHandshakePacket implements MySQLPacket {

    private final int protocolVersion = MySQLServerInfo.PROTOCOL_VERSION;

    private final String serverVersion = MySQLServerInfo.SERVER_VERSION;

    private final int capabilityFlagsLower = MySQLCapabilityFlag.calculateHandshakeCapabilityFlagsLower();

    private final MySQLStatusFlag statusFlag = MySQLStatusFlag.SERVER_STATUS_AUTOCOMMIT;

    private final int capabilityFlagsUpper = MySQLCapabilityFlag.calculateHandshakeCapabilityFlagsUpper();

    @Getter
    private final int connectionId;

    @Getter
    private final MySQLAuthPluginData authPluginData;

    public MySQLHandshakePacket(final int connectionId, final MySQLAuthPluginData authPluginData) {
        this.connectionId = connectionId;
        this.authPluginData = authPluginData;
    }

    public MySQLHandshakePacket(final MySQLPacketPayload payload) {
        Preconditions.checkArgument(0 == payload.readInt1(), "Sequence ID of MySQL handshake packet must be `0`.");
        Preconditions.checkArgument(protocolVersion == payload.readInt1());
        payload.readStringNul();
        connectionId = payload.readInt4();
        final byte[] authPluginDataPart1 = payload.readStringNulByBytes();
        payload.readInt2();
        payload.readInt1();
        Preconditions.checkArgument(statusFlag.getValue() == payload.readInt2());
        payload.readInt2();
        payload.readInt1();
        payload.skipReserved(10);
        byte[] authPluginDataPart2 = payload.readStringNulByBytes();
        authPluginData = new MySQLAuthPluginData(authPluginDataPart1, authPluginDataPart2);
    }

    @Override
    public void write(final MySQLPacketPayload payload) {
        payload.writeInt1(protocolVersion);
        payload.writeStringNul(serverVersion);
        payload.writeInt4(connectionId);
        payload.writeStringNul(new String(authPluginData.getAuthPluginDataPart1()));
        payload.writeInt2(capabilityFlagsLower);
        payload.writeInt1(MySQLServerInfo.CHARSET);
        payload.writeInt2(statusFlag.getValue());
        payload.writeInt2(capabilityFlagsUpper);
        payload.writeInt1(0);
        payload.writeReserved(10);
        payload.writeStringNul(new String(authPluginData.getAuthPluginDataPart2()));
    }

    @Override
    public int getSequenceId() {
        return 0;
    }
}