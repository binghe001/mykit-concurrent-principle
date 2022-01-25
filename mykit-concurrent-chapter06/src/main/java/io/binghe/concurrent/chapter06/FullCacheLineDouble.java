package io.binghe.concurrent.chapter06;

/**
 * @author binghe
 * @version 1.0.0
 * @description double类型填充缓存行
 */
public class FullCacheLineDouble {
    public volatile double value = 0;
    public double d1, d2, d3, d4, d5, d6;
}
