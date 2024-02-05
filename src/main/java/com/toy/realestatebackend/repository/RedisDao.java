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

    private final RedisTemplate<String, String> redisTemplate;

    public RedisDao(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * ValueOperations 통한 데이터 입력
     *
     * @param key
     *      데이터 key
     * @param value
     *      데이터 value
     */
    public void setValues(String key, String value) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, value);
    }

    /**
     * ValueOperations 통한 데이터 입력
     *
     * @param key
     *      데이터 key
     * @param value
     *      데이터 value
     * @param duration
     *      데이터 유효기간
     */
    public void setValues(String key, String value, Duration duration) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, value, duration);
    }

    /**
     * ValueOperations 통한 데이터 조회
     *
     * @param key
     *      데이터 key
     * @return
     *      데이터 value
     */
    public String getValues(String key) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    /**
     * ListOperations 통한 데이터 입력
     *
     * @param key
     *      데이터 key
     * @param value
     *      데이터 value
     */
    public void setValuesList(String key, String value) {
        redisTemplate.opsForList().rightPushAll(key,value);
    }

    /**
     * ListOperations 통한 데이터 조회
     *
     * @param key
     *      데이터 key
     * @return
     *      데이터 value 목록
     */
    public List<String> getValuesList(String key) {
        Long size = redisTemplate.opsForList().size(key);
        return ( null == size || 0 == size ) ? new LinkedList<>() : redisTemplate.opsForList().range(key, 0, size - 1);
    }

    /**
     * 데이터 key 기준 데이터 value 삭제
     *
     * @param key
     *      데이터 key
     */
    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }
}
