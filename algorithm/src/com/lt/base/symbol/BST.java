package com.lt.base.symbol;

import com.lt.base.stack.Stack;
import com.lt.base.stack.StackByArray;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author liangtao
 * @description 二叉查找树,统一使用
 * @date 2021年02月20 17:10
 **/
public class BST<K extends Comparable<K>, V> implements OrderedST<K, V> {
    private Node head;

    private class Node {
        //当前节点下的所有节点数量，包括自己 叶子节点为1
        private int size;
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(int size, K key, V value) {
            this.size = size;
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void put(K key, V value) {
        head = put(head, key, value);
    }

    private Node put(Node current, K key, V value) {
        if (current == null) return new Node(1, key, value);
        int cmp = current.key.compareTo(key);
        if (cmp < 0) current.right = put(current.right, key, value);
        else if (cmp > 0) current.left = put(current.left, key, value);
        else current.value = value;
        //重新计算size大小
        current.size = size(current.left) + size(current.right) + 1;
        return current;
    }


    @Override
    public V get(K key) {
        return get(head, key);
    }

    private V get(Node current, K key) {
        if (current == null) return null;
        int cmp = current.key.compareTo(key);
        if (cmp > 0) return get(current.left, key);
        else if (cmp < 0) return get(current.right, key);
        else return current.value;
    }

    @Override
    public int size() {
        return size(head);
    }

    private int size(Node current) {
        return current == null ? 0 : current.size;
    }


    @Override
    public K min() {
        if (isEmpty()) return null;
        Node cur = head;
        while (cur.left != null) cur = cur.left;
        return cur.key;
    }

    @Override
    public K max() {
        if (isEmpty()) return null;
        Node cur = head;
        while (cur.right != null) cur = cur.right;
        return cur.key;
    }

    /**
     * 获取小于等于K的最大键
     */
    @Override
    public K floor(K key) {
        Node cur = head;
        int cmp = 1;
        while (cur != null && cmp > 0) {
            cmp = cur.key.compareTo(key);
            if (cmp > 0) cur = cur.left;
        }

        if (cur == null || cmp > 0) return null;
        else return cur.key;
    }

    /**
     * 获取大于等于Key的最小键
     **/
    @Override
    public K ceiling(K key) {
        Node cur = head;
        int cmp = -1;
        while (cur != null && cmp < 0) {
            cmp = cur.key.compareTo(key);
            if (cmp < 0) cur = cur.right;
        }
        if (cur == null || cmp < 0) return null;
        else return cur.key;
    }

    /**
     * 小于key的键的数量
     */
    @Override
    public int rank(K key) {
        Node cur = head;
        int cmp = 1;
        while (cur != null && cmp >= 0) {
            cmp = cur.key.compareTo(key);
            if (cmp >= 0) cur = cur.left;
        }
        if (cur == null || cmp >= 0) return 0;
        else return cur.size;
    }

    @Override
    public K select(int k) {
        return null;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        throw new UnsupportedOperationException();
    }
    private void preorder(Stack<K> stack,Node node){
        if (node==null) return;
        preorder(stack,node.left);
        stack.push(node.key);
        preorder(stack,node.right);
    }


    @Override
    public Iterable<K> keys() {
        Stack<K> kStack = new StackByArray<>();
        preorder(kStack,head);
        return kStack;
    }


    public boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(head, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, K min, K max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(head); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (K key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

}
