package io.binghe.concurrent.chapter09;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 以CAS实现线程安全的count++
 */
public class CasCountIncrement {

    //获取Unsafe对象
    private static final Unsafe unsafe = getUnsafe();
    //线程的数量
    private static final int THREAD_COUNT = 20;
    //每个线程运行的次数
    private static final int EXECUTE_COUNT_EVERY_THREAD = 500;
    //自增的count值
    private volatile int count = 0;
    //count的偏移量
    private static long countOffset;

    static {
        try {
            countOffset = unsafe.objectFieldOffset
                    (CasCountIncrement.class.getDeclaredField("count"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            unsafe = (Unsafe) singleoneInstanceField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

    /**
     * 以CAS的方式对count值进行自增操作
     */
    public void incrementCountByCas(){
        //将count的值赋值给oldCount
        int oldCount = 0;
        do {
            oldCount = count;
        }while (!unsafe.compareAndSwapInt(this, countOffset, oldCount, oldCount + 1));
    }

    public static void main(String[] args) throws InterruptedException {
        CasCountIncrement casCountIncrement = new CasCountIncrement();
        //为了模拟并发使用了CountDownLatch
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        //20个线程
        IntStream.range(0, THREAD_COUNT).forEach((i) -> {
            new Thread(()->{
                //每个线程执行500次count++
                IntStream.range(0, EXECUTE_COUNT_EVERY_THREAD).forEach((j) -> {
                    casCountIncrement.incrementCountByCas();
                });
                latch.countDown();
            }).start();
        });
        latch.await();
        System.out.println("count的最终结果为: " + casCountIncrement.count);
    }
}
