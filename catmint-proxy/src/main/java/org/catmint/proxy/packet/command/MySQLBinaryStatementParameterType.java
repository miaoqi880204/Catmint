package org.catmint.proxy.packet.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.catmint.proxy.packet.constant.MySQLColumnType;

/**
 * Binary prepared statement parameter type for MySQL.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
@Getter
public final class MySQLBinaryStatementParameterType {

    private final MySQLColumnType columnType;

    private final int unsignedFlag;
}
