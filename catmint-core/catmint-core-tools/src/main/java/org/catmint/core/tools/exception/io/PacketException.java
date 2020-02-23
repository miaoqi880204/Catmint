package org.catmint.core.tools.exception.io;

/**
 * 报文异常
 *
 * @author Shuo Xiang
 */
public class PacketException extends RuntimeException {

    public PacketException() {
    }

    public PacketException(String message) {
        super(message);
    }

    public PacketException(String message, Throwable cause) {
        super(message, cause);
    }
}
