package io.binghe.concurrent.chapter02.threadgroup;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试线程的安全性
 */
public class SafeCount {

    private long value;

    public synchronized long nextValue(){
        return value++;
    }
}
