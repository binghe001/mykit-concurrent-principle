package io.binghe.concurrent.chapter17;

import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试缓存
 */
public class ReadWriteCacheTest {
    public static void main(String[] args){
        //创建缓存对象
        ReadWriteCache<String, Object> readWriteCache = new ConcurrentReadWriteCache<String, Object>();
        IntStream.range(0, 5).forEach((i) -> {
            new Thread(()->{
                String key = "name_".concat(String.valueOf(i));
                String value = "binghe_".concat(String.valueOf(i));
                readWriteCache.put(key, value);
            }).start();
        });

        IntStream.range(0, 5).forEach((i) -> {
            new Thread(() -> {
                String key = "name_".concat(String.valueOf(i));
                readWriteCache.get(key);
            }).start();
        });
    }
}
