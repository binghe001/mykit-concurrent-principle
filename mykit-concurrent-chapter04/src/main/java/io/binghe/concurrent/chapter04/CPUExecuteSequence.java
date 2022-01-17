package io.binghe.concurrent.chapter04;

/**
 * @author binghe
 * @version 1.0.0
 * @description CPU执行顺序
 */
public class CPUExecuteSequence {

    public static void main(String[] args){
        int a = 1;
        int b = 2;
        int sum = a + b;
        System.out.println(sum);
    }
}
