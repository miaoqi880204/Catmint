package org.catmint.context.database.metadata;

/**
 * <p>Title:数据源源数据</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @params
 * @return
 * @throws
 * @date 2021/3/3 下午10:42
 */
public interface DataSourceMetaData {

    /**
     * Get host name.
     *
     * @return host name
     */
    String getHostName();

    /**
     * Get port.
     *
     * @return port
     */
    int getPort();

    /**
     * Get catalog.
     *
     * @return catalog
     */
    String getCatalog();

    /**
     * Get schema.
     *
     * @return schema
     */
    String getSchema();
}
