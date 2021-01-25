package com.lt.base;

import java.util.Iterator;

/**
 * @author liangtao
 * @description 栈基于数组
 * @date 2021年01月25 16:49
 **/
public class StackByArray<Item> implements Stack<Item> {
    private Item[] array;
    private int N = 0;

    public StackByArray() {
        array = (Item[]) new Object[10];
    }

    public StackByArray(int size) {
        array = (Item[]) new Object[size];
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < N;
            }

            @Override
            public Item next() {
                return array[i++];
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void push(Item item) {
        checkCapacity();
        array[N++] = item;
    }

    private void checkCapacity() {
        if (N == array.length) {
            resize(2 * N);
        }
    }

    @Override
    public Item pop() {
        Item item = array[--N];
        //避免对象游离
        array[N] = null;
        if (N > 0 && N == array.length / 4) resize(array.length / 2);
        return item;
    }

    private void resize(int size) {
        Item[] newArray = (Item[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, N);
        array = newArray;
    }
}
