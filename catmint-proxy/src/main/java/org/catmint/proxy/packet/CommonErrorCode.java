package org.catmint.proxy.packet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Common error code.
 *
 * @author Shuo Xiang
 */
@RequiredArgsConstructor
@Getter
public enum CommonErrorCode implements SQLErrorCode {

    CIRCUIT_BREAK_MODE(10000, "C10000", "Circuit break mode is ON."),

    UNSUPPORTED_COMMAND(10001, "C10001", "Unsupported command: [%s]"),

    UNKNOWN_EXCEPTION(10002, "C10002", "Unknown exception: [%s]");

    private final int errorCode;

    private final String sqlState;

    private final String errorMessage;
}
