package org.catmint.proxy.frontend.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.catmint.beanfactory.BeanFactory;
import org.catmint.config.model.LogicDB;
import org.catmint.config.model.ProxyConfig;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Auth context.
 *
 * @author Shuo Xiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthContext {

    private static final AuthContext INSTANCE = new AuthContext();

    public static AuthContext getInstance() {
        return INSTANCE;
    }

    /**
     * Get Users.
     *
     * @return users
     */
    public List<ProxyUser> getUsers() {
        List<ProxyUser> users = new LinkedList<>();
        ProxyConfig proxyConfig = BeanFactory.getBeanSingleton(ProxyConfig.class);
        for (LogicDB logicDB : proxyConfig.getLogicDBS()) {
            users.add(new ProxyUser(
                    logicDB.getDatabaseConect().getUser(),
                    logicDB.getDatabaseConect().getPassword(),
                    Collections.singleton(logicDB.getLogicName())));
        }
        return users;
    }

    /**
     * Get Schemas.
     *
     * @return schemas
     */
    public List<String> getSchemas() {
        List<String> schemas = new LinkedList<>();
        ProxyConfig proxyConfig = BeanFactory.getBeanSingleton(ProxyConfig.class);
        for (LogicDB logicDB : proxyConfig.getLogicDBS()) {
            schemas.add(logicDB.getLogicName());
        }
        return schemas;
    }
}
