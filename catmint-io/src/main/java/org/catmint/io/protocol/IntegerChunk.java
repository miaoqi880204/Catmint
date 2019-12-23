package org.catmint.io.protocol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 整型分块
 *
 * @author Shuo Xiang
 */
@Getter
@ToString
@EqualsAndHashCode
public class IntegerChunk implements ChunkTranslatable {

    private static final byte MAX_LENGTH = 7;

    private final long srcData;
    private final byte length;

    public IntegerChunk(byte length, long srcData) {
        if (length < 1 || length > MAX_LENGTH) {
            throw new IllegalArgumentException("integer chunk length must be between 1 and " + MAX_LENGTH);
        }
        this.srcData = srcData;
        this.length = length;
    }

    @Override
    public byte[] translate() {
        if (length <= 0) {
            return null;
        }

        byte[] data = new byte[length];
        for (int i = 0, j = 8; i < length; i++) {
            if (i == 0) {
                data[i] = (byte) (srcData & 0xff);
            } else {
                data[i] = (byte) (srcData >>> j);
                j <<= 1;
            }
        }
        return data;
    }
}
