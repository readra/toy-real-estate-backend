package com.toy.realestatebackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.realestatebackend.repository.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Redis Service Layer
 *
 * @author 김진용
 */
@Slf4j
@Service
public class RedisService {
    private final RedisDao redisDao;

    @Autowired
    public RedisService(final RedisDao redisDao) {
        this.redisDao = redisDao;
    }

    /**
     * Redis Value 취득
     *
     * @param key
     *      Redis Key
     * @param clazz
     *      Value Class Type
     * @return
     *      Redis Value
     * @param <T>
     *      Raw Type
     * @throws Exception
     *      Exception
     */
    public <T> List<T> getValues(String key, Class<T> clazz) throws Exception {
        String value = (String) redisDao.getValues(key);
        if ( null == value || true == value.isBlank() ) {
            return null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(value, new TypeReference<>() {});
        }
    }

    /**
     * Redis Value 저장
     *
     * @param key
     *      Redis Key
     * @param values
     *      Redis Values
     * @throws Exception
     *      Exception
     */
    public void setValues(String key, List<?> values) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(values);

        redisDao.setValues(key, value);
    }
}
