package io.binghe.concurrent.chapter12;

import java.util.HashSet;
import java.util.Set;

/**
 * @author binghe
 * @version 1.0.0
 * @description 锁优化示例代码片段
 */
public class LockOptimizationTest {

    private void callSafeMethod1(){

    }

    private void callSafeMethod2(){

    }

    private void callUsafeMethod(){

    }

    public void syncMethod(){
        callSafeMethod1();
        synchronized (this){
            callUsafeMethod();
        }
        callSafeMethod2();
    }


    private Set<String> userSet = new HashSet<>();
    private Set<String> orderSet = new HashSet<>();

    public void addUser(String user){
        synchronized (userSet){
            userSet.add(user);
        }
    }
    public void addOrder(String order){
        synchronized (orderSet){
            orderSet.add(order);
        }
    }
    public void removeUser(String user){
        synchronized (userSet){
            userSet.remove(user);
        }
    }
    public void removeOrder(String order){
        synchronized (orderSet){
            orderSet.remove(order);
        }
    }


    private Object lock = new Object();

    public void lockMethod(){
        synchronized (lock){
            //省略代码片段1
            //省略代码片段2
        }
    }

    public void lockForMethod(){
        synchronized (lock){
            for (int i = 0; i < 100; i++){
                //省略其他代码
            }
        }
    }
}
