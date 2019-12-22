package org.catmint.config.spi.zk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Title:读取yml配置信息工具</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Configuration
public class ZkParameterInit {
    public static String ZK_ADDRESS;
    @Value( "${org.catmint.zk.address:}" )
    private String zk_address;

    @PostConstruct
    public void zkParameterInit(){
        ZK_ADDRESS = zk_address;
    }
}
