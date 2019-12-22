package org.catmint.config;

import org.catmint.config.model.DBConnectDTO;

/**
 * <p>Title:服务注册接口</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public interface ServiceRegistry {
    /**
    * <p>Title:服务注册接口</p>
    * <p>Description:</p>
    * @author QIQI
    * @params [serviceAddress - 服务地址, sreviceName - 服务名]
    * @return void
    * @throws 
    * @date 2019-12-21 23:52 
    */
    void register(DBConnectDTO dbConnectDTO);
}
