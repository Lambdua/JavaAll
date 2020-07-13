package com.lt.Y19_9.week1.day5;

/**
 * @author liangtao
 * @Date 2019/9/20
 * 给定一个排序的整数数组（升序）和一个要查找的整数target，
 * 用O(logn)的时间查找到target第一次出现的下标（从0开始），如果target不存在于数组中，返回-1。
 *
 * 输入: [1, 2, 3, 3, 4, 5, 10]，3
 * 输出: 2
 *
 * 输入: [1, 2, 3, 3, 4, 5, 10]，6
 * 输出: -1
 **/
public class Solution1 {
    public static int binarySearch(int[] nums, int target) {
        int left=0;
        int right=nums.length;
        int middle;
        while (left<right){
           middle=(left+right)/2;
           if (target<=nums[middle]){
               right=middle;
           }else {
               left=middle+1;
           }
        }
        if (target==nums[left]) return left;
        else return -1;
    }

    public static void main(String[] args) {
        int nums[]=new int[]{1, 2, 3, 3, 4, 5, 10};
        System.out.println(binarySearch(nums, 6));
    }
}
