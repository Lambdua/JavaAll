package com.lt.Y19_9.week1.day1;

/**
 * @author liangtao
 * @Date 2019/9/16
 * 合并两个有序升序的整数数组A和B变成一个新的数组。新数组也要有序
 * TODO
 **/
public class Solution5 {
    public static int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int[] sum = new int[A.length + B.length];

        int aindex = 0, bindex = 0;
            for (int i = 0; i < sum.length; i++) {

                if (aindex < A.length && bindex < B.length) {
                    sum[i] = A[aindex] > B[bindex] ? B[bindex++] : A[aindex++];
                } else if (aindex == A.length&&bindex<B.length) {
                    sum[i] =B[bindex++];
                }else if(bindex==B.length&&aindex<A.length){
                    sum[i]=A[aindex++];
                }
            }

        return sum;
    }



}
