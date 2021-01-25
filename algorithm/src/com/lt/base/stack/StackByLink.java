package com.lt.base.stack;

import java.util.Iterator;

/**
 * @author liangtao
 * @description 下压栈 链表实现
 * @date 2021年01月25 16:02
 **/
public class StackByLink<Item> implements Stack<Item> {
    private Node first;
    private int N;

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item = current.item;
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
        return N;
    }

    @Override
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    @Override
    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }


    private class Node {
        Item item;
        Node next;
    }
}

