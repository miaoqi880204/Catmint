package org.catmint.io.protocol.mysql;

import lombok.Data;

/**
 * MySQL 协议包
 *
 * @author Shuo Xiang
 */
@Data
public abstract class MySQLPacket {

    /** 报文头字节数 */
    public static final int PACKET_HEADER_SIZE = 4;
    /** 报文最大字节数 */
    public static final int MAX_PACKET_SIZE = 16 * 1024 * 1024;

    private int payloadLength;

    private byte sequenceId;

    public MySQLPacket() {}

    public MySQLPacket(int payloadLength, byte sequenceId) {
        this.payloadLength = payloadLength;
        this.sequenceId = sequenceId;
    }
}
