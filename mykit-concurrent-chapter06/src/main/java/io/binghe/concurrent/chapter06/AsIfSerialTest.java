package io.binghe.concurrent.chapter06;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试as-if-serial原则
 */
public class AsIfSerialTest {

    public void getSumData(){
        int x = 20;
        int y = 10;
        int z = x / y;
    }
}

