package com.lt.base;

import java.util.Iterator;

/**
 * @author liangtao
 * @description 背包 背包是一种不支持从中删除元素的集合数据类型——它的
 * 目的就是帮助用例收集元素并迭代遍历所有收集到的元素（用
 * 例也可以检查背包是否为空或者获取背包中元素的数量）。迭
 * 代的顺序不确定且与用例无关。要理解背包的概念，可以想象
 * 一个非常喜欢收集弹子球的人。他将所有的弹子球都放在一个
 * 背包里，一次一个，并且会不时在所有的弹子球中寻找某一颗拥
 * 有某种特点的弹子球。使用 Bag 的 API，用例可以将元素添加进
 * 背包并根据需要随时使用 foreach 语句访问所有的元素。用例
 * 也可以使用栈或是队列，但使用 Bag 可以说明元素的处理顺序
 * 不重要。
 * @date 2021年01月22 14:06
 **/
public class Bag<Item> implements Iterable<Item> {
    private Node first;

    private class Node {
        Item item;
        Node next;
    }
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }
}
