package io.binghe.concurrent.chapter09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试非公平锁
 */
public class NonfairLockTest {

    /**
     * 创建非公平锁实例
     */
    private Lock lock = new ReentrantLock(false);

    /**
     * 非公平锁模式下的加锁与释放锁
     */
    public void fairLockAndUnlock(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "抢占锁成功");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        FairLockTest fairLockTest = new FairLockTest();
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++){
            threads[i] = new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "开始抢占锁");
                fairLockTest.fairLockAndUnlock();
            });
        }
        for (int i = 0; i < 4; i ++){
            threads[i].start();
        }
    }
}
