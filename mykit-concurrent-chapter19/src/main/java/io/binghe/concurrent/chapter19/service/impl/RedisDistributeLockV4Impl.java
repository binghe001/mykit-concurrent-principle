package io.binghe.concurrent.chapter19.service.impl;

import io.binghe.concurrent.chapter19.service.RedisDistributeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description 实现Redis分布式锁 19.6.9  解决可重入性问题
 */
@Service("redisDistributeLockV4")
public class RedisDistributeLockV4Impl implements RedisDistributeLock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    private ThreadLocal<Integer> threadLocalCount = new ThreadLocal<Integer>();
    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        Boolean isLocked = false;
        if (threadLocal.get() == null){
            String currentThreadId = this.getCurrentThreadId();
            threadLocal.set(currentThreadId);
            isLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, currentThreadId, timeout, unit);
        }else{
            isLocked = true;
        }
        //加锁成功后，计数器的值加1
        if (isLocked){
            Integer count = threadLocalCount.get() == null ? 0 : threadLocalCount.get();
            threadLocalCount.set(++count);
        }
        return isLocked;
    }

    @Override
    public void releaseLock(String key) {
        //当前线程中绑定的线程id与Redis中的线程id相同时，再执行删除锁的操作
        if (threadLocal.get().equals(stringRedisTemplate.opsForValue().get(key))){
            Integer count = threadLocalCount.get();
            if (count == null || --count <= 0){
                stringRedisTemplate.delete(key);
                //防止内存泄露
                threadLocal.remove();
                threadLocalCount.remove();
            }else {
                threadLocalCount.set(count);
            }

        }
    }

    private String getCurrentThreadId(){
        return String.valueOf(Thread.currentThread().getId());
    }
}
