package com.lt.sort;

import java.util.Arrays;

/**
 * @author liangtao
 * @description 自顶向下的归并
 * @date 2021年01月21 10:04
 **/
public class MergeImpl1<T extends Comparable<T>> extends SortBase<T> {

    @Override
    public void sort(T[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public void merge(T[] a, int lo, int mid, int hi) {
        if (less(a[mid], a[mid + 1])) return;
        int i = 0;
        int j = mid - lo + 1;
        //这里其实不用这么复杂每次都复制，我们只在开始的sort方法中复制一个aux，供后面的每次使用即可
        T[] aux = Arrays.copyOfRange(a, lo, hi + 1);
        for (int l = lo; l <= hi; l++) {
            if (i + lo > mid) a[l] = aux[j++];
            else if (j + lo > hi) a[l] = aux[i++];
            else if (less(aux[i], aux[j])) a[l] = aux[i++];
            else a[l] = aux[j++];
        }
    }

}
