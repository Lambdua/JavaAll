package com.lt.base.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author liangtao
 * @description 下压栈 链表实现
 * @date 2021年01月25 16:02
 **/
public class StackByLink<I> implements Stack<I> {
    private Node first;
    private int size;

    @Override
    public Iterator<I> iterator() {
        return new Iterator<I>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public I next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                I item = current.item;
                current = current.next;
                return item;
            }
        };
    }


    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(I item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    @Override
    public I pop() {
        I item = first.item;
        first = first.next;
        size--;
        return item;
    }


    private class Node {
        I item;
        Node next;
    }
}

