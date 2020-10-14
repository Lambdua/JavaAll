package com.lt.threadPool;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 梁先生
 * @Date 2020/10/14
 * 线程池的异常处理策略
 * JDK 提供了 4 种 RejectedExecutionHandler 接口的实现,
 * ThreadPoolExecutor 的饱和策略可以
 * 通过调用 {@link ThreadPoolExecutor#setRejectedExecutionHandler(RejectedExecutionHandler)}来修改。
 * 它们都是以 ThreadPoolExecutor 类的静态内部类的形式定义的，它们的具体实现以及拒绝策略如下：
 **/
public class RejectExceptionHandlerIntroduction {

    /**
     * AbortPolicy : 线程池创建时默认使用的拒绝策略,抛出未检查的 RejectedExecutionException，调用者自己捕获处理
     * @see java.util.concurrent.ThreadPoolExecutor.AbortPolicy
     */
    public static class AbortPolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    e.toString()); // 抛异常！
        }
    }

    /**
     * DiscardPolicy:抛弃新提交的任务
     * @see java.util.concurrent.ThreadPoolExecutor.DiscardPolicy
     */
    public static class DiscardPolicy implements RejectedExecutionHandler {
        public DiscardPolicy() { }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            //什么都没做
        }
    }

    /**
     * DiscardOldestPolicy: 抛弃下一个被执行的任务，然后重新尝试提交任务
     *  ☆不要和 PriorityBlockingQueue 一起使用，会丢失优先级最高的任务
     * @see java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy
     */
    public static class DiscardOldestPolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) { // 先判断线程池关没
                e.getQueue().poll(); // 丢到等待队列中下一个要被执行的任务
                e.execute(r); // 重新尝试提交新来的任务
            }
        }
    }


    /**
     * CallerRunsPolicy: 既不抛出异常，也不抛弃任务
     * 它不会在线程池中执行该任务，而是在调用 execute 提交这个任务的线程执行
     * 如当主线程提交了任务时，任务队列已满，此时该任务会在主线程中执行。
     * 这样主线程在一段时间内不会提交任务给线程池，使得工作者线程有时间来处理完正在执行的任务
     * 可以实现服务器在高负载下的性能缓慢降低
     * 提交任务的应用程序被拿去执行任务了，不会返回 accept，TCP 层的请求队列会被填满而抛弃请求，
     * 客户端才会反应过来，即可以通过 TCP 层来缓冲一下
     * @see java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
     */
    public static class CallerRunsPolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                // 直接在把它提交来的线程调用它的 run 方法，相当于没有新建一个线程来执行它，
                // 而是直接在提交它的线程执行它，这样负责提交任务的线程一段时间内不会提交新的任务来
                r.run();
            }
        }
    }
}
