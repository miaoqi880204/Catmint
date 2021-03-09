package org.catmint.core.common.spi;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.catmint.core.tools.exception.CatmintException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <p>Title:CatmintServiceLoader </p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @params
 * @return
 * @throws
 * @date 2021/3/8 下午5:15
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CatmintServiceLoader {

    private static final Map<Class<?>, Collection<Class<?>>> SERVICES = new ConcurrentHashMap<>();

    /**
     * Register service.
     *
     * @param service service class
     * @param <T>     type of service
     */
    public static <T> void register(final Class<T> service) {
        if (SERVICES.containsKey( service )) {
            return;
        }
        for (T each : ServiceLoader.load( service )) {
            registerServiceClass( service, each );
        }
    }

    private static <T> void registerServiceClass(final Class<T> service, final T instance) {
        Collection<Class<?>> serviceClasses = SERVICES.computeIfAbsent( service, unused -> new LinkedHashSet<>() );
        serviceClasses.add( instance.getClass() );
    }

    /**
     * New service instances.
     *
     * @param service service class
     * @param <T>     type of service
     * @return service instances
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> newServiceInstances(final Class<T> service) {
        return SERVICES.containsKey( service ) ? SERVICES.get( service ).stream().map( each -> (T) newServiceInstance( each ) ).collect( Collectors.toList() ) : Collections.emptyList();
    }

    private static Object newServiceInstance(final Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (final InstantiationException | IllegalAccessException ex) {
            throw new CatmintException( clazz, ex );
        }
    }
}
