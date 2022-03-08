package io.binghe.concurrent.chapter09;

import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description StampedLock案例
 */
public class StampedLockTest {

    private final StampedLock lock = new StampedLock();

    /**
     * 写锁案例
     */
    public void writeLockAndUnlock(){
        //加锁时返回一个long类型的票据
        long stamp = lock.writeLock();
        try{
            System.out.println(Thread.currentThread().getName() + " 抢占写锁成功");
        }finally {
            //释放锁的时候带上加锁时返回的票据
            lock.unlock(stamp);
            System.out.println(Thread.currentThread().getName() + " 释放写锁成功");
        }
    }
    /**
     * 读锁案例
     */
    public void readLockAndUnlock(){
        //加锁时返回一个long类型的票据
        long stamp = lock.readLock();
        try{
            System.out.println(Thread.currentThread().getName() + " 抢占读锁成功");
        }finally {
            //释放锁的时候带上加锁时返回的票据
            lock.unlock(stamp);
            System.out.println(Thread.currentThread().getName() + " 释放读锁成功");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockTest stampedLockTest = new StampedLockTest();
        //写锁
        IntStream.range(0, 5).forEach((i) -> {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 开始抢占写锁");
                stampedLockTest.writeLockAndUnlock();
            }).start();
        });

        Thread.sleep(1000);
        System.out.println("===============================");

        //读锁
        IntStream.range(0, 5).forEach((i) -> {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 开始抢占读锁");
                stampedLockTest.readLockAndUnlock();
            }).start();
        });
    }
}
