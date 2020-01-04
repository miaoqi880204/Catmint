package org.catmint.proxy.packet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Server error code for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/refman/5.7/en/error-messages-server.html">error-messages-server</a>
 */
@RequiredArgsConstructor
@Getter
public enum MySQLServerErrorCode implements SQLErrorCode {

    ER_DBACCESS_DENIED_ERROR(1044, "42000", "Access denied for user '%s'@'%s' to database '%s'"),

    ER_ACCESS_DENIED_ERROR(1045, "28000", "Access denied for user '%s'@'%s' (using password: %s)"),

    ER_NO_DB_ERROR(1046, "3D000", "No database selected"),

    ER_BAD_DB_ERROR(1049, "42000", "Unknown database '%s'"),

    ER_ERROR_ON_MODIFYING_GTID_EXECUTED_TABLE(3176, "HY000",
            "Please do not modify the %s table with an XA transaction. This is an internal system table used to store GTIDs for committed transactions. "
                    + "Although modifying it can lead to an inconsistent GTID state, if neccessary you can modify it with a non-XA transaction.");

    private final int errorCode;

    private final String sqlState;

    private final String errorMessage;
}
