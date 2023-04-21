package io.binghe.concurrent.chapter19.task;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description 更新分布式锁的超时时间
 */
public class UpdateLockTimeoutTask implements Runnable{
    private String currentThreadId;
    private StringRedisTemplate stringRedisTemplate;
    private String key;

    public UpdateLockTimeoutTask(String currentThreadId,
                                 StringRedisTemplate stringRedisTemplate,
                                 String key) {
        this.currentThreadId = currentThreadId;
        this.stringRedisTemplate = stringRedisTemplate;
        this.key = key;
    }

    @Override
    public void run() {
        //以传递的线程id为key，当前执行更新超时时间的线程为value，保存到redis中
        stringRedisTemplate.opsForValue().set(currentThreadId, String.valueOf(Thread.currentThread().getId()));
        while (true){
            if (Thread.interrupted()){
                break;
            }
            stringRedisTemplate.expire(key, 30, TimeUnit.SECONDS);
            try {
                //每个10秒执行一次
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
