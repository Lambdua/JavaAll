package com.lt.sort;

/**
 * @author liangtao
 * @description 插入排序
 * @date 2021年01月20 13:23
 * 总的来说，插入排序对于部分有序的数组十分高效，也很适合小规模数组。
 **/
public class Insert<T extends Comparable<T>> extends SortBase<T> {

    @Override
    public void sort(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }
}
