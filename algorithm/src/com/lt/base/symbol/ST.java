package com.lt.base.symbol;

import java.security.Key;

/**
 * @author liangtao
 * @description 符号表抽象接口
 * @date 2021年02月05 13:20
 **/
public interface ST<K, V>{
    void put(K key, V value);

    V get(K key);

    int size();


    default V delete(K key) {
        V value = get(key);
        put(key, null);
        return value;
    }

    default boolean contains(K key) {
        return get(key) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }


    Iterable<Key> keys();
}
