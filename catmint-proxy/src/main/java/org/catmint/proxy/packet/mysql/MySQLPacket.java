package org.catmint.proxy.packet.mysql;

import org.catmint.proxy.packet.DatabasePacket;

/**
 * MySQL报文
 *
 * @author Shuo Xiang
 */
public interface MySQLPacket extends DatabasePacket<MySQLPacketPayload> {

    int PAYLOAD_LENGTH = 3;

    int SEQUENCE_LENGTH = 1;

    /**
     * Get sequence ID.
     *
     * @return sequence ID
     */
    int getSequenceId();
}
