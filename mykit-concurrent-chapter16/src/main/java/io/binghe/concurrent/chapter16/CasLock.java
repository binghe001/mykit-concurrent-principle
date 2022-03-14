package io.binghe.concurrent.chapter16;

/**
 * @author binghe
 * @version 1.0.0
 * @description 自旋锁接口
 */
public interface CasLock {

    /**
     * 加锁
     */
    void lock();

    /**
     * 释放锁
     */
    void unlock();
}
