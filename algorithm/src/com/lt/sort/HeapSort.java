package com.lt.sort;

/**
 * @author liangtao
 * @description 堆排序
 * @date 2021年02月02 11:19
 **/
public class HeapSort<T extends Comparable<T>> extends SortBase<T> {

    @Override
    public void sort(T[] a, int lo, int hi) {
        //总数
        int N = hi - lo + 1;

        for (int i = (N / 2 + lo) - 1; i >= lo; i--) {
            sink(a, i, N - 1);
        }

        while (N > 1) {
            exch(a, 0, --N);
            sink(a, 0, N);
        }
    }


    /**
     * 下沉
     *
     * @param lo 起始索引 包括
     * @param hi 终止索引 不包括
     */
    private void sink(T[] a, int lo, int hi) {
        //找到左子节点
        int j = (lo << 1);
        if (j > hi) return;
        //当前节点有右子节点且左节点小于右节点
        if (j < hi && less(a[j], a[j + 1])) j++;
        //如果子节点小于父节点 不进行替换
        if (less(a[j], a[lo])) return;
        //替换
        exch(a, j, lo);
        //递归下称
        sink(a, j, hi);
    }
}
