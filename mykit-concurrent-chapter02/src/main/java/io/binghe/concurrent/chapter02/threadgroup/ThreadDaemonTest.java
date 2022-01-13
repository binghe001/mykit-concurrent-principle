package io.binghe.concurrent.chapter02.threadgroup;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试守护线程
 */
public class ThreadDaemonTest {
    public static void main(String[] args){
        //创建threadDaemon线程实例
        Thread threadDaemon = new Thread(()->{
            System.out.println("我是守护线程");
        }, "threadDaemon");

        //将线程设置为守护线程
        threadDaemon.setDaemon(true);
        //启动线程
        threadDaemon.start();
    }
}
