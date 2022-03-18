package io.binghe.concurrent.chapter19.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author binghe
 * @version 1.0.0
 * @description 19.6.3  引入try-finally代码块
 */
@RestController
@RequestMapping("/order/v3")
public class OrderV3Controller {

    private final Logger logger = LoggerFactory.getLogger(OrderV3Controller.class);

    /**
     * 假设商品的id为1001
     */
    private static final String PRODUCT_ID = "1001";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/submitOrder")
    public String submitOrder(){
        //获取当前线程的id
        long threadId = Thread.currentThread().getId();
        //通过stringRedisTemplate来调用Redis的SETNX命令，key为商品的id，value为当前执行方法的线程id
        Boolean isLocked = stringRedisTemplate.opsForValue().setIfAbsent(PRODUCT_ID, String.valueOf(threadId));
        //获取锁失败，则直接返回下单失败
        if (!isLocked){
            return "failure";
        }
        try{
            String stockStr = stringRedisTemplate.opsForValue().get(PRODUCT_ID);
            if (stockStr == null || "".equals(stockStr.trim())){
                logger.info("库存不足，扣减库存失败");
                throw new RuntimeException("库存不足，扣减库存失败");
            }
            //将库存转化成int类型，进行减1操作
            int stock = Integer.parseInt(stockStr);
            if(stock > 0){
                stock -= 1;
                stringRedisTemplate.opsForValue().set(PRODUCT_ID, String.valueOf(stock));
                logger.info("库存扣减成功，当前库存为：{}", stock);
            }else{
                logger.info("库存不足，扣减库存失败");
                throw new RuntimeException("库存不足，扣减库存失败");
            }
        }finally {
            //执行完业务后，删除PRODUCT_ID，释放锁
            stringRedisTemplate.delete(PRODUCT_ID);
        }
        return "success";
    }
}
