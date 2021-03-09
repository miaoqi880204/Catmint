package org.catmint.context.database;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.catmint.core.common.utils.CatmintConfigUtils;

/**
* <p>Title:配置信息启动初始化</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/9 下午2:12 
*/
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DatabaseProtocolTypeFactory {

    @SneakyThrows
    public static void initializeDatabaseConfig() {
        CatmintConfigUtils.getBaseConf();
        CatmintConfigUtils.newInstance();
    }
}
