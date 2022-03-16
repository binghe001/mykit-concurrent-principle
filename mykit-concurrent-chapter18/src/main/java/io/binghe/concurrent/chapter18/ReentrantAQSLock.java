package io.binghe.concurrent.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author binghe
 * @version 1.0.0
 * @description 基于AQS实现可重入锁
 */
public class ReentrantAQSLock implements Lock {

    private AQSSync sync = new AQSSync();

    private class AQSSync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int acquires) {
            //获取状态值
            int state = getState();
            //获取当前线程对象
            Thread currentThread = Thread.currentThread();
            //如果获取的状态值为0，则说明进来的是第一个线程
            if (state == 0){
                //将状态修改为传递的arg值
                if (compareAndSetState(0, acquires)){
                    //设置当前线程获取到锁，并且是独占锁
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
            }else if (getExclusiveOwnerThread() == currentThread){
                // 当前线程已经获取到锁，这里是实现可重入锁的关键代码
                //首先对状态增加计数
                int nextc = state + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                //设置状态值
                setState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            //对状态减去相应的计数
            int status = getState() - releases;
            //标识是否完全释放锁成功，true:是；false:否
            boolean flag = false;
            //如果状态减为0，说明没有线程持有锁了
            if (status == 0) {
                //标识为完全释放锁成功
                flag = true;
                //设置获取到锁的线程为null
                setExclusiveOwnerThread(null);
            }
            //设置状态值
            setState(status);
            //返回是否完全释放锁的标识
            return flag;
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }


    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
