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
 * @description 测试订单的接口
 */
@RestController
@RequestMapping("/order/v1")
public class OrderV1Controller {

    private final Logger logger = LoggerFactory.getLogger(OrderV1Controller.class);

    /**
     * 假设商品的id为1001
     */
    private static final String PRODUCT_ID = "1001";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/submitOrder")
    public String submitOrder(){
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
        return "success";
    }
}
