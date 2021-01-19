package com.lt.safeCancleThread;

import com.lt.completableFuture.CompletableFutureConstant;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author 梁先生
 * @Date 2020/10/19
 * 计时任务简单实现
 * 需求：给一个 Runnable r 和时间 long timeout，解决
 * “最多花 timeout 分钟运行 Runnable 没运行完就取消”这种要求。
 **/
public class TimingTaskSimpleImpl {
    public static void run(Runnable runnable, long timeout, TimeUnit unit) {
        Future<?> task = CompletableFutureConstant.POOL.submit(runnable);
        try {
            task.get(timeout, unit);
        } catch (InterruptedException e) {
            //中断标志为true,再合适的地方会抛出中断异常
            e.printStackTrace();
        } catch (ExecutionException e) {
            //任务运行异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            //超时异常
            e.printStackTrace();
        } finally {
            //如果任务已经结束了，这个方法无影响
            //如果任务还在运行，这句会中断任务
            task.cancel(true);
        }
    }
}
