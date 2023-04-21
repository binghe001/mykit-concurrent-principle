package io.binghe.concurrent.chapter09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 悲观锁实战
 */
public class PessimismLockTest {

    private Lock lock = new ReentrantLock();

    /**
     * 加锁并释放锁
     */
    public void lockAndUnlock(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 抢占锁成功");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        PessimismLockTest pessimismLock = new PessimismLockTest();
        IntStream.range(0, 5).forEach((i) -> {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 开始抢占锁");
                pessimismLock.lockAndUnlock();
            }).start();
        });
    }
}