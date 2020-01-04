package org.catmint.proxy.packet.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Binary prepared statement for MySQL.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
@Getter
@Setter
public final class MySQLBinaryStatement {

    private final String sql;

    private final int parametersCount;

    private List<MySQLBinaryStatementParameterType> parameterTypes;
}
