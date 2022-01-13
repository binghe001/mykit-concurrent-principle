package io.binghe.concurrent.chapter02.threadgroup;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试用户线程
 */
public class ThreadUserTest {

    public static void main(String[] args){
        //创建threadUser线程实例
        Thread threadUser = new Thread(()->{
            System.out.println("我是用户线程");
        }, "threadUser");

        //启动线程
        threadUser.start();
    }
}
