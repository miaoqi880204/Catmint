package org.catmint.io.protocol.mysql;

import lombok.Data;
import org.catmint.io.protocol.PacketChunk;

import java.util.List;

/**
 * MySQL协议 握手包<br />
 * <a href="https://dev.mysql.com/doc/internals/en/connection-phase-packets.html#packet-Protocol::Handshake">MySQL 官方文档</a>
 *
 * @author Shuo Xiang
 */
@Data
public class HandshakeV10Packet extends MySQLPacket {

    private List<? extends PacketChunk> chunks;

}
