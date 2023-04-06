package io.binghe.concurrent.chapter19.service.impl;

import io.binghe.concurrent.chapter19.service.RedisDistributeLock;
import io.binghe.concurrent.chapter19.task.UpdateLockTimeoutTask;
import io.binghe.concurrent.chapter19.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description 实现Redis分布式锁 19.6.11  解决锁失效问题
 */
@Service("redisDistributeLockV8")
public class RedisDistributeLockV8Impl implements RedisDistributeLock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    private ThreadLocal<Integer> threadLocalCount = new ThreadLocal<Integer>();
    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        Boolean isLocked = false;
        if (threadLocal.get() == null){
            String currentThreadId = this.getCurrentThreadId();
            threadLocal.set(currentThreadId);
            isLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, currentThreadId, timeout, unit);

            //如果获取锁失败，则执行自旋操作，直到获取锁成功
            if (!isLocked){
                for (;;){
                    isLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, currentThreadId, timeout, unit);
                    if (isLocked){
                        break;
                    }
                }
            }
            //启动线程执行定时更新超时时间的方法
            new Thread(new UpdateLockTimeoutTask(currentThreadId, stringRedisTemplate, key)).start();

        }else{
            isLocked = true;
        }
        //加锁成功后，计数器的值加1
        if (isLocked){
            Integer count = threadLocalCount.get() == null ? 0 : threadLocalCount.get();
            threadLocalCount.set(++count);
        }
        return isLocked;
    }

    @Override
    public void releaseLock(String key) {
        //当前线程中绑定的线程id与Redis中的线程id相同时，再执行删除锁的操作
        String currentThreadId = stringRedisTemplate.opsForValue().get(key);
        if (threadLocal.get().equals(currentThreadId)){
            Integer count = threadLocalCount.get();
            if (count == null || --count <= 0){
                stringRedisTemplate.delete(key);
                //防止内存泄露
                threadLocal.remove();
                threadLocalCount.remove();

                //通过当前线程的id从Redis中获取更新超时时间的线程id
                String updateTimeThreadId = stringRedisTemplate.opsForValue().get(currentThreadId);
                if (updateTimeThreadId != null && !"".equals(updateTimeThreadId.trim())){
                    Thread updateTimeThread = ThreadUtils.getThreadByThreadId(Long.parseLong(updateTimeThreadId));
                    if (updateTimeThread != null){
                        //中断更新超时时间的线程
                        updateTimeThread.interrupt();
                        stringRedisTemplate.delete(currentThreadId);

                    }
                }
            }

        }
    }

    private String getCurrentThreadId(){
        return String.valueOf(Thread.currentThread().getId());
    }
}
