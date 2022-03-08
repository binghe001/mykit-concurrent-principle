package io.binghe.concurrent.chapter09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 可重入锁案例
 */
public class ReentrantLockTest {

    private Lock lock = new ReentrantLock();

    /**
     * 加锁并释放锁
     */
    public void lockAndUnlock(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 第1次抢占锁成功");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 第2次抢占锁成功");
        }finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 第1次释放锁成功");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 第2次释放锁成功");
        }
    }


    public static void main(String[] args){
        ReentrantLockTest reentrantLock = new ReentrantLockTest();
        IntStream.range(0, 2).forEach((i) -> {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 开始抢占锁");
                reentrantLock.lockAndUnlock();
            }).start();
        });
    }
}
