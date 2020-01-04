package org.catmint.proxy.packet.command;

import lombok.Getter;

/**
 * Null bitmap for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/null-bitmap.html">NULL-Bitmap</a>
 */
public final class MySQLNullBitmap {

    private final int offset;

    @Getter
    private final int[] nullBitmap;

    public MySQLNullBitmap(final int columnsNumbers, final int offset) {
        this.offset = offset;
        nullBitmap = new int[calculateLength(columnsNumbers, offset)];
    }

    private int calculateLength(final int columnsNumbers, final int offset) {
        return (columnsNumbers + offset + 7) / 8;
    }

    /**
     * Judge parameter is null or not null.
     *
     * @param index column index
     * @return parameter is null or not null
     */
    public boolean isNullParameter(final int index) {
        return (nullBitmap[getBytePosition(index)] & (1 << getBitPosition(index))) != 0;
    }

    /**
     * Set null bit.
     *
     * @param index column index
     */
    public void setNullBit(final int index) {
        nullBitmap[getBytePosition(index)] = 1 << getBitPosition(index);
    }

    private int getBytePosition(final int index) {
        return (index + offset) / 8;
    }

    private int getBitPosition(final int index) {
        return (index + offset) % 8;
    }
}
