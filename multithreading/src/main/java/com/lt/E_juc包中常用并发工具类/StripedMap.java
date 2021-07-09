package com.lt.E_juc包中常用并发工具类;


/**
 * @author 梁先生
 * @Date 2020/10/13
 * 分段锁简单实现
 * {@link java.util.concurrent.ConcurrentHashMap}使用了分段锁，支持16个并发写入
 * - 使用了一个包含 16 个锁的数组，每个锁保护所有散列桶的 1/16，其中第 N 个散列桶由第（N % 16）个锁来保护；
 * - 这大约能把对于锁的请求减少到原来的 1/16，也是 ConcurrentHashMap 最多能支持 16 个线程同时写入的原因；
 * - 对于 ConcurrentHashMap 的 size() 操作，为了避免枚举每个元素，ConcurrentHashMap 为每个分段都维护了一个独立的计数，
 * 并通过每个分段的锁来维护这个值，而不是维护一个全局计数；
 **/
public class StripedMap {
    private final int N_LOCKS = 16;
    private Node[] buckets;
    private final Object[] locks;

    private static class Node {
        Node next;
        Object key;
        Object value;
    }

    public StripedMap(int bucketsNum) {
        buckets = new Node[bucketsNum];
        locks = new Object[N_LOCKS];
        for (Object lock : locks) {
            lock = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }


    public Object get(Object key) {
        int hash = hash(key);
        //分段加锁
        synchronized (locks[hash % N_LOCKS]) {
            for (Node bucket : buckets) {
                if (bucket.key.equals(key)) {
                    return bucket.value;
                }
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            int hash = hash(buckets[i].key);
            //分段加锁
            synchronized (locks[hash % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }
}
