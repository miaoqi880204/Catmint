package org.catmint.config.model;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * <p>Title:DB连接传输model</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Data
public class DBConnect implements Serializable {
    private static final long serialVersionUID = 1463272221164238298L;
    //需要链接的数据库用户名
    @NonNull
    private String userName;
    //需要链接的数据库密码
    @NonNull
    private String password;
    //是否ssl协议
    @NonNull
    private Boolean isSsl;
    //需要连接的数据库端口
    @NonNull
    private Integer port;
    //需要连接的数据库ip
    @NonNull
    private String ip;
    //是否使用数据库压缩协议
    @NonNull
    private Boolean isCompress;
    //ssl密钥地址
    private String sslSecretKeyPath;
    //ssl证书地址
    private String sslCertificatePath;
    //ssl-ca证书地址
    private String sslCaCertificatePath;
    //客户端ip
    @NonNull
    private String clientIp;
    //服务端本机ip
    @NonNull
    private String serverIp;
    //服务端本机端口，默认8799
    @NonNull
    private Integer serverPort;
}
