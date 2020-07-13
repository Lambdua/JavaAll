package com.lt.Y19_9.week3.day1;

/**
 * @author liangtao
 * @Date 2019/9/30
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 **/
public class Solution_9 {
    public boolean isPalindrome(int x) {
        if(x<0) {
            return false;
        }

        String xstr=x+"";
        int i=0,j=xstr.length()-1;
        for(;i<xstr.length()/2;){
            if (xstr.charAt(i++)!=xstr.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution_9 solution_9 = new Solution_9();
        System.out.println(solution_9.isPalindrome(123421));
    }

}
