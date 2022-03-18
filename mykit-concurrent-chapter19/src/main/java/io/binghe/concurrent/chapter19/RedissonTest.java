package io.binghe.concurrent.chapter19;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description RedissonTest类
 */
public class RedissonTest {

    /**
     * 以3个RedissonClient测试红锁
     */
    public void testRedLockByThreeRessionClient(RedissonClient redisson1,
                                                RedissonClient redisson2,
                                                RedissonClient redisson3){
        RLock lock1 = redisson1.getLock("lock1");
        RLock lock2 = redisson2.getLock("lock2");
        RLock lock3 = redisson3.getLock("lock3");
        RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
        try {
            //lock1、lock2、lock3大多数加锁成功，最终加锁成功
            lock.lock();
            //TODO 执行加锁成功的逻辑
        } finally {
            lock.unlock();
        }
    }

    private static RedissonClient redissonClient(){
        Config config = new Config();
        //指定编码，默认编码为org.redisson.codec.JsonJacksonCodec
        //之前使用的spring-data-redis，用的客户端jedis，编码为org.springframework.data.redis.serializer.StringRedisSerializer
        //改用redisson后为了之间数据能兼容，这里修改编码为org.redisson.client.codec.StringCodec
        config.setCodec(new org.redisson.client.codec.StringCodec());
        SingleServerConfig singleServer = config.useSingleServer();
        String address = "redis://127.0.0.1:6379";
        singleServer.setAddress(address);
        singleServer.setDatabase(1);
        singleServer.setConnectTimeout(30000);
        singleServer.setIdleConnectionTimeout(30000);
        singleServer.setKeepAlive(true);
        singleServer.setPingConnectionInterval(15000);
        singleServer.setRetryAttempts(5);
        singleServer.setRetryInterval(3000);
        singleServer.setTimeout(3000);
        RedissonClient redisson = null;
        try {
            redisson = Redisson.create(config);
        } catch (RedisException e) {
            e.printStackTrace();
        }
        return redisson;
    }
}
