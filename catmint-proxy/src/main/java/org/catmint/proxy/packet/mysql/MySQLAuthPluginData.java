package org.catmint.proxy.packet.mysql;

import com.google.common.primitives.Bytes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
@Getter
public final class MySQLAuthPluginData {

    private final byte[] authPluginDataPart1;

    private final byte[] authPluginDataPart2;

    public MySQLAuthPluginData() {
        this(MySQLRandomGenerator.getInstance().generateRandomBytes(8), MySQLRandomGenerator.getInstance().generateRandomBytes(12));
    }

    /**
     * Get auth plugin data.
     *
     * @return auth plugin data
     */
    public byte[] getAuthPluginData() {
        return Bytes.concat(authPluginDataPart1, authPluginDataPart2);
    }
}
