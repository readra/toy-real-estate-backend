package com.toy.realestatebackend.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * Redis Dao Layer
 * 
 * @author 김진용
 */
@Repository
public class RedisDao {
    /**
     * 주석 추가
     */
    private final RedisTemplate<String, String> redisTemplate;

    public RedisDao(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValues(String key, String data) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    public void setValuesList(String key, String data) {
        redisTemplate.opsForList().rightPushAll(key,data);
    }

    public List<String> getValuesList(String key) {
        Long len = redisTemplate.opsForList().size(key);
        return len == 0 ? new LinkedList<>() : redisTemplate.opsForList().range(key, 0, len-1);
    }

    public void setValues(String key, String data, Duration duration) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data, duration);
    }

    public String getValues(String key) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }
}
