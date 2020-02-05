package org.catmint.core.engine;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Getter
@ToString
public final class ProxyUser implements Serializable {
    private static final long serialVersionUID = -9029707671305743359L;
    private String userName;
    private String password;
    private Set<String> database;

    public ProxyUser(String userName, String password, Set<String> database) {
        this.userName = userName;
        this.password = password;
        this.database = database;
    }
}
