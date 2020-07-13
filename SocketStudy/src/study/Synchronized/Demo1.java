package study.classLoader;

import java.io.ObjectInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liangtao
 * @Date 2019/9/14
 * synchronized三种作用方式:
 * 1. 作用在实例方法上
 * 2. 作用在静态方法上
 * 3. 静态代码块的方式
 **/
public class Demo1 {
    static int num = 0;
    static int sum = 0;

    public synchronized void instanceMethod(int num) {
        System.out.println(num++);
    }

    public static /*synchronized*/ void staticMethod() {
        for (int i = 0; i < 100000; i++) {

            System.out.println(sum++);
        }
    }

    public static void main(String[] args) {

        Object lock = new Object();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Runnable runnable = () -> {
            staticMethod();
        };
       /* threadPool.execute(runnable);
        threadPool.execute(runnable);*/

        Thread thread1=new Thread(runnable);

        Thread thread2=new Thread(runnable);
        thread1.start();
        thread2.start();


        //静态代码块
        synchronized (lock) {

        }


    }
}
