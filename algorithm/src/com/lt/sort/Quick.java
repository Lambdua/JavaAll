package com.lt.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * @author liangtao
 * @description 快速排序, 对元素以一个基点位左右两侧进行大小划分打乱，此时当左右两侧分治后
 * 整个数组就会编程有序了，不像归并排序那样，对分组排序结束后还要在进行分组的合并操作
 * 其实归并实在排序之后合并，快排是在排序之前分组,两者对于分组的操作时机不同。
 * @date 2021年01月21 17:17
 **/
public class Quick<T extends Comparable<T>> extends Example<T> {
    @Override
    public void sort(T[] a) {
        StdRandom.shuffle(a);
        super.sort(a);
    }

    @Override
    public void sort(T[] a, int lo, int hi) {

    }


    /**
     * 切分函数,快排的核心
     **/
    private int partition(T[] a, int lo, int hi) {
        //我们这里直接取lo位切分点
        T point = a[lo];
        int left = lo, right = hi;

        while (true) {
            //从左边找到第一个大于切点的点
            while (less(a[++left], point)) if (left == hi) break;
            //从右边第一个小于切点的
            while (less(point, a[right--])) if (right == lo) break;

        }


    }
}
