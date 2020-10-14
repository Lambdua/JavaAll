package com.lt.threadPool;

import java.util.concurrent.*;

/**
 * @author 梁先生
 * @Date 2020/10/13
 * @see java.util.concurrent.Executor
 * ThreadPoolExecutor 执行具体流程如下：
 *
 * 1）当池子大小小于corePoolSize就新建线程，并处理请求
 *
 * 2）当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去从workQueue中取任务并处理
 *
 * 3）当workQueue放不下新入的任务时，新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize就用RejectedExecutionHandler来做拒绝处理
 *
 * 4）另外，当池子的线程数大于corePoolSize的时候，多余的线程会等待keepAliveTime长的时间，如果无请求可处理就自行销毁
 **/
public class ThreadPoolIntroduction {

    /**
     * @param corePoolSize    核心线程池大小，即没有执行任务时的线程池大小，只有在工作队列满了的情况下才会创建超出这个数量的线程
     * @param maximumPoolSize 最大线程池的大小
     * @param keepAliveTime   某个线程的空闲时间超过了存活时间，那么将被标记为可回收的
     * @param unit            时间单位
     * @param workQueue       用来暂时保存任务的工作队列
     * @param threadFactory
     * @param handler         当 ThreadPoolExecutor 已经关闭或者达到了最大线程池大小并且工作队列已满时，
     *                        调用 `execute()` 方法会调用 RejectedExecutionHandler handler
     *                        的 `rejectedExecution(Runnable r, ThreadPoolExecutor executor);` 方法
     * @return
     */
    public ThreadPoolExecutor buildDefault(
            int corePoolSize, int maximumPoolSize, long keepAliveTime,
            TimeUnit unit, BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        return threadPoolExecutor;
    }


    /**
     * 创建固定长度的线程池，每当提交一个任务时就创建一个线程，直到达到线程池的最大数量
     * 如果某个线程由于发生了未预期的 Exception 而结束，那么线程池会补充一个新的线程。
     *
     * @param threadNums 线程数量
     */
    public ThreadPoolExecutor buildFixedThreadPool(int threadNums) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNums);
        //上下两者等价
        return new ThreadPoolExecutor(
                //线程池大小不可拓展
                threadNums, threadNums,
                //多余的线程会被立即终止
                0L, TimeUnit.MILLISECONDS,
                // 使用容量为 Integer.MAX_VALUE 的工作队列
                // FIFO
                new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 可缓存的线程池，如果线程池的当前规模超过了处理需求时，那么将回收空闲的线程，而当需求增加时，则可以添加新的线程，线程池的规模不存在任何限制。
     */
    public ThreadPoolExecutor buildCacheThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //上下两者等价
        return new ThreadPoolExecutor(
                //创建基础线程数量为0，可拓展到最大值的线程池
                0, Integer.MAX_VALUE,
                //空闲线程60秒后回收
                60L, TimeUnit.SECONDS,
                // 不存储元素的阻塞队列, 每一个 put 操作等待一个 take 操作，否则无法继续添加元素
                new SynchronousQueue<Runnable>());
    }

    /**
     * 单个工作者线程来执行任务，如果这个线程异常结束，会创建另一个线程来替代。能确保依照任务在队列中的顺序来串行执行。
     */
    public ExecutorService buildSingleThreadPool() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        return singleThreadExecutor;
//        return new FinalizableDelegatedExecutorService
//                (new ThreadPoolExecutor(1, 1,
//                        0L, TimeUnit.MILLISECONDS,
//                        new LinkedBlockingQueue<Runnable>()));

//        return new ThreadPoolExecutor(
//                //线程固定大小为1
//                1, 1,
//                //空闲线程直接回收
//                0L, TimeUnit.MICROSECONDS,
//                new LinkedBlockingQueue<Runnable>());
    }


    /**
     * 可以在给定的延迟后运行命令，或者定期执行命令。比 Timer 更灵活，功能更强大。
     */
    public ExecutorService buildScheduledThreadPoolExecutor(int threadNum) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(threadNum);
        //然后可以通过 schedule 方法提交线程到线程池：
        ScheduledFuture<String> future = scheduledExecutorService.schedule(() -> {
            return "schedule 执行";
        }, 60L, TimeUnit.SECONDS);
        System.out.println(future.get());
        return scheduledExecutorService;
    }

}
