package io.binghe.concurrent.chapter17;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public interface ReadWriteCache<K, V> {

    /**
     * 向缓存中写数据
     */
    void put(K key, V value);

    /**
     * 从缓存中读取数据
     */
    V get(K key);
}
