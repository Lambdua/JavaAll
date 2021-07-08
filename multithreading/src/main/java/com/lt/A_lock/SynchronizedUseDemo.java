package com.lt.A_lock;

import com.lt.Z_common.ThreadUtils;
import org.junit.Test;

import static com.lt.Z_common.constants.CompletableFutureConstant.POOL;

/**
 * @author liangtao
 * @description synchronized关键字的使用场景,尽量不要使用String类型和原始类型作为synchronized参数。
 * @date 2021年07月07 11:29
 **/
public class SynchronizedUseDemo {
    /**
     * synchronized 标注在实例方法上,它表示当前方法每次能且仅能有一个线程访问,
     * 另外，由于当前方法是实例方法， 所以如果该对象存在多个实例的话，不同的实例可以由不同的线程访问， 它们之间并无协作关系。
     *
     * @author liangtao
     * @date 2021/7/7
     **/
    public synchronized void method1(){
        System.out.println("实例方法1");
        ThreadUtils.sleep(5000L);
        System.out.println("实例方法1睡眠结束");
    }

    public synchronized void method2(){
        System.out.println("实例方法2");
        ThreadUtils.sleep(20L);
    }

    /**
     * 与实例方法的synchronized不同，静态方法的synchronized是基于当前方法所属的类,而每个类在虚拟机上有且只有一个类对象。
     * 所以，对于同一类而言，每次有且只能有一个线程能访问静态synchronized方法。
     * 当类中包含有多个静态的synchronized方法时，每次也仍然有且只能有一个线程可以访问其中的方法。
     * @author liangtao
     * @date 2021/7/7
     **/
    public synchronized static void method3(){
        System.out.println("静态方法3");
    }


    /**
     * synchronized的并发限制取决于其参数,这里的参数是this,就是当前类的实例对象
     */
    public void method4(){
        synchronized (this){
            System.out.println("实例方法中使用this");
        }
    }

    /**
     * 静态方法中使用
     */
    public static void method5(){
        synchronized (SynchronizedUseDemo.class){
            System.out.println("静态方法中使用类对象");
        }
    }
    /**
     * 如果当前对象有两个synchronized方法,一次也只能执行一个方法，因为每个实例内的同步方法，能且仅能有一个线程访问
     */
    @Test
    public void test1() throws InterruptedException {
        SynchronizedUseDemo demo = new SynchronizedUseDemo();
        POOL.execute(()->demo.method1());
        POOL.execute(()->demo.method2());
        POOL.shutdown();
        Thread.currentThread().join();
    }
}
