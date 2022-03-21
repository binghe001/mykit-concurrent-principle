package io.binghe.concurrent.service;

/**
 * @author binghe
 * @version 1.0.0
 * @description 秒杀服务
 */
public interface SeckillService {

    /**
     * 秒杀操作
     * @param id 商品id
     * @param number 秒杀下单的商品数量
     */
    int secKill(String id, int number);
}
