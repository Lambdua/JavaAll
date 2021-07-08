package com.lt.线程异常处理;

import com.lt.Z_common.ThreadUtils;

/**
 * @author liangtao
 * @description 线程的异常处理
 * @date 2021年07月08 09:40
 **/
public class ThreadExceptionDemo {

    static Thread.UncaughtExceptionHandler exceptionHandler=(thread, throwable)->{
        System.out.println("抛出异常的线程："+thread.getName()+",异常原因： "+throwable.getMessage());
    };

    static Thread throwExceptionThread=new Thread(()->{
        System.out.println("线程运行");
        ThreadUtils.sleep(100L);
        throw new RuntimeException("抛出运行时异常");
    });



    public static void main(String[] args) {
//        entranceTest();
//        catchThreadExceptionTest1();
        catchThreadExceptionTest2();
    }


    /**
     * 注！ 线程的异常，优先在自己线程内部进行捕获处理，而不是依靠外部的异常处理机制进行处理！
     */

    /**
     * 执行下列测试程序，发现在主线程中的try-catch并不会捕捉到线程中抛出的异常。
     * 因为每个线程都是【独立运行的代码片段】,在没有进行主动的线程之间的通信下，
     * 线程之间是彼此隔离的.
     */
    public static void entranceTest(){
        try {
            throwExceptionThread.start();
        }catch (Exception e){
            System.out.println("捕捉到了线程运行时抛出的异常: "+e.getMessage());
        }
    }

    /**
     * 通过设置{@link Thread#setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler)}
     * 来设置线程的未处理异常处理器
     */
    public static void catchThreadExceptionTest1(){
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
        throwExceptionThread.start();
    }

    /**
     * 通过{@link Thread#setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler)}
     * 来对各个实例线程设置自己的未捕捉异常处理器
     */
    public static void catchThreadExceptionTest2(){
        throwExceptionThread.setUncaughtExceptionHandler(exceptionHandler);
        throwExceptionThread.start();
    }




}
