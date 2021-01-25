package com.lt.base;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * @author liangtao
 * @description 栈基于数组
 * @date 2021年01月25 16:49
 **/
public class StackByArray<Item> implements Stack<Item> {
    private Item[] array;
    private int N = 0;
    private int left = 0;
    private Class<Item> type;

    public StackByArray() {
        array = (Item[]) Array.newInstance(type, 10);
    }

    public StackByArray(Class<Item> type, int size) {
        this.type = type;
        array = (Item[]) Array.newInstance(type, size);
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
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
        array[N++] = item;
    }

    @Override
    public Item pop() {
        return array[N--];
    }

    private void expansion() {
        Item[] newArray = (Item[]) Array.newInstance(type, array.length * 2);
        System.arraycopy(array, 0, newArray, 0, N);
        array = newArray;
    }
}
