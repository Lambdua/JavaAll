package com.lt.A_各种锁实现.简单锁实现;

import com.lt.Z_common.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author liangtao
 * @description
 * @date 2021年07月08 13:42
 **/
public class LockSimpleImpl implements Lock {
    private volatile boolean isLock = false;

    /**
     * 获取锁。如果当前锁不可用，则会被阻塞直至锁释放
     */
    @Override
    public void lock() {
        synchronized (this) {
            while (isLock) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLock = true;
        }

    }

    /**
     * 获取锁并允许被中断。这个方法和lock()类似，不同的是，它允许被中断并抛出中断异常。
     *
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 尝试获取锁。会立即返回结果，而不会被阻塞。
     */
    @Override
    public boolean tryLock() {
        return false;
    }

    /**
     * 尝试获取锁并等待一段时间。这个方法和{@link Lock#tryLock()}，但是它会根据参数等待–会，如果在规定的时间内未能获取到锁就会放弃；
     *
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long start = System.currentTimeMillis();
        synchronized (this) {
            while (isLock) {
                long remainTime = unit.toMillis(time-(System.currentTimeMillis() - start) );
                if (remainTime <= 0) return false;
                this.wait(remainTime);
            }
            isLock = true;
            return isLock;
        }
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        if (isLock) {
            synchronized (this) {
                if (isLock) {
                    isLock = false;
                    this.notify();
                }
            }
        }
    }

    /**
     * 返回绑定到此Lock实例的新Condition实例。
     * 在等待条件之前，当前线程必须持有锁。 调用Condition.await()将在等待之前自动释放锁，并在等待返回之前重新获取锁。
     * 实施注意事项 Condition实例的确切操作取决于Lock实现，并且必须由该实现记录。
     * todo
     */
    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        final WildMonster wildMonster = new WildMonster();
        String[] players = { "哪吒", "兰陵王", "铠", "典韦" };
        for (String player : players) {
            new Thread(() -> wildMonster.killWildMonster(), player).start();
        }
    }

    public static class WildMonster {
        private boolean isWildMonsterBeenKilled;
        // 创建锁对象
        private Lock lock = new LockSimpleImpl();

        public void killWildMonster() {
            // 获取锁
            String playerName = Thread.currentThread().getName();
            try {
                lock.lock();
                ThreadUtils.sleep(1000L);
                if (isWildMonsterBeenKilled) {
                    System.out.println(playerName + "未斩杀野怪失败...");
                    return;
                }
                isWildMonsterBeenKilled = true;
                System.out.println(playerName + "斩获野怪！");
            }finally {
                // 执行结束后，无论如何不要忘记释放锁
                lock.unlock();
            }
        }
    }
}
