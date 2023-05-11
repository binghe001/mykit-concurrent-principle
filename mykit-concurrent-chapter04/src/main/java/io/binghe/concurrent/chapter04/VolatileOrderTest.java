package io.binghe.concurrent.chapter04;

/**
 * @author pishi
 * @description: volatile 保证有序性证明
 * @date 2023年05月10日 20:00
 */
public class VolatileOrderTest {

    private static int a;
//    private static  volatile int a;

    private static int b;
//    private static volatile int b;

    private static int x;

    private static int y;


    public static void main(String[] args) throws InterruptedException {

        //小概率事件,基数够大,才能复现
        for (int i = 0; i < 100000; i++) {
            x = y = a = b = 0;

            //region 如果是有序的情况, 1 或 3 肯定有一个会比 2、4 先执行;所以就绝不会出现 x 、 y 同时 = 0的情况
            final Thread thread = new Thread(() -> {
                a = 1; //1
                x = b; //2
            });


            final Thread thread1 = new Thread(() -> {
                b = 1; //3
                y = a; //4
            });
            //endregion


            thread.start();
            thread1.start();
            thread.join();
            thread1.join();

            if (x == 0 && y == 0) {
                System.out.println("x = " + x + ",y = " + y);
            }


        }

    }

}
