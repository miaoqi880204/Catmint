package org.catmint.config.spi.zk;

import lombok.Data;
import org.catmint.config.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Title:Zk配置提示信息</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Data
@Component
@ConfigurationProperties(Constant.ZK_PROPERTIES)
public class ZkConfigProperties {
    private Zk zk;

    @Data
    class Zk {
        private String address;
    }
}
