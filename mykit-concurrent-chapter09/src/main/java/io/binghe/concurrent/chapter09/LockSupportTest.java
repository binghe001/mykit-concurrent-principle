package io.binghe.concurrent.chapter09;

import java.util.concurrent.locks.LockSupport;

/**
 * @author binghe
 * @version 1.0.0
 * @description LockSupport案例
 */
public class LockSupportTest {

    /**
     * 阻塞线程
     */
    public void parkThread(){
        System.out.println(Thread.currentThread().getName() + " 开始阻塞");
        LockSupport.park();
        System.out.println(Thread.currentThread().getName() + " 结束阻塞");
    }

    public static void main(String[] args) throws InterruptedException {
        LockSupportTest lockSupport = new LockSupportTest();
        Thread thread = new Thread(() -> {
            lockSupport.parkThread();
        });
        thread.start();
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName() +  " 开始唤醒 " + thread.getName() + " 线程");
        LockSupport.unpark(thread);
        System.out.println(Thread.currentThread().getName() +  " 结束唤醒 " + thread.getName() + " 线程");
    }
}
