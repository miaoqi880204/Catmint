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

    private int packetLength;
    private byte packetId;

    /**
     * 计算数据包大小，不包含包头长度
     *
     * @return
     */
    public abstract int calcPacketSize();
}
