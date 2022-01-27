package io.binghe.concurrent.chapter06;

/**
 * @author binghe
 * @version 1.0.0
 * @description Happens-Before原则
 */
public class HappensBeforeTest {

    /**
     * 对象的终结原则
     */
    public HappensBeforeTest(){
        System.out.println("执行构造方法");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("执行finalize()方法");
    }
    public static void main(String[] args){
        new HappensBeforeTest();
        //通知JVM执行GC，不一定立刻执行
        System.gc();
    }

    /**
     * 程序次序原则
     */
    public void programOrder(){
        int a = 1;
        int b = 2;
        int sum = a + b;
    }


    private volatile int count = 0;
    private double amount = 0;

    /**
     * volatile变量写规则
     */
    public void writeAmountAndCount(){
        amount = 1;
        count = 1;
    }

    /**
     * volatile变量读规则
     */
    public void readAmountAndCount(){
        if (count == 1){
            System.out.println(amount);
        }
    }


    private int value = 0;
    /**
     * 锁定原则
     */
    public void synchrionizedUpdateValue(){
        synchronized (this){
            if (value < 1){
                value = 1;
            }
        }
    }


    /**
     * 线程启动原则
     */
    public void threadStart(){
        Thread thread2 = new Thread(()-> {
            System.out.println(value);
        });
        value = 10;
        thread2.start();
    }

    /**
     * 线程终结原则
     */
    public void threadEnd() throws InterruptedException {
        Thread thread2 = new Thread(()-> {
            value = 10;
        });
        thread2.start();
        thread2.join();
        System.out.println(value);
    }


    /**
     * 线程中断原则
     */
    public void threadInterrupt() throws Exception{
        Thread thread2 = new Thread(()->{
            if(Thread.currentThread().isInterrupted()){
                System.out.println(value);
            }
        });
        thread2.start();
        value = 10;
        thread2.interrupt();
    }
}
