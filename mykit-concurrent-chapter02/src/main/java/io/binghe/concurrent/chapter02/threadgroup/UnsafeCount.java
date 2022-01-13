package io.binghe.concurrent.chapter02.threadgroup;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试线程的不安全性
 */
public class UnsafeCount {

    private long value;

    public long nextValue(){
        return value++;
    }
}
