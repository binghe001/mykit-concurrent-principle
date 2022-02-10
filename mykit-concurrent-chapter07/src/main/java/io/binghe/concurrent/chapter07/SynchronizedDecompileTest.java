package io.binghe.concurrent.chapter07;

/**
 * @author binghe
 * @version 1.0.0
 * @description Synchronized 反编译案例
 */
public class SynchronizedDecompileTest {

    public synchronized void syncMethod(){
        System.out.println("hello synchronized method");
    }

    public void synCodeBlock(){
        synchronized (this){
            System.out.println("hello synchronized code block");
        }
    }
}
