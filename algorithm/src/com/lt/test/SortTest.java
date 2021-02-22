package com.lt.test;

import com.lt.sort.*;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.List;

/**
 * @author liangtao
 * @description 排序测试用例
 * @date 2021年01月20 13:22
 **/
public class SortTest {
    public static void main(String[] args) {
        //-12 -4 0 1 2 3 5 7 8 12 32
        List<SortBase<Integer>> list = Arrays.asList(new Select<>(), new Insert<>(), new Shell<>(), new MergeImpl2<>(), new Quick<>(),new HeapSort<>());
        for (SortBase<Integer> sort : list) {
            StdOut.print(sort.getClass().getSimpleName()+"\n");
            sort.testCorrectness(new Integer[]{3, 2, 1, 5, 7, 8, 32, 12, 0, -4, -12});
        }
    }
}
