package io.binghe.concurrent.chapter07.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author binghe
 * @version 1.0.0
 * @description 分析对象锁
 */
public class ObjectLockAnalysis {

    public static void main(String[] args) throws InterruptedException {
        //printNormalLock();
        printBiasLock();
    }

    /**
     * 打印锁信息
     */
    private static void printNormalLock() throws InterruptedException {
        //创建测试类对象
        MyObject obj = new MyObject();
        //打印对象信息，此时对象处于无锁状态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj){
            //打印对象信息，此时对象处于轻量级锁状态
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            //计算对象的Hashcode值
            System.out.println(obj.hashCode());
            //计算处于轻量级状态的对象的HashCode值，轻量级锁会膨胀为重量级锁
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        synchronized (obj){
            //打印对象信息，此时对象处于重量级锁状态
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        //打印对象信息，此时对象处于重量级锁状态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    /**
     * 打印偏向锁信息
     */
    private static void printBiasLock() throws InterruptedException {
        //Java中的偏向锁在JVM启动几秒之后才会被激活
        //所以程序启动时先休眠5秒钟，等待激活偏向锁
        //否则会出现一些没必要的锁撤销
        Thread.sleep(5000);
        //创建测试类对象
        MyObject obj = new MyObject();
        //打印对象信息，此时对象处于偏向锁状态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj){
            //打印对象信息，此时对象处于偏向锁状态
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            //计算对象的Hashcode值
            System.out.println(obj.hashCode());
            //计算处于偏向锁状态的对象的HashCode值，偏向锁会膨胀为重量级锁
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        synchronized (obj){
            //打印对象信息，此时对象处于重量级锁状态
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        //打印对象信息，此时对象处于重量级锁状态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
