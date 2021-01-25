package com.lt.base;

/**
 * @author liangtao
 * @description 栈接口
 * @date 2021年01月25 16:52
 **/

public interface Stack<Item> extends Iterable<Item> {
    boolean isEmpty();

    int size();

    void push(Item item);

    Item pop();
}
