package com.lt.base.symbol;

import com.lt.base.stack.Stack;
import com.lt.base.stack.StackByArray;

import java.util.NoSuchElementException;

/**
 * @author liangtao
 * @description 红黑查找树
 * @date 2021年02月23 15:28
 **/
public class RedBlackBST<K extends Comparable<K>, V> implements OrderedST<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node head;


    private class Node {
        K key;
        V value;
        //由父节点指向该节点的链的颜色
        boolean color;
        Node left;
        Node right;
        int size;

        public Node(K key, V value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    private boolean isRed(Node cur) {
        return cur == null ? false : cur.color == RED;
    }


    /**
     * 左旋转
     **/
    private Node rotateLeft(Node cur) {
        Node x = cur.right;
        cur.right = x.left;
        x.left = cur;
        x.color = cur.color;
        cur.color = RED;
        x.size = cur.size;
        cur.size = 1 + size(cur.left) + size(cur.right);
        return x;
    }

    /**
     * 颜色转换，将左子节点和右子节点颜色由红->黑,同时更新指向自己的链接由黑->红
     *
     * @author liangtao
     * @date 2021/2/23
     **/
    private void flipColors(Node cur) {
        cur.color = RED;
        cur.left.color = BLACK;
        cur.right.color = BLACK;
    }


    private Node rotateRight(Node cur) {
        Node x = cur.left;
        cur.left = x.right;
        x.right = cur;
        x.color = cur.color;
        cur.color = RED;
        x.size = cur.size;
        cur.size = 1 + size(cur.left) + size(cur.right);
        return x;
    }


    @Override
    public K min() {
        if (isEmpty()) throw new NoSuchElementException("用空符号表调用min（）");
        Node cur = head;
        while (cur.left != null) cur = cur.left;
        return cur.key;
    }

    @Override
    public K max() {
        if (isEmpty()) throw new NoSuchElementException("用空符号表调用max（）");
        Node cur = head;
        while (cur.right != null) cur = cur.right;
        return cur.key;
    }

    /**
     * 获取小于等于K的最大键
     */
    @Override
    public K floor(K key) {
        if (key == null) throw new IllegalArgumentException("floor（）的参数为null");
        if (isEmpty()) throw new NoSuchElementException("用空符号表调用floor（）");
        Node cur = head;
        while (cur != null) {
            int cmp = cur.key.compareTo(key);
            if (cmp > 0) cur = cur.left;
            else if (cmp == 0) return key;
            else {
                if (cur.right != null && cur.right.key.compareTo(key) <= 0) cur = cur.right;
                else return cur.key;
            }
        }
        throw new NoSuchElementException("floor（）的参数太小");
    }

    /**
     * 获取大于等于Key的最小键
     **/
    @Override
    public K ceiling(K key) {
        if (key == null) throw new IllegalArgumentException("ceiling（）的参数为null");
        if (isEmpty()) throw new NoSuchElementException("用空符号表调用ceiling（）");
        Node cur = head;
        while (cur != null) {
            int cmp = cur.key.compareTo(key);
            if (cmp < 0) cur = cur.right;
            else if (cmp == 0) return key;
            else {
                if (cur.left != null && cur.left.key.compareTo(key) >= 0) cur = cur.left;
                else return cur.key;
            }
        }
        throw new NoSuchElementException("ceiling（）的参数太小");
    }

    /**
     * 小于key的键的数量
     */
    @Override
    public int rank(K key) {
        if (key == null) throw new IllegalArgumentException("rank（）的参数为null");
        if (isEmpty()) throw new NoSuchElementException("用空符号表调用rank（）");
        Node cur = head;
        int total = 0;
        while (cur != null) {
            int cmp = cur.key.compareTo(key);
            if (cmp > 0) cur = cur.left;
            else if (cmp == 0) return size(cur.left);
            else {
                total += size(cur.left) + 1;
                if (cur.right != null && cur.right.key.compareTo(key) < 0) {
                    cur = cur.right;
                } else {
                    break;
                }
            }
        }
        return total;
    }

    /**
     * 获取排名位k的键
     */
    @Override
    public K select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("select（）的参数无效：" + k);
        }
        Node cur = head;
        while (cur != null) {
            int leftSize = size(cur.left);
            if (leftSize > k) cur = cur.left;
            else if (leftSize < k) {
                k -= leftSize + 1;
                cur = cur.right;
            } else return cur.key;
        }
        throw new RuntimeException("未知异常");
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        Stack<K> stack = new StackByArray<>();
        preorder(stack, head, lo, hi);
        return stack;
    }

    /**
     * 前序遍历
     *
     * @author liangtao
     * @date 2021/2/23
     **/
    private void preorder(Stack<K> stack, Node node, K lo, K hi) {
        if (node == null) return;
        if (node.key.compareTo(lo) < 0 || node.key.compareTo(hi) > 0) return;
        preorder(stack, node.left, lo, hi);
        stack.push(node.key);
        preorder(stack, node.right, lo, hi);
    }


    @Override
    public void put(K key, V value) {
        put(head, key, value);
    }

    /**
     * 只要谨慎地使用左旋转、右旋转和颜 色转换这三种简单的操作，我们就能够保证插入操作后红黑树和 2-3 树的一一对应关系。
     * 在沿着插入点到根结点的路径向上移动时在所经过的每个结点中顺序完成以下操作，我们就能完成插入操作：
     * 1. 如果右子结点是红色的而左子结点是黑色的，进行左旋转；
     * 2. 如果左子结点是红色的且它的左子结点也是红色的，进行右旋转； 
     * 3. 如果左右子结点均为红色，进行颜色转换。
     *
     * @author liangtao
     * @date 2021/2/25
     **/
    private Node put(Node cur, K key, V value) {
        //标准的插入操作使用红链接相连
        if (cur == null) return new Node(key, value, 1, RED);
        int cmp = cur.key.compareTo(key);
        if (cmp > 0) put(cur.left, key, value);
        else if (cmp < 0) put(cur.right, key, value);
        else cur.value = value;

        //旋转和变色,下列的三个判断顺序不能改变
        // 1. 如果右子结点是红色的而左子结点是黑色的，进行左旋转；
        if (isRed(cur.right) && !isRed(cur.left)) cur = rotateLeft(cur);
        // 2. 如果左子结点是红色的且它的左子结点也是红色的，进行右旋转； 
        //这里第一个判断是false就不会在走第二个cur.left.left 所以不用进行空校验
        if (isRed(cur.left) && isRed(cur.left.left)) cur = rotateRight(cur);
        // 3. 如果左右子结点均为红色，进行颜色转换。
        if (isRed(cur.left) && isRed(cur.right)) flipColors(cur);
        cur.size = 1 + size(cur.left) + size(cur.right);
        return cur;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("get（）的参数为null");
        Node cur = head;
        while (cur != null) {
            int cmp = cur.key.compareTo(key);
            if (cmp > 0) cur = cur.left;
            else if (cmp < 0) cur = cur.right;
            else return cur.value;
        }
        return null;
    }

    @Override
    public int size() {
        return size(head);
    }

    private int size(Node current) {
        return current == null ? 0 : current.size;
    }

}
