package com.lt.base.symbol;

import com.lt.base.stack.Stack;
import com.lt.base.stack.StackByArray;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * @author liangtao
 * @description 二叉查找树, 统一使用
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
        if (isEmpty()) throw new NoSuchElementException();
        return min(head).key;
    }

    private Node min(Node cur) {
        if (cur.left == null) return cur;
        else return min(cur.left);
    }

    @Override
    public K max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(head).key;
    }

    private Node max(Node cur) {
        if (cur.right == null) return cur;
        else return max(cur.right);

    }

    /**
     * 获取小于等于K的最大键
     */
    @Override
    public K floor(K key) {
        if (key == null) throw new IllegalArgumentException("floor（）的参数为null");
        if (isEmpty()) throw new NoSuchElementException("使用了空符号表调用floor（）");
        Node result = floor(head, key);
        if (result == null) throw new NoSuchElementException("floor（）的参数太小");
        else return result.key;
    }

    private Node floor(Node cur, K key) {
        if (cur == null) return null;
        int cmp = cur.key.compareTo(key);
        if (cmp == 0) return cur;
        else if (cmp > 0) return floor(cur.left, key);
            //小于0还要在比较右侧是否也小于
        else {
            Node rResult = floor(cur.right, key);
            if (rResult != null) return rResult;
            else return cur;
        }
    }

    /**
     * 获取大于等于Key的最小键
     **/
    @Override
    public K ceiling(K key) {
        if (key == null) throw new IllegalArgumentException("ceiling（）的参数为null");
        if (isEmpty()) throw new NoSuchElementException("使用了空符号表调用 ceiling（）");
        Node result = ceiling(head, key);
        if (result == null) throw new NoSuchElementException("ceiling() 的参数太大");
        else return result.key;
    }

    private Node ceiling(Node cur, K key) {
        if (cur == null) return null;
        int cmp = cur.key.compareTo(key);
        if (cmp == 0) return cur;
        else if (cmp < 0) return ceiling(cur.right, key);
            //大于0， 看看左节点是否也大于0
        else {
            Node lResult = ceiling(cur.left, key);
            if (lResult != null) return lResult;
            else return cur;
        }
    }

    /**
     * 小于key的键的数量
     */
    @Override
    public int rank(K key) {
        if (key == null) throw new IllegalArgumentException("rank（）的参数为null");
        return rank(head, key);
    }


    private int rank(Node cur, K key) {
        if (cur == null) return 0;
        int cmp = cur.key.compareTo(key);
        if (cmp > 0) return rank(cur.left, key);
        else if (cmp < 0) return 1 + size(cur.left) + rank(cur.right,key);
        else return size(cur.left);
    }

    /**
     * 获取排名为k的键(从0开始)
     */
    @Override
    public K select(int k) {
        if (k > size() || k < 0) throw new IllegalArgumentException("排名参数过大或者过小");
        return select(head, k).key;
    }

    private Node select(Node cur, int k) {
        if (cur == null) return null;
        int leftSize = size(cur.left);
        if (k > leftSize) return select(cur.right, k - leftSize - 1);
        if (k < leftSize) return select(cur.left, k);
        else return cur;
    }


    @Override
    public V delete(K key) {
        if (key == null) throw new IllegalArgumentException("用空键调用delete（）");
        V result = get(key);
        head = delete(head, key);
        return result;
    }

    private Node delete(Node cur, K key) {
        if (cur == null) return null;
        int cmp = cur.key.compareTo(key);
        if (cmp > 0) delete(cur.left, key);
        else if (cmp < 0) delete(cur.right, key);
        else {
            if (cur.left == null) return cur.right;
            if (cur.right == null) return cur.left;
            //寻找后继节点
            Node t = cur;
            cur = min(cur.right);
            cur.right = delMin(cur.right);
            cur.left = t.left;
        }
        cur.size = size(cur.left) + size(cur.right) + 1;
        return cur;
    }


    @Override
    public V delMax() {
        if (isEmpty()) throw new NoSuchElementException();
        V result = max(head).value;
        head = delMax(head);
        return result;
    }

    private Node delMax(Node cur) {
        if (cur.right == null) return cur.left;
        cur.right = delMax(cur.right);
        cur.size = size(cur.left) + size(cur.right) + 1;
        return cur;
    }

    @Override
    public V delMin() {
        if (isEmpty()) throw new NoSuchElementException();
        V result = min(head).value;
        head = delMin(head);
        return result;
    }

    private Node delMin(Node cur) {
        if (cur.left == null) return cur.right;
        cur.left = delMin(cur.left);
        cur.size = size(cur.left) + size(cur.right) + 1;
        return cur;
    }


    @Override
    public Iterable<K> keys(K lo, K hi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<K> keys() {
        Stack<K> kStack = new StackByArray<>();
        preorder(kStack, head);
        return kStack;
    }

    private void preorder(Stack<K> stack, Node node) {
        if (node == null) return;
        preorder(stack, node.left);
        stack.push(node.key);
        preorder(stack, node.right);
    }

    public boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
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
    private boolean isSizeConsistent() {
        return isSizeConsistent(head);
    }

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
