package io.binghe.concurrent.chapter18;

import java.util.concurrent.locks.Lock;

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
            System.out.println(Thread.currentThread().getName() + " 第一次获取到锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 第二次获取到锁");
            count++;
        }finally {
            System.out.println(Thread.currentThread().getName() + " 第一次释放锁");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 第二次释放锁");
            lock.unlock();
        }
    }

    public long getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantAQSLockTest reentrantAQSLockTest = new ReentrantAQSLockTest();

        for(int i = 0; i < 10; i++){
             Thread thread = new Thread(() -> {
                 reentrantAQSLockTest.incrementCount();
             });
             thread.start();
             thread.join();
         }

        System.out.println("count的最终结果为: " + reentrantAQSLockTest.getCount());
    }
}
