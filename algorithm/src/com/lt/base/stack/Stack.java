package com.lt.base.stack;

/**
 * @author liangtao
 * @description 栈接口
 * @date 2021年01月25 16:52
 **/

public interface Stack<I> extends Iterable<I> {
    boolean isEmpty();

    int size();

    void push(I item);

    I pop();
}
