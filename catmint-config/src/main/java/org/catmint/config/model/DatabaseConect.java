package org.catmint.config.model;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * <p>Title:数据库链接信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class DatabaseConect implements Serializable {
    private static final long serialVersionUID = -7924815868226675101L;
    //数据库名字
    @Getter
    private String dbname;
    //数据库ip节点信息
    @Getter
    private Ip ip;
    //数据库用户名
    @Getter
    private String user;
    //数据库密码
    @Getter
    private String password;
    /**
    * <p>Title:数据库方言</p>
    * <p>Description:
     * <xs:restriction base="xs:string">
     *      <xs:enumeration value="mysql"/>
     *      <xs:enumeration value="postgresql"/>
     *      <xs:enumeration value="mariadb"/>
     *      <xs:enumeration value="oracle"/>
     *      <xs:enumeration value="sqlserver"/>
     *      <xs:enumeration value="db2"/>
     * </xs:restriction>
     *
     * </p>
    * @author QIQI
    * @params
    * @return
    * @throws
    * @date 2020-01-04 17:31
    */
    @Getter
    private String dialect;

    public DatabaseConect(@NonNull String dbname,@NonNull Ip ip,@NonNull String user,
                          @NonNull String password,@NonNull String dialect) {
        this.dbname = dbname;
        this.ip = ip;
        this.user = user;
        this.password = password;
        this.dialect = dialect;
    }

    /**
    * <p>Title:数据库IP节点信息</p>
    * <p>Description:</p>
    * @author QIQI
    * @params
    * @return 
    * @throws 
    * @date 2020-01-04 18:19 
    */
    static class Ip{
        //主节点信息
        @Getter
        private String masterIp;
        //从节点信息
        @Getter
        private LinkedList<String> slaveIps;

        public Ip(@NonNull String masterIp, LinkedList<String> slaveIps) {
            this.masterIp = masterIp;
            this.slaveIps = slaveIps;
        }
    }
}
