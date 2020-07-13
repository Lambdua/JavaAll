package com.lt.Y19_9.week2.day4;

/**
 * @author liangtao
 * @Date 2019/9/26
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 回文: 正读反读都是一样的.
 * <p>
 * 思考:
 * 暴力方法:
 * 检索所有子串,对每一个子串对半切分,一一比对,相等就是回文,记录最长子串,返回
 * 中心拓展法:
 * 以每一个字母为中心分别向左向右判断,记录最长回文.
 **/
public class Solution_5 {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length == 0) return "";
        String result = "";
        String oneMaxLength="";
        for (int i = 0; i < length; i++) {
            String temp1 = maxHuiwen(i, i, s);
            String temp2 = maxHuiwen(i, i + 1, s);
            oneMaxLength = temp1.length() > temp2.length() ? temp1 : temp2;
            if (oneMaxLength.length()>result.length()){
                result=oneMaxLength;
            }

        }

        return result;

    }

    public String maxHuiwen(int lIndex, int rIndex, String s) {
        String result = "";
        int l = lIndex;
        int r = rIndex;

        while ((l >= 0 && r < s.length()) && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        return s.substring(++l,r);
    }

    public static void main(String[] args) {
        String str="abcbaaa";
        Solution_5 solution5=new Solution_5();
        System.out.println(solution5.longestPalindrome(str));
    }

}
