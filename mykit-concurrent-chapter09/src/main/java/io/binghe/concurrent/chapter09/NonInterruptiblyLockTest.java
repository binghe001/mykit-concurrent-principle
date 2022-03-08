package io.binghe.concurrent.chapter09;

/**
 * @author binghe
 * @version 1.0.0
 * @description 不可中断锁案例
 */
public class NonInterruptiblyLockTest {

    public synchronized void lock(){
        try {
            System.out.println(Thread.currentThread().getName() + " 抢占锁成功");
            if (Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().getName() + " 被中断");
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 抢占锁被中断");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NonInterruptiblyLockTest nonInterruptiblyLock = new NonInterruptiblyLockTest();
        Thread threadA = new Thread(() -> {
            nonInterruptiblyLock.lock();
        }, "threadA");
        Thread threadB = new Thread(() -> {
            nonInterruptiblyLock.lock();
        }, "threadB");

        threadA.start();
        threadB.start();

        Thread.sleep(100);

        threadA.interrupt();
        threadB.interrupt();

        Thread.sleep(2000);
    }
}
