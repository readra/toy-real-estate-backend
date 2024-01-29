package com.toy.realestatebackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 설정
 * 
 * @author 김진용
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {
    // Redis 호스트
    @Value("${spring.redis.host:127.0.0.1}")
    private String host;

    // Redis 포트
    @Value("${spring.redis.port:6379}")
    private int port;

    /**
     * RedisConnectionFactory 객체
     *
     * @return
     *      RedisConnectionFactory 객체
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    /**
     * RedisTemplate 객체
     *
     * @return
     *      RedisTemplate 객체
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }
}
