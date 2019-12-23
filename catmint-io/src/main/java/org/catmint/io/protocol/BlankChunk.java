package org.catmint.io.protocol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 空分块
 *
 * @author Shuo Xiang
 */
@Getter
@ToString
@EqualsAndHashCode
public class BlankChunk implements ChunkTranslatable {

    private final int length;

    public BlankChunk(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("blank chunk length must be greater than 0");
        }
        this.length = length;
    }

    @Override
    public byte[] translate() {
        byte[] data = new byte[length];
        for (int i = 0; i < data.length; i++) {
            data[i] = 0;
        }
        return data;
    }
}
