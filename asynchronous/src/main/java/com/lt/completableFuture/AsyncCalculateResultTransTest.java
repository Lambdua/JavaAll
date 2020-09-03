package com.lt.completableFuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.lt.completableFuture.CompletableFutureConstant.POOL_EXECUTOR;

/**
 * @author 梁先生
 * @Date 2020/9/2
 * 基于单CompletableFuture实现异步计算与结果转换
 **/
public class AsyncCalculateResultTransTest {
    CompletableFutureConstant constant = new CompletableFutureConstant();

    @Test
    public void simpleTest() throws ExecutionException, InterruptedException {
        //1. 创建一个completableFuture对象
        CompletableFuture<String> future = new CompletableFuture<>();
        // 2.开启线程计算任务结果，并设置
        POOL_EXECUTOR.execute(() -> {
            try {
                //2.1睡眠
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 2.2设置计算结果到future
            System.out.println("----" + Thread.currentThread().getName() + " set future result----");
            future.complete("hello");
        });
        // 3.等待计算结果
        System.out.println("---main thread wait future result---");
        System.out.println(future.get());
        System.out.println("---main thread got future result---");
    }

    /**
     * 基于runAsync系列方法实现无返回值的异步计算
     */
    @Test
    public void runAsyncTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(constant.runAsyncRunnable);
        constant.templateMethod(future);
    }

    /**
     * 基于runAsync系列方法实现无返回值的异步计算,
     * 使用自定义线程池
     */
    @Test
    public void runAsyncByCustomizePoolTest() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(constant.runAsyncRunnable, POOL_EXECUTOR);
        constant.templateMethod(future);
    }


    /**
     * 基于supplyAsync系列方法实现有返回值的异步计算
     */
    @Test
    public void runAsyncHaveResultTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(constant.supplyAsyncSupplier);
        constant.templateMethod(future);
    }

    /**
     * 基于thenRun实现异步任务A，执行完毕后，激活异步任务B执行，
     * 需要注意的是，这种方式激活的异步任务B是拿不到任务A的执行结果的：
     */
    @Test
    public void thenRunCombineTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(constant.supplyAsyncSupplier).thenRun(constant.thenRunRunnable);
        constant.templateMethod(future);
    }


    /**
     * 基于thenAccept实现异步任务A，执行完毕后，激活异步任务B执行
     * 需要注意的是，这种方式激活的异步任务B是可以拿到任务A的执行结果的：
     */
    @Test
    public void thenAcceptCombineTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(constant.supplyAsyncSupplier).thenAccept(constant.thenAcceptConsumer);
        constant.templateMethod(future);
    }

    /**
     * 基于thenApply实现异步任务A，执行完毕后，激活异步任务B执行。 thenApply有返回值
     * 需要注意的是，这种方式激活的异步任务B是可以拿到任务A的执行结果的，并且可以获取到异步任务B的执行结果。[
     */
    @Test
    public void thenApplyCombineTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(constant.supplyAsyncSupplier).thenApply(constant.thenApplyFunction);
        constant.templateMethod(future);
    }

    /**
     * 基于whenComplete设置回调函数，当异步任务执行完毕后进行回调，不会阻塞调用线程：
     */
    @Test
    public void whenCompleteTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(constant.supplyAsyncExceptionSupplier);
        CompletableFuture<String> completableFuture = supplyAsyncFuture.whenComplete(constant.thenCompleteConsumer);
        constant.templateMethod(completableFuture);
    }
}
