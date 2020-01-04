package org.catmint.proxy.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public final class ConnectionIdGenerator {

    private static final ConnectionIdGenerator INSTANCE = new ConnectionIdGenerator();

    private int currentId;

    /**
     * Get instance.
     *
     * @return instance
     */
    public static ConnectionIdGenerator getInstance() {
        return INSTANCE;
    }

    /**
     * Get next connection ID.
     *
     * @return next connection ID
     */
    public synchronized int nextId() {
        if (currentId >= Integer.MAX_VALUE) {
            currentId = 0;
        }
        return ++currentId;
    }
}

