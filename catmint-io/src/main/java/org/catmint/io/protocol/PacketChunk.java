package org.catmint.io.protocol;

import lombok.Data;

/**
 * 报文包中的分块
 *
 * @author Shuo Xiang
 */
@Data
public abstract class PacketChunk {

    /** 分块长度，-1 表示不定长 */
    private byte length;

    private byte[] payload;
}
