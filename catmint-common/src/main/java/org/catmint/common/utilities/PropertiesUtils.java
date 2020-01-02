package org.catmint.common.utilities;

import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title:读取properties工具类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class PropertiesUtils {
    
    /**
    * <p>Title:</p>
    * <p>Description:</p>
    * @author QIQI
    * @params [propertiesName -- 传入properties文件名, key]
    * @return java.lang.String
    * @throws 
    * @date 2020-01-02 22:48 
    */
    public static String readValue(String propertiesName,String key) {
        Properties prop = new Properties();
        try (InputStream inputStream = Object.class.getResourceAsStream("/" + propertiesName)){
            if (inputStream != null) {
                prop.load(inputStream);
            }
            return prop.getProperty(key);
        } catch (Exception e) {
            return null;
        }
    }
}
