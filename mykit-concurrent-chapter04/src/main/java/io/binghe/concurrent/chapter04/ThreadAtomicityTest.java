package io.binghe.concurrent.chapter04;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试线程的原子性
 */
public class ThreadAtomicityTest {

    private Long count;

    public Long getCount(){
        return count;
    }

    public void  incrementCount(){
        count++;
    }
}
