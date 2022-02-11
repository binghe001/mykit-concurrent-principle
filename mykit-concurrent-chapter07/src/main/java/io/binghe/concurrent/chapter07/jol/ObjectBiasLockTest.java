package io.binghe.concurrent.chapter07.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试偏向锁，
 * 运行时添加-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0参数
 */
public class ObjectBiasLockTest {

    public static void main(String[] args){
        //创建测试类对象
        MyObject obj = new MyObject();
        //打印对象信息，此时对象处于无锁状态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
