//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 示例:
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4],
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
//
//
// 进阶:
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
// Related Topics 数组 分治算法 动态规划

package com.lt.easy;

/**
 * @author liangtao
 * @Date 2020/7/3
 **/
public class e53最大子序和 {
    public static int maxSubArray(int[] nums) {
        int[] itemMax = new int[nums.length];
        itemMax[0] = nums[0];
        int max = itemMax[0];
        for (int i = 1; i < nums.length; i++) {
            itemMax[i] = Math.max(itemMax[i - 1] + nums[i], nums[i]);
            max = Math.max(max, itemMax[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        maxSubArray(new int[]{-1});
    }
}
