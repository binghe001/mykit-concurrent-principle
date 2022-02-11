package io.binghe.concurrent.chapter07.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试轻量级锁
 */
public class ObjectLightweightLockTest {
    public static void main(String[] args){
        //创建测试类对象
        MyObject obj = new MyObject();
        //打印对象信息，此时对象处于无锁状态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj){
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
