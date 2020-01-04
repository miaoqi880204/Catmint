package org.catmint.beanfactory;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title:Bean单例工厂</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
@Slf4j
public class BeanFactory {
    public static final ConcurrentHashMap<String, Object> BeanInstantiation = new ConcurrentHashMap();

    /**
    * <p>Title:所有Bean单例工厂</p>
    * <p>Description:</p>
    * @author QIQI
    * @params [obj]
    * @return T
    * @throws
    * @date 2019-12-27 18:06
    */
    public static <T> T getBeanSingleton(Class<? extends Object> obj) {
        if (null == BeanInstantiation.get( obj.getName() )) {
            try {
                BeanInstantiation.put( obj.getName(), Class.forName( obj.getName() ).newInstance() );
            } catch (Exception e) {
                log.error( "BeanFactory.getBeanSingleton error ", e );
            }
        }
        return (T) BeanInstantiation.get( obj.getName() );
    }
}
