package org.catmint.proxy.frontend.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Proxy user.
 *
 * @author Shuo Xiang
 */
@Getter
@AllArgsConstructor
public final class ProxyUser {

    private final String username;
    private final String password;
    private final Set<String> schemas;
}
