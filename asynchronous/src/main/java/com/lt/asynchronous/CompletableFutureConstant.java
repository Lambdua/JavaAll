package com.lt.asynchronous;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 梁先生
 * @Date 2020/9/2
 * completableFuture测试使用全部用例
 * 当Completable-Future的计算任务完成后，会自动弹出栈中的行为方法并执行。
 * 需要注意的是，由于是栈结构，在同一个CompletableFuture对象上行为注册的顺序与行为执行的顺序是相反的
 **/
public class CompletableFutureConstant {

    //自定义线程池
    //线程数等于cpu核心数
    public static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5));

    public  void templateMethod(CompletableFuture future) throws ExecutionException, InterruptedException {
        sleep(100);
        System.out.println("main 开始");
        System.out.println("future返回值：" + future.get());
        System.out.println("main 结束");
    }


    public Runnable runAsyncRunnable = () -> {
        printInfo("runAsync", 2000, null);
    };

    public Supplier<String> supplyAsyncSupplier = () -> {
        printInfo("supplyAsync", 2000, null);
        return "supplyAsync";
    };

    public Supplier<String> supplyAsyncExceptionSupplier = () -> {
        System.out.println("supplyAsyncExceptionS 开始");
        sleep(1000);
        //抛出异常
        int i = 2 / 0;
        return "supplyAsync";
    };

    public Runnable thenRunRunnable = () -> {
        printInfo("thenRun", 3000, null);
    };

    /**
     * Function<参数类型，返回类型>
     */
    public Function<String, String> thenApplyFunction = (param) -> {
        printInfo("thenApply", 2000, param);
        return "thenApply";
    };

    public Consumer<String> thenAcceptConsumer = (param) -> {
        printInfo("thenAccept", 1000, param);
    };

    public BiConsumer<String, Throwable> thenCompleteConsumer = (param1, param2) -> {
        if (ObjectUtil.isNotNull(param2)) {
            //存在异常信息,future执行失败
            System.out.println("future执行失败，异常信息： " + param2.getMessage());
        } else {
            System.out.println("所有future链都已执行完成,接收到的参数为： " + param1);
        }
    };

    public void printInfo(String methName, int timeMillis, String param) {
        if (StrUtil.isNotEmpty(param)) {
            System.out.println(methName + "开始,接收到的参数为: " + param);
        } else {
            System.out.println(methName + "开始");
        }
        sleep(timeMillis);
        System.out.println(methName + "结束");
    }

    public void sleep(int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
