package com.lt.threadSimplyUse;

import java.util.concurrent.*;

/**
 * @author 梁先生
 * @Date 2020/10/6
 * java中使用都线程的几种方式
 **/
public class MultiThreadingUseInJava {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MultiThreadingUseInJava entity = new MultiThreadingUseInJava();
        //1. 继承Thread类
        MyThread myThread = entity.new MyThread();
        myThread.start();
        //2. 继承Runnable接口
        MyRunnable myRunnable = entity.new MyRunnable();
        new Thread(myRunnable).start();
        //3. 实现callable接口
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> submit = executor.submit(entity.new MyCallable());
        String callableResult = submit.get();
        System.out.println("callableResult = " + callableResult);
    }


    private class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("重写run方法");
        }
    }


    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("实现run方法");
        }
    }


    /**
     * Callable 接口的使用需要搭配线程池，由于这里还没有介绍线程池的概念，
     * 提前介绍可能会造成理解障碍，所以放在后续介绍线程池的部分
      */
    private class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "实现call方法";
        }
    }
}
