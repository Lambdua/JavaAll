package com.lt.C_safe_cancle_thread;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 梁先生
 * @Date 2020/10/19
 * 最简单取消线程的方法
 * 自己设置一个标记， private volatile boolean cancelled;
 * 子任务轮询这个标记，在另一个线程中去改变这个标记，告知子任务结束。
 * 当子任务检测到标记改变时，就推出执行
 * 缺点：
 *  不过这种方法是有缺陷的，一旦正在执行的任务发生了阻塞，并且该阻塞状态一直没有解除，
 *  那么它将不再有机会判断取消标记，这样即使令 `cancelled = true` 了，需要被取消的线程也检测不到。
 **/
public class CancelThreadWay1 implements Runnable {
    public static ExecutorService pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), new ThreadPoolExecutor.DiscardOldestPolicy());
    private final List<BigInteger> primes=new ArrayList<>();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p=BigInteger.ONE;
        while (!cancelled){
            p=p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    // 另一个线程来中止当前任务
    public void cancel(){
        cancelled=true;
    }

    public synchronized List<BigInteger> get(){
        return Collections.unmodifiableList(primes);
    }

    public static void main(String[] args) throws InterruptedException {
        CancelThreadWay1 way1 = new CancelThreadWay1();
        pool.execute(way1);
        Thread.sleep(1000);
        way1.cancel();
        List<BigInteger> bigIntegers = way1.get();
        bigIntegers.stream().forEach(System.out::println);
    }
}
