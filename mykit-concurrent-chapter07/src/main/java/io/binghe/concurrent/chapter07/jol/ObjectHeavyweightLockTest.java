package io.binghe.concurrent.chapter07.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试重量级锁
 */
public class ObjectHeavyweightLockTest {
    public static void main(String[] args){
        //创建测试类对象
        MyObject obj = new MyObject();
        //打印对象信息，此时对象处于无锁状态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj){
            //当前锁状态为轻量级锁
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            //计算处于轻量级锁状态的对象的HashCode值，轻量级锁会膨胀为重量级锁
            obj.hashCode();
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
