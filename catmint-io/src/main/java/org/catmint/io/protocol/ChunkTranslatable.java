package org.catmint.io.protocol;

/**
 * 报文报分块转换功能，根据协议将源数据转换为二进制数组
 *
 * @author Shuo Xiang
 */
public interface ChunkTranslatable {

    byte[] translate();
}
