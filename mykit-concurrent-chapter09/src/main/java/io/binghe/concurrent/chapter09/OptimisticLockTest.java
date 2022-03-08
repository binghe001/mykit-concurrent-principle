package io.binghe.concurrent.chapter09;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 乐观锁实战
 */
public class OptimisticLockTest {

    private AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementCount(){
        atomicInteger.incrementAndGet();
    }
    public int getCount(){
       return atomicInteger.get();
    }

    public static void main(String[] args) throws InterruptedException {
        OptimisticLockTest optimisticLock = new OptimisticLockTest();
        IntStream.range(0, 10).forEach((i) -> {
            new Thread(()->{
                optimisticLock.incrementCount();
            }).start();
        });
        Thread.sleep(500);
        int count = optimisticLock.getCount();
        System.out.println("最终的结果数据为: " + count);
    }
}
