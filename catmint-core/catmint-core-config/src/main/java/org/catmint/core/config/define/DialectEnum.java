package org.catmint.core.config.define;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@AllArgsConstructor
public enum  DialectEnum {
    MYSQL("MySQL");

    @Getter
    private String name;
}
