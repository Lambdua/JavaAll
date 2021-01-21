package com.lt.sort;

import java.util.Arrays;

/**
 * @author liangtao
 * @description 自底向上归并
 * @date 2021年01月21 15:19
 **/
public class MergeImpl2<T extends Comparable<T>> extends Example<T> {
    @Override
    public void sort(T[] a, int lo, int hi) {
        for (int i = 1; i <= hi; i += i) {
            for (int j = lo; j <= hi - i; j += i + i) {
                merge(a, j, j + i - 1, Math.min(j + i + i - 1, hi));
            }
        }
    }

    private void merge(T[] a, int lo, int mid, int hi) {
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
