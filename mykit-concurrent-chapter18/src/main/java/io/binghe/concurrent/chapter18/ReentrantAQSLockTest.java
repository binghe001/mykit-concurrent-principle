package io.binghe.concurrent.chapter18;

import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试可重入锁
 */
public class ReentrantAQSLockTest {

    /**
     * 成员变量count
     */
    private int count;

    /**
     * 可重入锁
     */
    private Lock lock = new ReentrantAQSLock();

    /**
     * 自增count的方法
     */
    public void incrementCount(){
        try{
            lock.lock();
            count++;
        }finally {
            lock.unlock();
        }
    }

    public long getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantAQSLockTest reentrantAQSLockTest = new ReentrantAQSLockTest();

        Thread threadA = new Thread(() -> {
            IntStream.range(0, 500).forEach((i) -> {
                reentrantAQSLockTest.incrementCount();
            });
        });

        Thread threadB = new Thread(() -> {
            IntStream.range(0, 500).forEach((i) -> {
                reentrantAQSLockTest.incrementCount();
            });
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("count的最终结果为: " + reentrantAQSLockTest.getCount());
    }
}
