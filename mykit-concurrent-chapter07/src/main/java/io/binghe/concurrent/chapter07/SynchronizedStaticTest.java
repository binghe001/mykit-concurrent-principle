package io.binghe.concurrent.chapter07;

import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description synchronized 修饰静态方法
 */
public class SynchronizedStaticTest {

    private static Long count = 2000L;

    public static synchronized void decrementCount(){
        count--;
    }
    public static Long execute() throws InterruptedException {
        Thread thread1 = new Thread(()->{
            IntStream.range(0, 1000).forEach((i) -> decrementCount());
        });

        Thread thread2 = new Thread(()->{
            IntStream.range(0, 1000).forEach((i) -> decrementCount());
        });

        //启动线程1和线程2
        thread1.start();
        thread2.start();

        //等待线程1和线程2执行完毕
        thread1.join();
        thread2.join();

        //返回count的值
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Long count = execute();
        System.out.println(count);
    }
}
