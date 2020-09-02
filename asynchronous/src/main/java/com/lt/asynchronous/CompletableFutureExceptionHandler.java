package com.lt.asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 梁先生
 * @Date 2020/9/2
 * 异常处理
 * CompletableFuture提供了completeExceptionally方法来处理异常情况
 **/
public class CompletableFutureExceptionHandler {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1.创建一个CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<>();

        // 2.开启线程计算任务结果，并设置
        new Thread(() -> {
            // 2.1休眠3s，模拟任务计算
            try {
                // 2.1.1 抛出异常
                if (true) {
                    throw new RuntimeException("excetion test");
                }
                // 2.1.2设置正常结果
                future.complete("ok");
            } catch (Exception e) {
                // 2.1.3 设置异常结果
                future.completeExceptionally(e);
            }
            // 2.2设置计算结果到future
            System.out.println("----" + Thread.currentThread().getName() + " set future result----");

        }, "thread-1").start();

        // 3.等待计算结果
//        System.out.println(future.get());
        // 此方式在有异常时，返回默认值，实现当出现异常时返回默认值。
        System.out.println(future.exceptionally(t -> "default").get());// 默认值
    }
}
