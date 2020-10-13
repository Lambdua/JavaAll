package com.lt.commonUseConcurrencyApi;

import com.lt.completableFuture.CompletableFutureConstant;

import java.util.concurrent.Semaphore;

/**
 * @author 梁先生
 * @Date 2020/10/13
 * @see Semaphore 用来控制同时访问特定资源的线程数量
 **/
public class SemaphoreUse {
    /**
     * permits: 同时允许多少线程访问该资源
     * fair: 是都时公平锁,默认非公平
     * 公平：等待时间越久的获取锁的优先度越高
     */
    static Semaphore someDataSemaphore = new Semaphore(3, true);
    private static final int THREAD_COUNT=10;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            CompletableFutureConstant.POOL_EXECUTOR.execute(()->{
                try {
                    //获取一个资源信号量，获取不到会一直阻塞
                    someDataSemaphore.acquire();
                    System.out.println("save data"+Thread.currentThread().getName());
                    Thread.sleep(1000);
                    //释放资源信号量
                    someDataSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        CompletableFutureConstant.POOL_EXECUTOR.shutdown();
    }
}
