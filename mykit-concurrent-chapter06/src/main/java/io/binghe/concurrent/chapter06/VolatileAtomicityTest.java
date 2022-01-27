package io.binghe.concurrent.chapter06;

import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试volatile不能保证原子性
 */
public class VolatileAtomicityTest {

    private volatile Long count = 0L;

    public void  incrementCount(){
        count++;
    }

    public Long execute() throws InterruptedException {
        Thread thread1 = new Thread(()->{
            IntStream.range(0, 1000).forEach((i) -> incrementCount());
        });

        Thread thread2 = new Thread(()->{
            IntStream.range(0, 1000).forEach((i) -> incrementCount());
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
        VolatileAtomicityTest multiThreadAtomicity = new VolatileAtomicityTest();
        Long count = multiThreadAtomicity.execute();
        System.out.println(count);
    }
}
