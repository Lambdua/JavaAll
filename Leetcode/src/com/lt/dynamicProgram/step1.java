package com.lt.dynamicProgram;

/**
 * @author liangtao
 * @Date 2020/7/10
 * 斐波那契数列
 **/
public class step1 {
    /**
     * 自顶向下
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        int[] mem = new int[n + 2];
        return recursiveFib(n + 1, mem);
    }

    public int recursiveFib(int n, int[] mem) {
        if (n == 2 || n == 1) return 1;
        if (mem[n] != 0) return mem[n];
        mem[n] = recursiveFib(n - 1, mem) + recursiveFib(n - 2, mem);
        return mem[n];
    }

    /**
     * 自下向上
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        step1 entity = new step1();
        System.out.println(entity.fib(6));
        System.out.println(entity.fib2(6));
    }
}
