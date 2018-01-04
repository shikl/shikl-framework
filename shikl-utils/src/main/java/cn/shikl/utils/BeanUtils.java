package cn.shikl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * The type Bean utils.
 *
 * @author shikl.
 * @date 2016 /10/27.
 */
public class BeanUtils {

    private final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * Transfer map to bean.
     *
     * @param <T>   the type parameter
     * @param map   the map
     * @param clazz the clazz
     * @return the t
     * @throws Exception the exception
     */
    public static <T extends Object> T transferMap2Bean(Map<String, Object> map, Class<T> clazz) throws Exception {
        logger.debug("map:{},class:{}", map, clazz);
        T instance = clazz.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : descriptors) {
            String key = property.getName();
            if (map.containsKey(key)) {
                Object value = map.get(key);
                Method setter = property.getWriteMethod();
                setter.invoke(instance, value);
            }
        }
        return instance;
    }
}
