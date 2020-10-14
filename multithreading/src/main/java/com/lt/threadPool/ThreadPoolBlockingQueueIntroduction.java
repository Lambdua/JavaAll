package com.lt.threadPool;

import java.util.concurrent.*;

/**
 * @author 梁先生
 * @Date 2020/10/14
 * ThreadPoolExecutor 的 {@link BlockingQueue}详解
 * BlockingQueue(阻塞队列):
 * <p>
 * 如果运行的线程少于 corePoolSize，则 Executor始终首选添加新的线程，而不进行排队(不添加进阻塞队列)。
 * 如果运行的线程等于或多于 corePoolSize，则 Executor 始终首选将请求加入队列，而不添加新的线程。
 * 如果无法将请求加入队列，则创建新的线程，除非创建此线程超出 maximumPoolSize，在这种情况下，任务将被拒绝。
 * <p>
 * 线程池队列详情见：
 * https://blog.csdn.net/CrazyMo_/article/details/80631348
 **/
public class ThreadPoolBlockingQueueIntroduction {

    public static void main(String[] args) {
        ThreadPoolBlockingQueueIntroduction entity = new ThreadPoolBlockingQueueIntroduction();
        entity.synchronousQueueUse();
    }


    private ThreadPoolExecutor buildByArgs(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60L, TimeUnit.SECONDS,
                workQueue, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 【1】. SynchronousQueue
     *  它将任务直接提交给线程而不保存它们。在此，如果不存在可用于立即运行任务的线程，则试图把任务加入队列将失败，因此会构造一个新的线程。
     *  此策略可以避免在处理可能具有内部依赖性的请求集时出现锁。直接提交通常要求无界,maximumPoolSizes 以避免拒绝新提交的任务。
     *  当命令以超过队列所能处理的平均数连续到达时，此策略允许无界线程具有增长的可能性。
     *  {@link SynchronousQueue}线程安全的Queue，可以存放若干任务（但当前只允许有且只有一个任务在等待），
     *  其中每个插入操作必须等待另一个线程的对应移除操作，也就是说A任务进入队列，B任务必须等A任务被移除之后才能进入队列，否则执行异常策略。
     *  你来一个我扔一个，所以说SynchronousQueue没有任何内部容量。
     */

    /**
     * 比如：核心线程数为2，最大线程数为3；使用SynchronousQueue。
     * 当前有2个核心线程在运行，又来了个A任务，两个核心线程没有执行完当前任务，根据如果运行的线程等于或多于 corePoolSize，
     * 则 Executor 始终首选将请求加入队列，而不添加新的线程。所以A任务被添加到队列，此时的队列是SynchronousQueue，
     * 当前不存在可用于立即运行任务的线程，因此会构造一个新的线程，此时又来了个B任务，两个核心线程还没有执行完。
     * 新创建的线程正在执行A任务，所以B任务进入Queue后，最大线程数为3，发现没地方仍了。就只能执行异常策略(RejectedExecutionException)。
     */
    public void synchronousQueueUse() {
        ThreadPoolExecutor pool = buildByArgs(2, 3, new SynchronousQueue<>());
        for (int i = 0; i < 4; i++) {
            pool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + " 执行 - " + j);
                }
                System.out.println("run");
            });
        }
        pool.shutdown();
    }


    /**
     * 【2】无界队列 如LinkedBlockingQueue
     *  使用无界队列（例如，不具有预定义容量的 {@link LinkedBlockingQueue}）将导致在所有核心线程都在忙时新任务在队列中等待。
     *  这样，创建的线程就不会超过 corePoolSize,因此，maximumPoolSize 的值也就没意义了。
     *  也就不会有新线程被创建，都在那等着排队呢。
     *  如果未指定容量，则它等于 Integer.MAX_VALUE。
     *  如果设置了Queue预定义容量，则当核心线程忙碌时，新任务会在队列中等待，直到超过预定义容量(新任务没地方放了)，才会执行异常策略。
     *  你来一个我接一个，直到我容不下你了。FIFO，先进先出。
     *
     *  无界的话要防止任务增长的速度远远超过处理任务的速度。
     */
    /**
     * 比如：
     * 核心线程数为2，最大线程数为3；使用LinkedBlockingQueue(1)，设置容量为1,不指定时为默认Integet.MAX_VALUE。
     * 当前有2个核心线程在运行，又来了个A任务，两个核心线程没有执行完当前任务，根据如果运行的线程等于或多于 corePoolSize，
     * 则 Executor 始终首选将请求加入队列，而不添加新的线程。所以A任务被添加到队列，此时的队列是LinkedBlockingQueue，
     * 此时又来了个B任务，两个核心线程没有执行完当前任务，A任务在队列中等待，队列已满。所以根据如果无法将请求加入队列，则创建新的线程，
     * B任务被新创建的线程所执行，此时又来个C任务，此时maximumPoolSize已满，队列已满，只能执行异常策略(RejectedExecutionException)。
     */
    public void linkBlockingQueueUse() {
        ThreadPoolExecutor pool = buildByArgs(2, 3, new LinkedBlockingQueue<>(1));
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + " 执行 - " + j);
                }
                System.out.println("run");
            });
        }
        pool.shutdown();

    }


    /**
     * 【3】 有界队列 如ArrayBlockingQueue,ArrayBlockingQueue 是一个内部使用数组实现的有界队列，一旦创建后，容量不可变
     * 操作模式跟LinkedBlockingQueue查不多，只不过必须为其设置容量,所以叫有界队列。
     * new ArrayBlockingQueue<Runnable>(Integer.MAX_VALUE) 跟 new LinkedBlockingQueue(Integer.MAX_VALUE)效果一样。
     * LinkedBlockingQueue 底层是链表结构，ArrayBlockingQueue  底层是数组结构。
     * 你来一个我接一个，直到我容不下你了。FIFO，先进先出。
     */
    public void arrayBlockingQueueUse() {
        ThreadPoolExecutor pool = buildByArgs(2, 3, new ArrayBlockingQueue<>(1));
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + " 执行 - " + j);
                }
                System.out.println("run");
            });
        }
        pool.shutdown();
    }


}
