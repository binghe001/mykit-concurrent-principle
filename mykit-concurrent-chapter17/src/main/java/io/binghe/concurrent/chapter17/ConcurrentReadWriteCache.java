package io.binghe.concurrent.chapter17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author binghe
 * @version 1.0.0
 * @description 线程安全的缓存
 */
public class ConcurrentReadWriteCache<K, V> implements ReadWriteCache<K, V> {
    /**
     * 缓存中存储数据的map
     */
    private volatile Map<K, V> map = new HashMap<K, V>();

    /**
     * 读写锁
     */
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    private final Lock readLock = lock.readLock();

    /**
     * 写锁
     */
    private final Lock writeLock = lock.writeLock();

    /**
     * 向缓存中写数据
     */
    @Override
    public void put(K key, V value){
        try{
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " 写数据开始");
            map.put(key, value);
        }finally {
            System.out.println(Thread.currentThread().getName() + " 写数据结束");
            writeLock.unlock();
        }
    }

    /**
     * 根据指定的key读取缓存中的数据
     */
    @Override
    public V get(K key){
        //定义返回的数据为空
        V value = null;
        try{
            //获取读锁
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " 读数据开始");
            //从缓存中获取数据
            value = map.get(key);
        }finally {
            System.out.println(Thread.currentThread().getName() + " 读数据结束");
            //释放读锁
            readLock.unlock();
        }
        //如果从缓存中获取的数据不为空，则直接返回数据
        if (value != null){
            return value;
        }
        //缓存中的数据为空
        try{
            //获取写锁
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " 从数据库读取数据并写入缓存开始");
            //模拟从数据库中获取数据
            value = getValueFromDb();
            //将数据放入map缓存中
            map.put(key, value);
        }finally {
            System.out.println(Thread.currentThread().getName() + " 从数据库读取数据并写入缓存结束");
            //释放写锁
            writeLock.unlock();
        }
        //返回数据
        return value;
    }

    /**
     * 模拟从数据库中获取数据
     */
    private V getValueFromDb() {
        return (V) "binghe";
    }
}
