package com.lt.base.symbol;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author liangtao
 * @description 基于链表的符号表实现
 * @date 2021年02月06 13:44
 **/
public class SequentialSearchST<K extends Comparable<K>, V> implements ST<K, V> {
    /**
     * 节点数量
     */
    private int size = 0;
    /**
     * 链表表头
     */
    private Node first;

    //内部链表节点构造
    private class Node {
        private K key;
        private V val;
        private Node next;

        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }


    @Override
    public void put(K key, V value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                //命中旧节点，更新
                x.val = value;
                return;
            }
        }
        // 未命中，新建结点
        first = new Node(key, value, first);
        size++;
    }

    @Override
    public V get(K key) {
        for (Node node = first; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return node.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        return () -> new Iterator<K>() {
            Node h = first;

            @Override
            public boolean hasNext() {
                return h != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K k = h.key;
                h = h.next;
                return k;
            }
        };
    }
}
