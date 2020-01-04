package org.catmint.proxy.packet;

/**
 * SQL error code.
 *
 * @author Shuo Xiang
 */
public interface SQLErrorCode {

    /**
     * Get error code.
     *
     * @return error code
     */
    int getErrorCode();

    /**
     * Get SQL state.
     *
     * @return SQL state
     */
    String getSqlState();

    /**
     * Get error message.
     *
     * @return error message
     */
    String getErrorMessage();
}
