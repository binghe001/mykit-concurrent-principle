package io.binghe.concurrent.chapter19.service;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description Redis分布式锁接口
 */
public interface RedisDistributeLock {
    /**
     * 加锁
     */
    boolean tryLock(String key, long timeout, TimeUnit unit);

    /**
     * 解锁
     */
    void releaseLock(String key);
}
