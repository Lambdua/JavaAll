package com.lt.base.symbol;

/**
 * @author liangtao
 * @description 二叉查找树
 * @date 2021年02月20 17:10
 **/
public class BST<K extends Comparable<K>, V> implements ST<K, V> {
    Node head;

    private class Node {
        private int N;
        private K k;
        private V v;
        private Node left;
        private Node right;

        public Node(int n, K k, V v) {
            N = n;
            this.k = k;
            this.v = v;
        }
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }
}
