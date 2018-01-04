package cn.shikl.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.shikl.core.config.Configure;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Collection;

/**
 * redis config
 *
 * @author shikl
 */
@Configuration
@EnableCaching
@PropertySource(value = {"classpath:redis-config.properties"})
public class RedisConfiguration extends CachingConfigurerSupport {

    public static final String HOST_NAME = "redis.host";

    public static final String TOPOLOGY ="redis.topology";

    public static final String CLUSTER_TOPOLOGY ="cluster";
    public static final String DEFAULT_TOPOLOGY ="server";

    public static final String PORT = "redis.port";

    public static final String EXPIRATION_TIME = "redis.cache.expiration.time";

    public static final String DEFAULT_HOST_NAME = "localhost";

    public static final String DEFAULT_PORT = "6379";

    public static final String MAX_ACTIVE = "redis.pool.maxActive";
    public static final String MAX_IDLE = "redis.pool.maxIdle";
    public static final String MIN_IDLE = "redis.pool.minIdle";
    public static final String MAX_WAITMILLIS = "redis.pool.maxWaitMillis";
    public static final String MIN_EVICTABLE_TIME = "redis.pool.minEvictableIdleTimeMillis";
    /**
     * 默认过期时间.3000 seconds
     */
    public static int DEFAULT_EXPIRATION_TIME = 3000;

    @Autowired(required = false)
    private Configure configure;

    private String getProperty(String key, String defaultValue) {
        return configure.getProperty(key, defaultValue);
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = null;

        String topology = getProperty(TOPOLOGY, DEFAULT_TOPOLOGY);

        if ( topology.equals(DEFAULT_TOPOLOGY) ) {

            redisConnectionFactory = new JedisConnectionFactory();
            String hostName = getProperty(HOST_NAME, DEFAULT_HOST_NAME);
            int port = Integer.valueOf(getProperty(PORT, DEFAULT_PORT));
            redisConnectionFactory.setHostName(hostName);
            redisConnectionFactory.setPort(port);

        } else if (topology.equals(CLUSTER_TOPOLOGY)){

            Collection<String> clusters = new ArrayList<>();
            int i =1;
            while (true) {
                String cluster = configure.getProperty("cluster" + i + ".redis.host", "");
                if (StringUtils.isNotBlank(cluster)){
                    clusters.add(cluster);
                    i++;
                } else {
                    break;
                }
            }
            RedisClusterConfiguration configuration = new RedisClusterConfiguration(clusters);
            redisConnectionFactory = new JedisConnectionFactory(configuration);
        }
        redisConnectionFactory.setPoolConfig(jedisPoolConfig());
        return redisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        long _minEvictable = Long.valueOf(getProperty(MIN_EVICTABLE_TIME, "1800000"));
        long _maxWait = Long.valueOf(getProperty(MAX_WAITMILLIS, "-1"));
        int _maxTotal = Integer.valueOf(getProperty(MAX_ACTIVE, "8"));
        int _maxIdle = Integer.valueOf(getProperty(MAX_IDLE, "8"));
        int _minIdle = Integer.valueOf(getProperty(MIN_IDLE, "0"));
        poolConfig.setTestOnBorrow(true);
        poolConfig.setMinEvictableIdleTimeMillis(_minEvictable);
        poolConfig.setMaxWaitMillis(_maxWait);
        poolConfig.setMaxTotal(_maxTotal);
        poolConfig.setMaxIdle(_maxIdle);
        poolConfig.setMinIdle(_minIdle);
        return poolConfig;
    }

    //    <bean id="dataJedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
//    <property name="maxActive" value="300"/>
//    <property name="maxIdle" value="100"/>
//    <property name="maxWait" value="10000"/>
//    <property name="testOnBorrow" value="true"/>
//    </bean>

    //以下为加入cache

    @Bean
    public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        String _time = configure.getProperty(EXPIRATION_TIME);
        int expTime = DEFAULT_EXPIRATION_TIME;
        if (StringUtils.isNotEmpty(_time)) {
            expTime = Integer.valueOf(_time);
        }
        cacheManager.setDefaultExpiration(expTime); // Sets the default expire time (in seconds)
        cacheManager.afterPropertiesSet();
        return cacheManager;
    }

    public KeyGenerator keyGenerator() {
        KeyGenerator keyGenerator = new SimpleKeyGenerator();
        return keyGenerator;
    }
}
