package com.lt.base.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author liangtao
 * @description 栈基于数组
 * @date 2021年01月25 16:49
 **/
public class StackByArray<I> implements Stack<I> {
    private I[] array;
    private int size = 0;

    public StackByArray() {
        array = (I[]) new Object[10];
    }

    public StackByArray(int size) {
        array = (I[]) new Object[size];
    }

    @Override
    public Iterator<I> iterator() {
        return new Iterator<I>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public I next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                return array[i++];
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(I item) {
        checkCapacity();
        array[size++] = item;
    }

    private void checkCapacity() {
        if (size == array.length) {
            resize(size << 1);
        }
    }

    @Override
    public I pop() {
        I item = array[--size];
        //避免对象游离
        array[size] = null;
        if (size > 0 && size == array.length >> 2) resize(array.length >> 1);
        return item;
    }

    private void resize(int size) {
        I[] newArray = (I[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, this.size);
        array = newArray;
    }
}
