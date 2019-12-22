package org.catmint.io.protocol.mysql;

/**
 * MySQL协议 成功反馈包
 *
 * @author Shuo Xiang
 */
public class OKPacket extends MySQLPacket {

    /** 成功反馈通用报文 */
    public static final byte[] OK = new byte[]{7, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0};
    /** 鉴权成功，反馈报文 */
    public static final byte[] AUTH_OK = new byte[]{7, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0};

    @Override
    public int calcPacketSize() {
        return 0;
    }
}
