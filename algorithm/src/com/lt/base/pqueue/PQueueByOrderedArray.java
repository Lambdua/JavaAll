package com.lt.base.pqueue;

import com.lt.base.stack.Stack;
import com.lt.base.stack.StackByArray;

/**
 * @author liangtao
 * @description 有序数组实现优先队列
 * todo  没有实现,不记得什么时候写的这个东东了。放这里吧
 * @date 2021年02月01 09:31
 **/
public class PQueueByOrderedArray<K extends Comparable<K>> implements PrimaryQueueBase<K> {
    private Stack<K> innerStack;

    public PQueueByOrderedArray() {
        this.innerStack = new StackByArray<>();
    }

    @Override
    public void insert(K key) {

    }

    @Override
    public K max() {
        return null;
    }

    @Override
    public K delMax() {
        return innerStack.pop();
    }

    @Override
    public K min() {
        return null;
    }

    @Override
    public K delMin() {
        return null;
    }

    @Override
    public int size() {
        return innerStack.size();
    }

}
