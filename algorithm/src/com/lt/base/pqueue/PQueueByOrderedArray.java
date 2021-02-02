package com.lt.base.pqueue;

import com.lt.base.stack.Stack;
import com.lt.base.stack.StackByArray;

/**
 * @author liangtao
 * @description 有序数组实现优先队列
 * @date 2021年02月01 09:31
 **/
public class PQueueByOrderedArray<key extends Comparable<key>> implements PrimaryQueueBase<key> {
    private Stack<key> innerStack;

    public PQueueByOrderedArray() {
        this.innerStack = new StackByArray<>();
    }

    @Override
    public void insert(key key) {

    }

    @Override
    public key max() {
        return null;
    }

    @Override
    public key delMax() {
        return innerStack.pop();
    }

    @Override
    public key min() {
        return null;
    }

    @Override
    public key delMin() {
        return null;
    }

    @Override
    public int size() {
        return innerStack.size();
    }

}
