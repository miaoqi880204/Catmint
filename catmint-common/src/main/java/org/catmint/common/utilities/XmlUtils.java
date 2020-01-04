package org.catmint.common.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Title:xml解析工具类</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class XmlUtils {

    /**
    * <p>Title:xml解析根据字符串</p>
    * <p>Description:</p>
    * @author QIQI
    * @params [xml, clasz]
    * @return T
    * @throws 
    * @date 2020-01-04 19:48 
    */
    public static <T> T xmlParsingByString(String xml, Class clasz){
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            return (T) xmlMapper.readValue( xml, clasz );
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * <p>Title:xml解析根据文件</p>
     * <p>Description:</p>
     * @author QIQI
     * @params [xml, clasz]
     * @return T
     * @throws
     * @date 2020-01-04 19:48
     */
    public static <T> T xmlParsingByPath(String path, Class clasz){
        ObjectMapper xmlMapper = new XmlMapper();
        try (InputStream inputStream = Object.class.getResourceAsStream("/" + path)){
            if (inputStream != null) {
                return (T) xmlMapper.readValue( inputStream,clasz );
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
