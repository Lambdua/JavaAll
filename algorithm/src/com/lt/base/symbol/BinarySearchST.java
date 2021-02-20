package com.lt.base.symbol;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author liangtao
 * @description 有序二分查找符号表实现
 * 使用一对平行数组来存储符号表键值对，通过数组的索引来高效的实现{@link #get(Comparable)}操作。
 * 此实现核心在于 {@link #rank(Comparable)}方法，返回符号表中小于给定k的数量。对于 get() 方法，只要
 * 给定的键存在于表中，rank() 方法就能够精确地告诉我们在哪里能够找到它（如果找不到，那它
 * 肯定就不在表中了）。对于 put() 方法，只要给定的键存在于表中，rank() 方法就能够精确地告诉我们到哪里去更
 * 新它的值，以及当键不在表中时将键存储到表的何处。我们将所有更大的键向后移动一格来腾出位
 * 置（从后向前移动）并将给定的键值对分别插入到各自数组中的合适位置。
 * @date 2021年02月20 14:51
 **/
public class BinarySearchST<K extends Comparable<K>, V> implements OrderedST<K, V> {
    K[] keys;
    V[] values;
    private int N;

    public BinarySearchST() {
        this(10);
    }

    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }

    @Override
    public K min() {
        if (isEmpty()) return null;
        return keys[0];
    }

    @Override
    public V delete(K key) {
        if (!contains(key)) {
            return null;
        }
        int i = rank(key);
        V v = values[i];
        for (int j = i; j < N; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        if (N > 0 && N == keys.length >> 2) resize(N >> 1);
        return v;
    }

    @Override
    public K max() {
        if (isEmpty()) return null;
        return keys[N - 1];
    }

    @Override
    public K floor(K key) {
        int i = rank(key);
        if (i-1>=0){
            return keys[i-1];
        }else {
            return null;
        }
    }

    @Override
    public K ceiling(K key) {
        return keys[rank(key)];
    }

    /**
     * 基于数组的特性，我们可以使用二分查找发快速查找小于key的键索引位置
     */
    @Override
    public int rank(K key) {
        int left = 0, right = N - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int cmp = keys[mid].compareTo(key);
            if (cmp < 0) left = mid + 1;
            else if (cmp > 0) right = mid - 1;
            else return mid;
        }
        return left;
    }

    @Override
    public K select(int k) {
        return keys[k];
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return () -> new Iterator<K>() {
            int left = rank(lo);
            int right = rank(hi);

            @Override
            public boolean hasNext() {
                return left <= right;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return keys[left++];
            }
        };
    }

    @Override
    public void put(K key, V value) {
        //1. 查找键，找到直接更新返回
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        checkCapacity();
        //2. 没找到，从i开始 后续所有键后移一位
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        //3. 插入
        keys[i] = key;
        values[i] = value;
        N++;
    }

    @Override
    public V get(K key) {
        if (isEmpty()) return null;
        //找到k的索引位置
        int i = rank(key);
        //k索引不越界且等于keys[i]
        if (i < N && keys[i].compareTo(key) == 0) return values[i];
        else return null;
    }

    @Override
    public int size() {
        return N;
    }

    private void checkCapacity() {
        if (N == keys.length) {
            resize(N << 1);
        }
    }

    private void resize(int size) {
        K[] kArray = (K[]) new Comparable[size];
        System.arraycopy(keys, 0, kArray, 0, N);
        keys = kArray;
        V[] vArray = (V[]) new Object[size];
        System.arraycopy(values, 0, vArray, 0, N);
        values = vArray;
    }

}
