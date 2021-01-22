package com.lt.sort;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author liangtao
 * @description 所有排序算法的基础框架, 全都采用升序进行排序
 * 将类的排序代码放在sort()方法中,不同的算法实现，自定义自己的sort方法
 * @date 2021年01月20 09:58
 **/
public abstract class SortBase<T extends Comparable<T>> {

    public abstract void sort(T[] a, int lo, int hi);

    public void sort(T[] a){
        sort(a,0,a.length-1);
    }

    /**
     * 元素a是否小于元素b
     *
     * @author liangtao
     * @date 2021/1/20
     **/
    protected boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 指定索引的两个元素进行位置交换
     *
     * @param a 排序数组
     * @param i 替换索引
     * @param j 替换索引
     * @author liangtao
     * @date 2021/1/20
     **/
    protected void exch(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    protected void show(T[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }


    public boolean isSorted(T[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (less(a[i + 1], a[i])) {
                StdOut.println("排序错误"+this.getClass().getName());
                return false;
            }
        }
        return true;
    }

    /**
     * 验证排序正确性和排序输出
     *
     * @param a 测试数据
     * @author liangtao
     * @date 2021/1/20
     **/
    public void testCorrectness(T[] a) {
        sort(a);
        isSorted(a);
        show(a);
    }
}
