package io.binghe.concurrent.chapter12;

import java.util.HashSet;
import java.util.Set;

/**
 * @author binghe
 * @version 1.0.0
 * @description 锁未优化示例代码片段
 */
public class LockUnOptimizationTest {

    private void callSafeMethod1(){

    }

    private void callSafeMethod2(){

    }

    private void callUsafeMethod(){

    }

    public synchronized void syncMethod(){
        callSafeMethod1();
        callUsafeMethod();
        callSafeMethod2();
    }



    private Set<String> userSet = new HashSet<>();
    private Set<String> orderSet = new HashSet<>();

    public synchronized void addUser(String user){
        userSet.add(user);
    }
    public synchronized void addOrder(String order){
        orderSet.add(order);
    }
    public synchronized void removeUser(String user){
        userSet.remove(user);
    }
    public synchronized void removeOrder(String order){
        orderSet.remove(order);
    }


    private Object lock = new Object();

    public void lockMethod(){
        synchronized (lock){
            //省略代码片段1
        }
        synchronized (lock){
            //省略代码片段2
        }
    }

    public void lockForMethod(){
        for (int i = 0; i < 100; i++){
            synchronized (lock){
                //省略其他代码
            }
        }
    }
}
