package org.catmint.context.localcache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProxyUserThreadLocal {
    public static final ThreadLocal<String> USER_NAME_THREAD_LOCAL = new ThreadLocal<>();

}
