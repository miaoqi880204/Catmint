package org.catmint.io.protocol;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 报文包中的分块
 *
 * @author Shuo Xiang
 */
@ToString
@EqualsAndHashCode
public abstract class PacketChunk {

    /** 分块长度，-1 表示不定长 */
    private byte length;

    private byte[] data;

    public PacketChunk() {
    }

    public PacketChunk(byte length, byte[] data) {
        this.length = length;
        this.data = data;
    }

    /**
     * 转化为byte数组
     *
     * @return
     */
    public abstract byte[] translate();

    public byte getLength() {
        return length;
    }

    protected void setLength(byte length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    protected void setData(byte[] data) {
        this.data = data;
    }

}
