package com.lt.saveCancleThread;

/**
 * @author 梁先生
 * @Date 2020/10/19
 * 中断的简单用例
 **/
public class InterruptSimpleUse {
    public static void main(String[] args) throws InterruptedException {
        InterruptSimpleUse entity = new InterruptSimpleUse();
//        entity.interruptUse();
//        entity.isInterruptedUse();
        entity.interruptedUse();
    }


    public Thread buildThread() {
        return new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "线程正在运行");
            }
        });
    }


    /**
     * 这里我们调用{@link Thread#interrupt()}方法并不会停止执行任务线程
     * 因为这个函数不会去真正意义上的打断一个正在运行的线程，而是修改这个线程的中断状态码（interrupt status）。
     * 同时，对于处在sleep()、wait()和join()方法阻塞下的线程，该方法会使线程抛出一个异常。
     * 对于可中断通道（NIO中的Channel）的阻塞状态，该方法会关闭通道，抛出异常。
     * 对于NIO中选择器的阻塞（Selector.selector），该方法等同于调用选择器的wakeup()方法。
     */
    public void interruptUse() {
        Thread task = buildThread();
        task.start();
        task.interrupt();
        System.out.println(Thread.currentThread().getName() + "执行");
    }


    /**
     * {@link Thread#isInterrupted()}  会返回当前线程的中断标志位
     */
    public void isInterruptedUse() {
        Thread thread = buildThread();
        thread.start();
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    /**
     * {@link Thread#interrupted()} 返回当前线程的中断标志位，同时重置中断标志位
     * 该方法是一个Thread类的静态方法，调用时会返回当前线程的中断状态码。
     * 内部调用：  return currentThread().isInterrupted(true); 这个内部方法参数为true则进行重置
     */
    public void interruptedUse() {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    System.err.println("线程中断标志位为true");
                }
            }
        });
        System.out.println(thread.isInterrupted());
        thread.start();
        thread.interrupt();
        while (thread.isInterrupted()){
            System.out.println("任务线程还是true");
        }
        System.out.println(thread.isInterrupted());
    }

}

