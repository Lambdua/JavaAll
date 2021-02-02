package com.lt.base.pqueue;

/**
 * @author liangtao
 * @description 优先队列的抽象基类
 * @date 2021年01月22 13:40
 **/
public interface PrimaryQueueBase<Key extends Comparable<Key>> {

    void insert(Key key);

    Key max();

    Key delMax();

    Key min();

    Key delMin();

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();
}
