package com.lt.A_各种锁实现.可重入锁;

import com.lt.Z_common.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static com.lt.Z_common.constants.CompletableFutureConstant.POOL;

/**
 * @author liangtao
 * @description 可重入锁简单实现
 * @date 2021年07月08 14:49
 **/
public class ReentrantLockSimpleImpl implements Lock {

    private volatile boolean isLock;

    private int lockCounter;

    private Thread currentThread = null;


    @Override
    public void lock() {
        synchronized (this) {
            Thread callingThread = Thread.currentThread();
            while (isLock && !callingThread.equals(currentThread)) {
                //当上锁了且不是获得锁的线程尝试获取锁时
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLock = true;
            lockCounter++;
            currentThread = callingThread;
            System.out.println("当前线程: " + callingThread.getName() + "当前重入次数: " + lockCounter + ".");
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long start = System.currentTimeMillis();
        synchronized (this) {
            while (isLock && !Thread.currentThread().equals(currentThread)) {
                long remainTime = unit.toMillis(time - (System.currentTimeMillis() - start));
                if (remainTime <= 0) return false;
                this.wait(remainTime);
            }
            isLock = true;
            lockCounter++;
            currentThread = Thread.currentThread();
            return isLock;
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (Thread.currentThread().equals(currentThread)) {
                this.lockCounter--;
                if (lockCounter == 0) {
                    this.isLock = false;
                    notify();
                }
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        ReentrantLockUse reentrantLockUse = new ReentrantLockUse();
        Runnable runnable = reentrantLockUse::A;
        /*
        下面三个任务并发执行，但是执行顺序却是同步的，因为在 ReentrantLockUse 里面添加了锁
         */
        POOL.submit(runnable);
        POOL.submit(runnable);
        POOL.submit(runnable);
        POOL.shutdown();
        System.out.println("主线程结束");
    }

    private static class ReentrantLockUse {
        Lock lock = new ReentrantLockSimpleImpl();

        /**
         * A方法内部调用了加锁的B方法
         */
        public void A() {
            lock.lock();
            try {
                B();
                System.out.println(Thread.currentThread().getName() + "--> A");
                ThreadUtils.sleep(3000L);
            } finally {
                lock.unlock();
            }
        }

        public void B() {
            lock.lock();
            try {
                ThreadUtils.sleep(2000L);
                System.out.println(Thread.currentThread().getName() + "--> B");
            } finally {
                lock.unlock();
            }
        }
    }
}
