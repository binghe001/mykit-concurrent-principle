package io.binghe.concurrent.chapter15;

import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试自定义的线程池
 */
public class ThreadPoolTest {

    public static void main(String[] args){
        ThreadPool threadPool = new ThreadPool(5);
        IntStream.range(0, 10).forEach((i) -> {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "--->> Hello ThreadPool");
            });
        });
        threadPool.shutdown();
    }
}
