package com.lt.Y19_9.week2.day5;

/**
 * @author liangtao
 * @Date 2019/9/27
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 注意:
 *  假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
 *  请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 *
 *
 **/
public class Solution_7 {

    public static void main(String[] args) {
        Solution_7 solution_7 = new Solution_7();
        solution_7.reverse(128);
    }
    public int reverse(int x) {
        //当前计算结果
        int ans = 0;
        while (x != 0) {
            //下一位计算结果
            int pop = x % 10;
            //如果当前的结果>最大值 ,或者=最大值的情况下,下一位结果大于7(127的个为数),则溢出
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            //如果当前的结果<最小值 ,或者=最小值的情况下,下一位结果小于-8(-128的个为数),则溢出
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }
}
