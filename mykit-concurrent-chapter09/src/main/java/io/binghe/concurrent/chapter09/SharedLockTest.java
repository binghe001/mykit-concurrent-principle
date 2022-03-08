package io.binghe.concurrent.chapter09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 共享锁案例
 */
public class SharedLockTest {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock lock = readWriteLock.readLock();

    /**
     * 加锁并释放锁
     */
    public void lockAndUnlock(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 抢占锁成功");
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " 被中断");
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放锁成功");
        }
    }

    public static void main(String[] args){
        SharedLockTest sharedLockTest = new SharedLockTest();
        IntStream.range(0, 5).forEach((i) -> {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 开始抢占锁");
                sharedLockTest.lockAndUnlock();
            }).start();
        });
    }
}
