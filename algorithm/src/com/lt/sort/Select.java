package com.lt.sort;

/**
 * @author liangtao
 * @description 选择排序实现
 * @date 2021年01月20 11:07
 **/
public class Select<T extends Comparable<T>> extends Example<T> {

    @Override
    public void sort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }
}
