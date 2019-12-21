package org.catmint.io.utilities;

import io.netty.buffer.ByteBuf;

/**
 * ByteBuf 工具类
 *
 * @author Shuo Xiang
 */
public final class ByteBufUtils {

    private ByteBufUtils() {
    }

    public static final void writeUpper2(ByteBuf byteBuf, int i) {
        byteBuf.writeByte((byte) (i & 0xff));
        byteBuf.writeByte((byte) (i >>> 8));
    }

    public static final void writeUpper3(ByteBuf byteBuf, int i) {
        byteBuf.writeByte((byte) (i & 0xff));
        byteBuf.writeByte((byte) (i >>> 8));
        byteBuf.writeByte((byte) (i >>> 16));
    }

    public static final void writeUpper4(ByteBuf byteBuf, long l) {
        byteBuf.writeByte((byte) (l & 0xff));
        byteBuf.writeByte((byte) (l >>> 8));
        byteBuf.writeByte((byte) (l >>> 16));
        byteBuf.writeByte((byte) (l >>> 24));
    }

    public static final void writeWithNull(ByteBuf byteBuf, byte[] bs) {
        byteBuf.writeBytes(bs);
        byteBuf.writeByte((byte) 0);
    }

    public static int readUpper2(ByteBuf byteBuf) {
        int i = byteBuf.readByte() & 0xff;
        i |= (byteBuf.readByte() & 0xff) << 8;
        return i;
    }

    public static int readUpper3(ByteBuf byteBuf) {
        int i = byteBuf.readByte() & 0xff;
        i |= (byteBuf.readByte() & 0xff) << 8;
        i |= (byteBuf.readByte() & 0xff) << 16;
        return i;
    }

    public static long readUpper4(ByteBuf byteBuf) {
        long l = byteBuf.readByte() & 0xff;
        l |= (byteBuf.readByte() & 0xff) << 8;
        l |= (byteBuf.readByte() & 0xff) << 16;
        l |= (byteBuf.readByte() & 0xff) << 24;
        return l;
    }
}
