package io.binghe.concurrent.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author binghe
 * @version 1.0.0
 * @description Redisson的配置类
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
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
