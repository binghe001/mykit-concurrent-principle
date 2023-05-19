package io.binghe.concurrent.chapter04;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试不安全的单例对象
 */
public class SingleInstance {

    private static SingleInstance instance;
//    private static volatile SingleInstance instance;

    private SingleInstance() {

    }


    public static SingleInstance getInstance() {
        if (instance == null) {
            synchronized (SingleInstance.class) {
                if (instance == null) {
                    instance = new SingleInstance();
                    /*
                    1.  在堆内存中为新的 `User` 对象分配空间。
                    2.  在栈内存中为变量 `user` 分配空间。
                    3.  调用 `User` 类的构造函数来初始化这个新对象。
                    4.  将新创建的 `User` 对象在堆内存中的地址赋值给栈内存中的变量 `user`。
                    */
                }
            }
        }
        return instance;
    }
}
