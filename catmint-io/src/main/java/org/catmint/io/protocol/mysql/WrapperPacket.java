package org.catmint.io.protocol.mysql;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MySQL协议 外层包
 *
 * @author Shuo Xiang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WrapperPacket extends MySQLPacket {

    private byte[] data;

    @Override
    public int calcPacketSize() {
        return data == null ? 0 : data.length;
    }
}