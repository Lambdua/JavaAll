//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 4:
//
// Related Topics 动态规划
package com.lt.easy;

/**
 * @author liangtao
 * @Date 2020/7/3
 **/
public class e70爬楼梯 {
    private int[] all;
    public int climbStairs(int n) {
        all=new int[n+1];
        return recursive(n);
    }
    public int recursive(int n){
        if (n==1) return 1;
        if (n==2) return 2;
        if (all[n]!=0 )return all[n];
        else all[n]=recursive(n-1)+recursive(n-2);
        return all[n];
    }
    public static void main(String[] args){
        new e70爬楼梯().climbStairs(45);
    }

}
