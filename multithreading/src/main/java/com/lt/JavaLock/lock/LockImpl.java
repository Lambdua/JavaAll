package com.lt.JavaLock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author 梁先生
 * @Date 2020/10/20
 **/
public class LockImpl implements Lock {
    @Override
    public void lock() {
        Thread thread = new Thread();
        thread.interrupt();
//        thread.isInterrupted()
//        Thread.interrupted()

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
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
