package com.lt.sort;

/**
 * @author liangtao
 * @description 希尔排序
 * @Date 2021/1/20
 **/
public class Shell<T extends Comparable<T>> extends Example<T> {
    @Override
    public void sort(T[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h *= 3 + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h /= 3;
        }
        isSorted(a);
    }
}
