package io.binghe.concurrent.chapter09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author binghe
 * @version 1.0.0
 * @description 可中断锁案例
 */
public class InterruptiblyLockTest {

    private Lock lock = new ReentrantLock();

    /**
     * 加锁并释放锁
     */
    public void lockAndUnlock(){
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " 抢占锁成功");
            if (Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().getName() + " 被中断");
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 抢占锁被中断");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptiblyLockTest interruptiblyLock = new InterruptiblyLockTest();
        Thread threadA = new Thread(() -> {
            interruptiblyLock.lockAndUnlock();
        }, "threadA");
        Thread threadB = new Thread(() -> {
            interruptiblyLock.lockAndUnlock();
        }, "threadB");

        threadA.start();
        threadB.start();

        Thread.sleep(100);

        threadA.interrupt();
        threadB.interrupt();

        Thread.sleep(2000);
    }
}
