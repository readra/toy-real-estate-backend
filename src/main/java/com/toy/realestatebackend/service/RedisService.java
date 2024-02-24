package com.toy.realestatebackend.service;

import com.toy.realestatebackend.repository.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void setValues() {
        redisDao.setValues("test", "test");
        log.info("RedisService setValues.");
    }
}
