package com.lt.base.queue;

/**
 * @author liangtao
 * @description 队列
 * @Date 2021/1/25
 **/
public interface Queue<Item> extends Iterable<Item> {
    void enqueue(Item item);

    Item dequeue();

    int size();

    boolean isEmpty();

}
