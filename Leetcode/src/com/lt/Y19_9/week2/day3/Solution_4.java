package com.lt.Y19_9.week2.day3;

import java.util.Stack;

/**
 * @author liangtao
 * @Date 2019/9/25
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * <p>
 * 思考:
 * 可以先计算中位数索引所在位置
 * 循环遍历两个数组,当索引到达时获取,计算
 * 时间复杂度: O(mn/2)
 **/
public class Solution_4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;

        //计算中位数索引位置
        int sum = nums1.length + nums2.length;
        boolean isOne = sum % 2 != 0;
        int index = isOne ? sum / 2 : sum / 2 - 1;

        int n1Index = 0;
        int n2Index = 0;
        boolean n1isNull=false;
        boolean n2isNull=false;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= index; i++) {
            //索引越界检查
            if (n1Index==nums1.length){
                //此时n1已经没有数
                n1isNull=true;
            }
            if (n2Index==nums2.length){
                n2isNull=true;
            }
            if (!n1isNull&&!n2isNull){
                if (nums1[n1Index] < nums2[n2Index]) {
                    stack.push(nums1[n1Index]);
                    n1Index++;
                }else {
                    stack.push(nums2[n2Index]);
                    n2Index++;
                }
            }

            if (n1isNull){
                stack.push(nums2[n2Index]);
                n2Index++;
            }
            if (n2isNull){
                stack.push(nums1[n1Index]);
                n1Index++;
            }

        }
        if (isOne){
            return stack.pop();
        }else {
            int temp=0;
            if (!n1isNull&&!n2isNull){
                 temp=nums1[n1Index]<nums2[n2Index]?nums1[n1Index]:nums2[n2Index];
            }
            if (n1isNull){
                temp=nums2[n2Index];
            }
            if (n2isNull){
                temp=nums1[n1Index];
            }

            return ((double)(temp+stack.pop()))/2;
        }

    }

    public static void main(String[] args) {
        int n1[]=new int[]{1,2,3,4,5,6};
        int n2[]=new int[]{-1,0};
        double medianSortedArrays = findMedianSortedArrays(n1, n2);
        System.out.println(medianSortedArrays);

    }
}
