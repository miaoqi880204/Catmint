package org.catmint.config.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:DB连接传输model</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Data
public class DBConnectDTO implements Serializable {
    private static final long serialVersionUID = 1463272221164238298L;
    //需要链接的数据库用户名
    private String userName;
    //需要链接的数据库密码
    private String password;
    //是否ssl协议
    private String isSsl;
    //需要连接的数据库端口
    private int port;
    //需要连接的数据库ip
    private String ip;
    //是否使用数据库压缩协议
    private boolean isCompress;
    //ssl密钥地址
    private String sslSecretKeyPath;
    //ssl证书地址
    private String sslCertificatePath;
    //ssl-ca证书地址
    private String sslCaCertificatePath;
    //客户端ip
    private String clientIp;
    //客户端端口
    private String clientPort;
}
