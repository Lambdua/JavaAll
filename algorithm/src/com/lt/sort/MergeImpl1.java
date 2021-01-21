package com.lt.sort;

import java.util.Arrays;

/**
 * @author liangtao
 * @description 自顶向下的归并
 * @date 2021年01月21 10:04
 **/
public class MergeImpl1<T extends Comparable<T>> extends Example<T> {

    @Override
    public void sort(T[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public void merge(T[] a, int lo, int mid, int hi) { // 将a[lo..mid] 和 a[mid+1..hi] 归并
        if (less(a[mid], a[mid + 1])) return;
        int i = 0, j = mid - lo+1;
        T[] aux = Arrays.copyOfRange(a, lo, hi + 1);
        for (int l = lo; l <= hi; l++) {
            if (i + lo > mid) a[l] = aux[j++];
            else if (j + lo > hi) a[l] = aux[i++];
            else if (less(aux[i], aux[j])) a[l] = aux[i++];
            else a[l] = aux[j++];
        }
    }

}
