package io.binghe.concurrent.chapter09;

import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description Synchronized实现的可重入锁案例
 */
public class ReentrantLockSyncTest {

    /**
     * 加锁并释放锁
     */
    public synchronized void lockAndUnlock2(){
        System.out.println(Thread.currentThread().getName() + " 第1次抢占锁成功");
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " 第2次抢占锁成功");
        }
        System.out.println(Thread.currentThread().getName() + " 第1次释放锁成功");
    }

    public static void main2(String[] args){
        ReentrantLockSyncTest reentrantLock = new ReentrantLockSyncTest();
        IntStream.range(0, 2).forEach((i) -> {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 开始抢占锁");
                reentrantLock.lockAndUnlock();
                System.out.println(Thread.currentThread().getName() + " 第2次释放锁成功");
            }).start();
        });
    }

    /**
     * 加锁并释放锁
     */
    public void lockAndUnlock(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " 第1次抢占锁成功");
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " 第2次抢占锁成功");
            }
            System.out.println(Thread.currentThread().getName() + " 第1次释放锁成功");
        }
        System.out.println(Thread.currentThread().getName() + " 第2次释放锁成功");
    }

    public static void main(String[] args){
        ReentrantLockSyncTest reentrantLock = new ReentrantLockSyncTest();
        IntStream.range(0, 2).forEach((i) -> {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 开始抢占锁");
                reentrantLock.lockAndUnlock();
            }).start();
        });
    }
}
