//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1：
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//
//
// 示例 2：
//
// 输入: "cbbcbd"
//输出: "bb"
//
// Related Topics 字符串 动态规划
// 👍 2402 👎 0
package com.lt.medium;

/**
 * @author liangtao
 * @Date 2020/7/9
 * 思路：
 * 1. 暴力循环
 * 2. 中心拓展
 * 3. 动态规划
 **/
public class m5最长回文子串 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int len = s.length();
        boolean db[][] = new boolean[len][len];
        int maxl = 0, maxr = 0, maxLen = 0;

        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || db[l + 1][r - 1])) {
                    db[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxl = l;
                        maxr = r;
                        maxLen = r - l + 1;
                    }

                }
            }
        }
        return s.substring(maxl, maxr + 1);
    }

    public static void main(String[] args) {
        m5最长回文子串 entity = new m5最长回文子串();
        System.out.println(entity.longestPalindrome("aaabaaaa"));
    }
}
