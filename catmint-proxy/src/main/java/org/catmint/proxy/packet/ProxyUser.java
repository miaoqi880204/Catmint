package org.catmint.proxy.packet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

/**
 * Proxy user.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
@Getter
public final class ProxyUser {

    private final String password;

    private final Collection<String> authorizedSchemas;
}
