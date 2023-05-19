package io.binghe.concurrent.chapter02.threadgroup;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description 证明 volatile 的可见性
 */
public class UnsafeCount {

    //    public long value = 0L;
    public volatile long value = 0L;


    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public static void main(String[] args) {
        final UnsafeCount unsafeCount = new UnsafeCount();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            unsafeCount.setValue(100L);
            System.out.println(unsafeCount.getValue());
        }).start();


        // 此时已经将 变量复制到线程变量了,但是 其他线程修改了主内存的数据之后,没有通知此线程修改(主内存的修改,对与此线程不可变),所以一直是 0
        while (unsafeCount.getValue() == 0) {

        }


        System.out.println("结束");

    }
}


