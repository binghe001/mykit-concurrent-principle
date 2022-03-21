package io.binghe.concurrent.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 秒杀活动开始前构建商品缓存
 */
@Component
public class SeckillCacheBuilder {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String GOODS_CACHE = "seckill:goodsStock:";

    private String getCacheKey(String id) {
        return GOODS_CACHE.concat(id);
    }


    public void prepare(String id, int totalCount) {
        String key = getCacheKey(id);
        Map<String, Integer> goods = new HashMap<>();
        goods.put("totalCount", totalCount);
        goods.put("initStatus", 0);
        goods.put("seckillCount", 0);
        redisTemplate.opsForHash().putAll(key, goods);
    }
}
