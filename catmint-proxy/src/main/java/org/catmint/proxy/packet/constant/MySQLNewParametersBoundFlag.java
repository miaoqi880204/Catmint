package org.catmint.proxy.packet.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * New parameters bound flag for MySQL.
 *
 * @author Shuo Xiang
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-stmt-execute.html">COM_STMT_EXECUTE</a>
 */
@RequiredArgsConstructor
@Getter
public enum MySQLNewParametersBoundFlag {

    PARAMETER_TYPE_EXIST(1),

    PARAMETER_TYPE_NOT_EXIST(0);

    private final int value;

    /**
     * Value of new parameters bound flag.
     *
     * @param value value
     * @return new parameters bound flag
     */
    public static MySQLNewParametersBoundFlag valueOf(final int value) {
        for (MySQLNewParametersBoundFlag each : MySQLNewParametersBoundFlag.values()) {
            if (value == each.value) {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("Cannot find value '%s' in new parameters bound flag", value));
    }
}

