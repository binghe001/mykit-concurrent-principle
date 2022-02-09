package io.binghe.concurrent.chapter07;

/**
 * @author binghe
 * @version 1.0.0
 * @description synchronized修饰代码块
 */
public class SynchronizedBlockTest {

    private Long countA = 0L;
    private Long countB = 0L;

    public synchronized void incrementCount1(){
        countA ++;
        countB ++;
    }

    private Object countALock = new Object();
    private Object countBLock = new Object();

    public void incrementCount(){
        synchronized (countALock){
            countA ++;
        }
        synchronized (countBLock){
            countB ++;
        }
    }
}
