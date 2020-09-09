package com.lt.reactive.demo;

import com.lt.reactive.model.Person;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * @author 梁先生
 * @Date 2020/9/5
 * 基于Recator实现异步编程简单Demo
 * https://github.com/reactor/reactor
 **/
public class ReactorDemo {
    @Test
    public void simpleDemo() {
        List<Person> personList = Person.makePerson();

        Flux
                //转换列表为Flowable流对象
                .fromArray(personList.toArray(new Person[0]))
                .filter(p -> p.getAge() > 10)
                .map(Person::getHeight)
                //订阅输出
                .subscribe(System.out::println);
    }

    /**
     * 在Reactor中有两种反应式类型：Mono与Flux。
     * 其中Mono代表着0或1个元素的流对象,Flux代表含有0或N个元素的流对象。
     */

    /**
     * Reactor也是使用Schedulers来抽象统一API背后的并发调度线程池，其提供了几个可通过Schedulers访问的标准调度执行器。
     * ·Schedulers.elastic()：线程池中的线程是可以复用的，按需创建与空闲回收，该调度器适用于I/O密集型任务。
     * ·Schedulers.parallel()：含有固定个数的线程池，该调度器适用于计算密集型任务。
     * ·Schedulers.single()：单一线程来执行任务。
     * ·Schedulers.immediate()：立刻使用调用线程来执行。
     * ·Schedulers.fromExecutor()：将已有的Executor转换为Scheduler来执行任务。
     */

    /**
     * RxJava中当在反应式类型上施加observeOn操作后，其后续的操作将会在切换的线程上执行，而Reactor中则是使用publishOn来实现对等的功能。
     */
    @Test
    public void publishOnAndSchedulers() throws InterruptedException {
        long start = System.currentTimeMillis();

        Flux.just("hello", "world", "liangtao")
                .publishOn(Schedulers.single())
                .subscribe((str) -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " " + str);
                },Throwable::printStackTrace);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        Thread.currentThread().join();
    }


}
