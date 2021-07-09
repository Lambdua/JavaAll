package com.lt.E_juc包中常用并发工具类;

import java.util.concurrent.CountDownLatch;

/**
 * @author 梁先生
 * @Date 2020/10/13
 * @see CountDownLatch 可以使一个线程等待别的线程都执行完后在执行当前线程
 * 但只能使用一次
 **/
public class CountDownLatchUse {
    //设置一个countdown,计数2
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(countDownLatch.getCount());
            //countdown-1=1
            countDownLatch.countDown();
            System.out.println(countDownLatch.getCount());
            //countdown-1=0;
            countDownLatch.countDown();
        }).start();
        //等待countdown=0时继续执行当前线程
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName());
    }
}
