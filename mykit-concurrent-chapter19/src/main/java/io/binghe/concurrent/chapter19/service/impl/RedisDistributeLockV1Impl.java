package io.binghe.concurrent.chapter19.service.impl;

import io.binghe.concurrent.chapter19.service.RedisDistributeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description 实现Redis分布式锁 19.6.6  误删锁分析
 */
@Service("redisDistributeLockV1")
public class RedisDistributeLockV1Impl implements RedisDistributeLock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, this.getCurrentThreadId(), timeout, unit);
    }

    @Override
    public void releaseLock(String key) {
        stringRedisTemplate.delete(key);
    }

    private String getCurrentThreadId(){
        return String.valueOf(Thread.currentThread().getId());
    }
}
