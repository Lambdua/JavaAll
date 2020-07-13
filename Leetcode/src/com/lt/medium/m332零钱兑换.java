//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。
//
//
//
// 示例 1:
//
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3
//解释: 11 = 5 + 5 + 1
//
// 示例 2:
//
// 输入: coins = [2], amount = 3
//输出: -1
//
//
//
// 说明:
//你可以认为每种硬币的数量是无限的。
// Related Topics 动态规划
// 👍 696 👎 0

package com.lt.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @Date 2020/7/10
 **/
public class m332零钱兑换 {
    Map<Integer, Integer> mem = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (mem.containsKey(amount)) return mem.get(amount);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int j = coinChange(coins, amount - coins[i]);
            if (j == -1) continue;
            min = Math.min(min, j + 1);
        }
        mem.put(amount, min == Integer.MAX_VALUE ? -1 : min);
        return mem.get(amount);
    }


    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i < coin) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        m332零钱兑换 entity = new m332零钱兑换();
        System.out.println(entity.coinChange2(new int[]{2, 5, 10, 1}, 27));
    }


}
