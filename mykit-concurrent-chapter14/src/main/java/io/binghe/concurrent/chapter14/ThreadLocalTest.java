package io.binghe.concurrent.chapter14;

/**
 * @author binghe
 * @version 1.0.0
 * @description ThreadLocal案例程序
 */
public class ThreadLocalTest {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();

    public static void main(String[] args){
        Thread threadA = new Thread(()->{
            THREAD_LOCAL.set("ThreadA: " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "本地变量中的值为: " + THREAD_LOCAL.get());
            System.out.println(Thread.currentThread().getName() + "未删除本地变量，本地变量中的值为: " + THREAD_LOCAL.get());
        }, "Thread-A");

        Thread threadB = new Thread(()->{
            THREAD_LOCAL.set("ThreadB: " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "本地变量中的值为: " + THREAD_LOCAL.get());
            THREAD_LOCAL.remove();
            System.out.println(Thread.currentThread().getName() + "删除本地变量后，本地变量中的值为: " + THREAD_LOCAL.get());
        }, "Thread-B");

        threadA.start();
        threadB.start();
    }
}