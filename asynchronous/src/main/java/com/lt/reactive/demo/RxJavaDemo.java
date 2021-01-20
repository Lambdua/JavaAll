package com.lt.reactive.demo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.lt.completableFuture.CompletableFutureConstant.POOL;

/**
 * @author 梁先生
 * @Date 2020/9/3
 * 更多API使用详见:  https://github.com/ReactiveX/RxJava RxJava还提供了一些比较高级的操作符，
 * 比如Window、Interval、Buffer、Defer等，以及回压等功能，
 * RxJava的部分使用样例
 **/
public class RxJavaDemo {


    public static String rpcCall(String ip, String param) {
        System.out.println(Thread.currentThread().getName() + " " + ip + " rpcCall:" + param);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return param;

    }

    public List<String> getIPList() {
        // 1.生成ip列表
        List<String> ipList = new ArrayList<String>();
        for (int i = 1; i <= 10; ++i) {
            ipList.add("192.168.0." + i);
        }
        return ipList;
    }

    @Test
    public void suquentialExecute() {
        // 1.生成ip列表
        List<String> ipList = getIPList();
        // 2.顺序调用
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                .map(v -> rpcCall(v, v))
                .subscribe(System.out::println);

        // 3.打印耗时
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void asyncExecute() throws InterruptedException {
        // 1.生成ip列表
        List<String> ipList = getIPList();

        // 2.顺序调用
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                .observeOn(Schedulers.io())//2.1切换到IO线程执行
                .map(v -> rpcCall(v, v))//2.2映射结果
                .subscribe(System.out::println);//2.3订阅

        // 3.打印耗时
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        Thread.currentThread().join();
    }


    /**
     * 默认情况下被观察对象与其上施加的操作符链的运行以及把运行结果通知给观察者对象使用的是调用subscribe方法所在的线程，
     * SubscribeOn操作符可以通过设置Scheduler来改变这个行为，让上面的操作切换到其他线程来执行。
     * ObserveOn操作符可以指定一个不同的Scheduler让被观察者对象使用其他线程来把结果通知给观察者对象，并执行观察者的回调函数。
     * 需要注意SubscribeOn这个操作符指定的是被观察者对象本身在哪个调度器上执行，而且和在流上的操作链中SubscribeOn的位置无关，
     * 并且整个调用链上调用多次时，只有第一次才有效。
     * 而ObservableOn则是指定观察者对象在哪个调度器上接收被观察者发来的通知，
     * 在操作符链上每当调用了ObservableOn这个操作符时都会进行线程的切换
     *
     * @throws InterruptedException
     */
    @Test
    public void subscribeOnUse() throws InterruptedException {
        long start = System.currentTimeMillis();
        Flowable.fromCallable(() -> {
            Thread.sleep(1000);
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("cost:" + (System.currentTimeMillis() - start));

        Thread.sleep(2000); // 等待流结束
    }


    /**
     *在RxJava中，操作运算符不能直接使用Threads或ExecutorServices进行异步处理，而需要使用Schedulers来抽象统一API背后的并发调度线程池。
     * RxJava提供了几个可通过Schedulers访问的标准调度执行器。
     * .Schedulers.computation()：在后台运行固定数量的专用线程来计算密集型工作。大多数异步操作符使用它作为其默认调度线程池。
     * ·Schedulers.io()：在动态变化的线程集合上运行类I/O或阻塞操作。
     * ·Schedulers.single()：以顺序和FIFO方式在单个线程上运行。
     * ·Schedulers.trampoline()：在其中一个参与线程中以顺序和FIFO方式运行，通常用于测试目的。
     * RxJava还可以让我们通过Schedulers.from（Executor）将现有的Executor（及其子类型，如ExecutorService）包装到Scheduler中
     */


    @Test
    public void flatMapAsync() {
        List<String> ipList = getIPList();
        // 并发调用
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                //使用flatMap方法把每个ip转换为一个Flowable对象
                .flatMap(ip ->
                        //Flowable.just将每个ip作为数据源使用just方法获取一个Flowable流对象
                        Flowable.just(ip)
                                //设置元素发射逻辑并使用IO线程来做,所以是非阻塞的
                                .subscribeOn(Schedulers.io())
                                //使用map操作符把ip对象转换为rpcCall的结果，由于上一步是非阻塞的，所以ipList中的所有ip执行rpc调用都是并发进行的。
                                .map(v -> rpcCall(v, v)))
                //阻塞等待所有的rpc并发执行完毕，然后顺序打印执行结果，需要注意的是阻塞的是main函数所在线程。
                .blockingSubscribe(System.out::println);
        //打印耗时
        System.out.println("cost:" + (System.currentTimeMillis() - start));

    }

    /**
     * RxJava虽然内置了io与computation类型的线程池来做同步转异步，但是其允许我们使用业务自定义的线程池来进行处理
     */
    @Test
    public void custExeututePool() {
        // 1.生成ip列表
        List<String> ipList = getIPList();
        // 2.并发调用
        long start = System.currentTimeMillis();
        Flowable<String> stringFlowable = Flowable.fromArray(ipList.toArray(new String[0]))
                .flatMap(ip ->
                        Flowable.just(ip)
                                //使用我们的自定义线程池
                                .subscribeOn(Schedulers.from(POOL))
                                .map(v -> rpcCall(v, v)));
        stringFlowable.blockingSubscribe(System.out::println);

        // 3.打印耗时
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        //4.关闭线程池,由于POOL_EXECUTOR内部默认创建的是用户线程，所以必须要调用代码4销毁用户线程，这样当前jvm才会正常退出。
        POOL.shutdown();
    }

    /**
     * RxJava提供了高级的延迟操作符defer操作
     */
    @Test
    public void deferOperation() {
        //原子计数器
        AtomicInteger count = new AtomicInteger();

        Observable
                //使用range产生了一个包含1到10的数据流
                .range(1, 10)
                //每当发射出一个元素后让计数器值增加1
                .doOnNext(ignored -> count.incrementAndGet())
                //则忽略发射出的元素
                .ignoreElements()
                //调用andThen并当原始流结束后开启一个Single的流,新的Single流中的元素就是当前计数器中的值
                .andThen(Single.just(count.get()))
                //订阅新的流，试图打印出原始流中的元素个数
                .subscribe(System.out::println);

        /**
         * 运行上面代码会输出0，这是因为Single.just（count.get()）是在数据流尚未运行还在编译时计算的。
         * 所以我们需要延迟Single.just(count.get())再执行
         * RxJava提供的defer操作符可以解决这个问题。
         */

        AtomicInteger count2 = new AtomicInteger();
        Observable.range(1, 10)
                .doOnNext(ignored -> count2.incrementAndGet())
                .ignoreElements()
                //Single.defer(()->Single.just(count.get()))，使得Single.just(count.get())方法不会在编译时执行，
                // 而是等到原始流结束后才会执行。
                .andThen(Single.defer(() -> Single.just(count2.get())))
                .subscribe(System.out::println);
    }
}
