package dataStructure.impl;

import dataStructure.IQueue;

import java.util.Iterator;

/**
 * 单链表实现的队列
 * 特点：先进先出
 *
 * @author liangtao
 * @Date 2020/5/24
 **/
public class Queue<T> implements IQueue<T> {
    public static void main(String[] args) {
        Queue<Integer> queue=new Queue();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
//            stack.push(i);
        }
        System.out.println("大小为:"+queue.size());
        for (Integer integer : queue) {
            System.out.print(integer+",");
        }
        System.out.println();
        System.out.println("dequeue后大小为:"+queue.size());
    }



    private int N;
    private Node first;
    private Node last;

    private class Node<T> {
        T item;
        Node next;
    }

    public Queue() {
        Node<T> emptyNode = new Node<>();
        this.first = emptyNode;
        this.last = emptyNode;
    }

    @Override
    public boolean isEmpty() {
        return 0 == N;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void enqueue(T item) {
        if (item == null) return;
        Node node = new Node<T>();
        node.item = item;
        first.next = node;
        first = node;
        N++;
    }

    @Override
    public T dequeue() {
        // 这是初始化的空节点
        if (last.item == null && last.next != null) last = last.next;
        if (last==null) return null;
        Node tempNode=last;
        last=last.next;
        N--;
        return (T) tempNode.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public T next() {
                return dequeue();
            }
        };
    }
}
