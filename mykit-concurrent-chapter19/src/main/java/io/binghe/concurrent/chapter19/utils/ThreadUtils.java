package io.binghe.concurrent.chapter19.utils;

/**
 * @author binghe
 * @version 1.0.0
 * @description 根据线程id获取线程对象的工具类
 */
public class ThreadUtils{
    //根据线程id获取线程句柄
    public static Thread getThreadByThreadId(long threadId){
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group != null){
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++){
                if(threadId == threads[i].getId()){
                    return threads[i];
                }
            }
        }
        return null;
    }
}
