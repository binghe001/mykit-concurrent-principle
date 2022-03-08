package io.binghe.concurrent.chapter11;

import java.util.ArrayList;
import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 一次性申请和释放资源
 */
public class ResourcesRequester {

    //存放申请资源的集合
    private List<Object> resources = new ArrayList<Object>();

    //一次性申请所有的资源
    public synchronized boolean applyResources(Object source, Object target){
        if (resources.contains(source) || resources.contains(target)){
            return false;
        }
        resources.add(source);
        resources.add(target);
        return true;
    }

    public synchronized void releaseResources(Object source, Object target){
        resources.remove(source);
        resources.remove(target);
    }
}
