package io.binghe.concurrent.chapter02.threadgroup;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试线程组的使用
 */
public class ThreadGroupTest {

    public static void main(String[] args){
        //创建线程组threadGroup
        ThreadGroup threadGroup = new ThreadGroup("threadGroupTest");

        //创建thread1对象实例，并在构造方法中传入线程组和线程名称
        Thread thread1 = new Thread(threadGroup, ()->{
            String groupName = Thread.currentThread().getThreadGroup().getName();
            String threadName = Thread.currentThread().getName();
            System.out.println(groupName + "-" + threadName);
        }, "thread1");

        //创建thread2对象实例，并在构造方法中传入线程组和线程名称
        Thread thread2 = new Thread(threadGroup, ()->{
            String groupName = Thread.currentThread().getThreadGroup().getName();
            String threadName = Thread.currentThread().getName();
            System.out.println(groupName + "-" + threadName);
        }, "thread2");

        //启动thread1
        thread1.start();
        //启动thread2
        thread2.start();
    }
}
