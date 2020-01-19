package com.xyd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2020/01/02/18:30
 * @Description:
 */
@Configuration
@PropertySource(value = "classpath:redis.properties",encoding = "utf-8")
@EnableCaching //开启
public class SpringCacheConfig {
    @Value("${redis.host}")
    private String host;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.minIdle}")
    private int minIdle;

    @Bean
    public RedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        //配置池对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        jedisConnectionFactory.setPoolConfig(poolConfig);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,Object> getRedistTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        /*
         * redis的序列化器：RedisSerializer是一个父接口，具体的多个子类型分别实现了不同的序列化方式
         * 1.key的序列化器使用StringRedisSerializer
         * 2.value的序列化器使用GenericJackson2JsonRedisSerializer
         *
         * */
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        //设置hash的序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        return redisTemplate;
    }

    @Bean
    public CacheManager getCacheManager(RedisTemplate<String,Object> redisTemplate){
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        HashSet<String> cacheNames = new HashSet<>();
        /*cacheNames.add("sysOfficeCache");
        cacheNames.add("statuteCache");
        cacheNames.add("appVersionCache");
        cacheNames.add("resourceCache");*/
        redisCacheManager.setCacheNames(cacheNames);
        return redisCacheManager;
    }
}