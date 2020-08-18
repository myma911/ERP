package com.haoyongsys.erp.common.util.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig{

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);

        // 对key的默认序列化器。默认值是StringSerializer
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);

        // 对value的默认序列化器，默认值是取自DefaultSerializer的JdkSerializationRedisSerializer
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);

        // 存储Map时key需要的序列化配置
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // 存储Map时value需要的序列化配置
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);

        //redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }
}