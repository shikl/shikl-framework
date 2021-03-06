package cn.shikl.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 缓存配置
 *
 * @author shikl
 * @date 2016-3-1
 */
public class CacheResolver implements org.springframework.cache.interceptor.CacheResolver {

    @Autowired
    private CacheManager cacheManager;

    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        List<Cache> caches = new ArrayList<Cache>();
        for (String cacheName : context.getOperation().getCacheNames()) {
            caches.add(cacheManager.getCache(cacheName));
        }
        return caches;
    }
}
