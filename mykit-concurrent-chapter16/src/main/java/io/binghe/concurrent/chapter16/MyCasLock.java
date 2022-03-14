package io.binghe.concurrent.chapter16;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author binghe
 * @version 1.0.0
 * @description 自旋锁实现类
 */
public class MyCasLock implements CasLock{
    /**
     * 创建AtomicReference类型的成员变量
     */
    private AtomicReference<Thread> threadOwner = new AtomicReference<Thread>();

    @Override
    public void lock() {
        //获取当前线程的对象currentThread
        Thread currentThread = Thread.currentThread();
        //自旋操作
        for(;;){
            //如果以CAS的方式将null修改为当前线程对象currentThread成功，则退出自旋
            if (threadOwner.compareAndSet(null, currentThread)){
                break;
            }
        }
    }

    @Override
    public void unlock() {
        //获取当前线程的对象currentThread
        Thread currentThread = Thread.currentThread();
        //通过CAS方式将当前线程的对象currentThread修改成null
        threadOwner.compareAndSet(currentThread, null);
    }
}
