package com.lt.F_线程间通讯;

import com.lt.Z_common.constants.CompletableFutureConstant;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liangtao
 * @description 两个线程交替执行，打印1-100之间的数。 while轮询/wait notify机制
 * @date 2021年07月06 14:35
 **/
public class ThreadCommunication {
    static ThreadPoolExecutor executor = CompletableFutureConstant.POOL;
    static volatile int i = 1;
    static final int MAX = 100;
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        implementMethod3();
    }

    /**
     * 通过标识符判断，这种方式会一直消耗cpu，不推荐
     */
    public  void implementMethod1() {
        Runnable runnable1 = () -> {
            String name = Thread.currentThread().getName();
            while (i <= MAX) {
                if (flag) {
                    System.out.println("线程： " + name + ": " + i++);
                    flag = !flag;
                }
            }
        };
        Runnable runnable2 = () -> {
            String name = Thread.currentThread().getName();
            while (i <= MAX) {
                if (!flag) {
                    System.out.println("线程： " + name + ": " + i++);
                    flag = !flag;
                }
            }
        };
        executor.execute(runnable1);
        executor.execute(runnable2);
        executor.shutdown();
    }

    /**
     * 通过通知等待方式来实现，主要是{@link Object#wait()} 和{@link Object#notify()}
     * wait():一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器（锁）。
     * notify():一旦执行此方法，就会唤醒被wait的一个线程,被唤醒的线程加入到可以获取锁的队列中
     * wait()，notify()，notifyAll()三个方法必须使用在同步代码块或同步方法中
     * wait()，notify()，notifyAll()三个方法的调用者必须是同步代码块或同步方法中的同步监视器（synchronized）。
     * 就是说：synchronized(对象）这个对象和wait，notify，notifyall调用的是同一个对象,否则，会出现IllegalMonitorStateException异常
     */
    public static void implementMethod3() {
        Runnable target=() -> {
            String name = Thread.currentThread().getName();
            while (true) {
                System.out.println(name+" 开始获取锁");
                synchronized (lock) {
                    System.out.println(name+" 获取到了锁");
                    System.out.println(name+" notify");
                    lock.notify();
                    if (i <= 100) {
                        System.out.println(name + ":" + i++);
                        try {
                            System.out.println(name+" 开始wait，释放锁");
                            lock.wait();
                            System.out.println(name+" 被唤醒");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        };
        new Thread(target,"奇数").start();
        new Thread(target,"偶数").start();
    }
}
