package org.catmint.io.protocol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

/**
 * 字符串分块
 *
 * @author Shuo Xiang
 */
@Getter
@ToString
@EqualsAndHashCode
public class StringChunk implements ChunkTranslatable {

    private final boolean tailNull;
    private final String srcData;

    public StringChunk(String srcData) {
        this(srcData, false);
    }

    public StringChunk(String srcData, boolean tailNull) {
        this.srcData = srcData;
        this.tailNull = tailNull;
    }

    @Override
    public byte[] translate() {
        byte[] data = srcData.getBytes();
        if (tailNull) {
            int length = data.length + 1;
            data = Arrays.copyOf(data, length);
            data[length] = 0;
        }
        return data;
    }
}
