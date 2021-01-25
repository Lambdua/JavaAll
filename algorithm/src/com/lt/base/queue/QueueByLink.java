package com.lt.base.queue;

import java.util.Iterator;

/**
 * @author liangtao
 * @description 队列的链表结构实现
 * @Date 2021/1/25
 **/
public class QueueByLink<Item> implements Queue<Item> {

    Node head;
    Node last;
    int N;

    private class Node {
        Item item;
        Node next;
    }

    @Override
    public void enqueue(Item item) {
        Node old = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) head = last;
        else old.next = last;
        N++;
    }

    @Override
    public Item dequeue() {
        Item item = head.item;
        head = head.next;
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node n = head;

            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public Item next() {
                Item item = n.item;
                n = n.next;
                return item;
            }
        };
    }
}
