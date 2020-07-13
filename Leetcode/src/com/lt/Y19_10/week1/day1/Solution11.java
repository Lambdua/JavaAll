package com.lt.Y19_10.week1.day1;

/**
 * @author liangtao
 * @Date 2019/10/8
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图见question_11.jpg
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 * 思考:
 *      暴力法:
 *          获取所有可以组成的容积,取最大值.
 *          时间复杂度:O(n^2);
 *
 *     面积表达式:
 *      area=(i2-i1)*min(a1,a2);
 *
 *     双指针法:
 *          用两个指针来标识数组的两端,首先可以获取宽度最大的图形
 *          然后在比较左指针和右指针的值,将较小值的指针向前或者向后一步,
 *          这是在确保宽度小一格的情况下,确保我们希望的面积是增大的方向.
 *          每一次都计算面积,存储最大值.
 *         时间复杂度: O(n)
 **/
public class Solution11 {
    public int maxArea(int[] height) {
        int maxArea=0;
        int leftIndex=0;
        int righrIndex=height.length-1;

        for (int i = 0; i < height.length-1; i++) {
            int tempArea=(righrIndex-leftIndex)*Math.min(height[leftIndex],height[righrIndex]);
            maxArea=tempArea>maxArea?tempArea:maxArea;
            if (height[leftIndex]<=height[righrIndex]){
                leftIndex++;
            }else {
                righrIndex--;
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        Solution11 solution11=new Solution11();
        int maxArea = solution11.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(maxArea);
    }
}
