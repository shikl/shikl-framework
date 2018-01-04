package cn.shikl.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.jcache.JCacheCacheManager;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

/**
 * 缓存配置管理文件.
 *
 * @author shikl
 * @date 2016-10-17
 */
public class CacheConfig {
    public CacheManager cacheManager() {
        javax.cache.CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Object, Object> mutableConfiguration = new MutableConfiguration<Object, Object>();
        mutableConfiguration.setStoreByValue(false);
        cacheManager.createCache("user_token", mutableConfiguration);

        JCacheCacheManager jCacheCacheManager = new JCacheCacheManager(cacheManager);
        return jCacheCacheManager;
    }

    //    @Bean
//    public CacheResolver cacheResolver() {
//        return new CacheResolver();
//    }

    //    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {

            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                System.out.println("cache get error");
            }

            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                System.out.println("cache put error");
            }

            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                System.out.println("cache evict error");
            }

            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                System.out.println("cache clear error");
            }
        };
    }
}
