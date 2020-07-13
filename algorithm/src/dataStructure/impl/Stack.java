package dataStructure.impl;

import dataStructure.IStack;

import java.util.Iterator;

/**
 * 下压堆栈，使用链表进行实现
 * 特性：先进后出，提供api：push,pop
 *
 * @author liangtao
 * @Date 2020/5/24
 **/
public class Stack<T> implements IStack<T> {

    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        System.out.println("大小为:"+stack.size());
        for (Integer integer : stack) {
            System.out.print(integer+",");
        }
        System.out.println();
        System.out.println("pop后大小为:"+stack.size());
    }
    /**
     * 数量
     */
    private int N=0;
    private Node head;


    private class Node<T> {
        T item;
        Node next;
    }

    @Override
    public boolean isEmpty() {
        return 0==N;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void push(T item) {
        Node<T> itemNode = new Node<>();
        itemNode.item=item;
        itemNode.next=head;
        head=itemNode;
        N++;
    }

    @Override
    public T pop() {
        if(isEmpty()) return null;
        Node itemNode=head;
        head=head.next;
        N--;
        return (T) itemNode.item;

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
                return pop();
            }
        };
    }
}
