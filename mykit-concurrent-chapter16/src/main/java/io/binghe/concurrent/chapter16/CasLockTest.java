package io.binghe.concurrent.chapter16;

import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试自旋锁
 */
public class CasLockTest {
    /**
     * 创建CasLock对象
     */
    private CasLock lock = new MyCasLock();

    /**
     * 成员变量count
     */
    private long count = 0;

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

    /**
     * 获取count的值
     */
    public long getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        //创建CasLockTest对象
        CasLockTest casLockTest = new CasLockTest();

        Thread threadA = new Thread(() -> {
            IntStream.range(0, 100).forEach((i) -> {
                casLockTest.incrementCount();
            });
        });

        Thread threadB = new Thread(() -> {
            IntStream.range(0, 100).forEach((i) -> {
                casLockTest.incrementCount();
            });
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("count的最终结果为: " + casLockTest.getCount());
    }
}
