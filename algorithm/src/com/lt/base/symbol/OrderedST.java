package com.lt.base.symbol;

/**
 * @author liangtao
 * @description 有序的符号表抽象接口, 在基于有序的符号表中，我们可以有更多的拓展操作。
 * 例如获取符号表中的最大或者最小key，以及获取一个有界的数据段等，下面是针对有序符号表的一个
 * api拓展
 * @date 2021年02月05 15:23
 **/
public interface OrderedST<K extends Comparable<K>, V> extends ST<K, V> {

    K min();

    default V delMin() {
        return delete(min());
    }

    K max();

    default V delMax() {
        return delete(max());
    }

    /**
     * 获取[lo..hi]之间的键的数量
     */
    default int size(K lo, K hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        return rank(hi) - rank(lo);
    }

    /**
     * 获取小于等于K的最大键
     */
    K floor(K key);

    /**
     * 获取大于等于Key的最小键
     **/
    K ceiling(K key);

    /**
     * 小于key的键的数量
     */
    int rank(K key);
    /**
     *
     * 对于 0到size()-1的所有索引 i 都有一下两个特性：
     * 1. i==rank(select(i))
     * 2. k==select(rank(key))
     */
    /**
     * 获取排名位k的键
     */
    K select(int k);

    /**
     * 获取[lo...hi]之间迭代器
     */
    Iterable<K> keys(K lo, K hi);

    @Override
    default Iterable<K> keys() {
        return keys(min(), max());
    }
}
