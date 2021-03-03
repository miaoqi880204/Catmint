package org.catmint.core.config.define;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Getter
@AllArgsConstructor
public final class ProxyUser {
    private static final long serialVersionUID = -9029707671305743359L;
    private final String userName;
    private final String password;
    private final Collection<String> database;
}
