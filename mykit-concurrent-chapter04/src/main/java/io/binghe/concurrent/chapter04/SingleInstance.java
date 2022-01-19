package io.binghe.concurrent.chapter04;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试不安全的单例对象
 */
public class SingleInstance {

    private static SingleInstance instance;

    private SingleInstance(){

    }

    public static SingleInstance getInstance(){
        if (instance == null){
            synchronized (SingleInstance.class){
                if (instance == null){
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }
}
