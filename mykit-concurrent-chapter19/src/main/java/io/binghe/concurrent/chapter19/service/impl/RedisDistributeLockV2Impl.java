package io.binghe.concurrent.chapter19.service.impl;

import io.binghe.concurrent.chapter19.service.RedisDistributeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description 实现Redis分布式锁 19.6.7  实现加锁和解锁归一
 */
@Service("redisDistributeLockV2")
public class RedisDistributeLockV2Impl implements RedisDistributeLock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        String currentThreadId = this.getCurrentThreadId();
        threadLocal.set(currentThreadId);
        return stringRedisTemplate.opsForValue().setIfAbsent(key, currentThreadId, timeout, unit);
    }

    @Override
    public void releaseLock(String key) {
        //当前线程中绑定的线程id与Redis中的线程id相同时，再执行删除锁的操作
        if (threadLocal.get().equals(stringRedisTemplate.opsForValue().get(key))){
            stringRedisTemplate.delete(key);
            //防止内存泄露
            threadLocal.remove();
        }
    }

    private String getCurrentThreadId(){
        return String.valueOf(Thread.currentThread().getId());
    }
}
