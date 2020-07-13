//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//
// 你可以假设数组中无重复元素。
//
// 示例 1:
//
// 输入: [1,3,5,6], 5
//输出: 2
//
//
// 示例 2:
//
// 输入: [1,3,5,6], 2
//输出: 1
//
//
// 示例 3:
//
// 输入: [1,3,5,6], 7
//输出: 4
//
//
// 示例 4:
//
// 输入: [1,3,5,6], 0
//输出: 0
//
// Related Topics 数组 二分查找

package com.lt.easy;

/**
 * @author liangtao
 * @Date 2020/7/2
 **/
public class e35搜索插入位置 {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) return middle;
            else if (nums[middle] > target) right = middle;
            else if (nums[middle] < target) left = middle + 1;
        }
        return left;
    }

    // 1,2,3,5,6
    public static void main(String[] args) {
        e35搜索插入位置 entity = new e35搜索插入位置();
        int i = entity.searchInsert(new int[]{1,2,3,5,7,8,12}, 0);
        System.out.println(i);
    }
}
