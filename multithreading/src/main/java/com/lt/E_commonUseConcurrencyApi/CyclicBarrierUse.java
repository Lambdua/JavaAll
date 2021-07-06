package com.lt.E_commonUseConcurrencyApi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 梁先生
 * @Date 2020/10/13
 * @see CyclicBarrier
 * 可以让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会打开
 * 让所有线程通过，并且这个屏障可以循环使用（这点和 CountDownLatch 很不同）
 **/
public class CyclicBarrierUse {
    //parties: 指让多少个线程或者任务等待至barrier状态
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new After());

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(() -> {
            System.out.println(" in thread");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                System.out.println("异常" + e.getMessage());
            }
        }).start();

        System.out.println("in main ");
        cyclicBarrier.await();

        System.out.println("main finish");
    }

    static class After implements Runnable {

        @Override
        public void run() {
            System.out.println("等待线程数以足够，启动当前任务");
        }
    }
}
