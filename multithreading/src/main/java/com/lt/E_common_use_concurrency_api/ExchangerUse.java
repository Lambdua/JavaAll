package com.lt.E_common_use_concurrency_api;


import com.lt.Z_common.constants.CompletableFutureConstant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

/**
 * @author 梁先生
 * @Date 2020/10/13
 * @see Exchanger 一个用于两个线程间交换数据的工具类。
 * 如果第一个线程先执行了`exchange(V)`方法，它会阻塞在那里，
 * 等待第二个线程执行`exchange(V)`方法，
 * `exchange(V)`会返回另一个线程传入的数据。
 **/
public class ExchangerUse {
    static Exchanger<String> strExchanger=new Exchanger<>();
    static CountDownLatch countDownLatch=new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        //执行第一个
        CompletableFutureConstant.POOL.execute(()->{
            String currentThreadName=Thread.currentThread().getName();
            try {
                Thread.sleep(1000);
                String result = strExchanger.exchange(currentThreadName);
                System.out.println(currentThreadName+" 接收到的exchanger值："+result);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //执行第二个
        CompletableFutureConstant.POOL.execute(()->{
            String currentThreadName=Thread.currentThread().getName();
            try {
                Thread.sleep(1000);
                String result = strExchanger.exchange(currentThreadName);
                System.out.println(currentThreadName+" 接收到的exchanger值："+result);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        countDownLatch.await();
        System.out.println("main finish");
        CompletableFutureConstant.POOL.shutdown();
    }
}
