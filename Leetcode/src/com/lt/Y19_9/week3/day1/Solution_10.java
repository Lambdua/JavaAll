package com.lt.Y19_9.week3.day1;

/**
 * @author liangtao
 * @Date 2019/9/30
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * <p>
 * 思考:
 * 对于. 单词判断
 * 对于* 使用while判断,知道不符号时跳出循环
 **/
public class Solution_10 {
    public boolean isMatch(String s, String p) {
        if (p.trim().equals("*")) {
            return true;
        }
        //1. 对pattern尽心逐个字符匹配
        //  '.' 匹配任意单个字符
        //  '*' 匹配零个或多个前面的那一个元素
        int strIndex = 0;
        int strLength = s.length();
        for (int i = 0; i < p.length(); i++) {
            if (strIndex==strLength) {
                return true;
            }
            char tempChar = p.charAt(i);

            //pattern为'.'
            if (tempChar == '.') {
                if (strIndex < strLength-1) {
                    strIndex++;
                } else {
                    return true;
                }
            } else if (tempChar == '*') {//pattern为'*'
                boolean continueNext = true;
                char preChar = p.charAt(i - 1);
                while (continueNext) {
                    if (preChar == '.') {

                    }
                    continueNext = s.charAt(strIndex) == preChar ? true : false;
                    if (continueNext) {
                        strIndex++;
                    }
                    if (strIndex==strLength) {
                        return true;
                    }
                }
            } else {//pattern为字符
                boolean flag = s.charAt(strIndex) == p.charAt(i) ? true : false;
                if (strIndex < strLength&&flag){
                    strIndex++;
                }
                if (strIndex==strLength) {
                    return true;
                }
                if (!flag){
                    if (!(i < p.length() - 1 && p.charAt(i + 1) == '*')) {

                        return false;
                    }

                }
            }
        }
        return strIndex == strLength;
    }

    public static void main(String[] args) {
        Solution_10 solution_10 = new Solution_10();
        //"mississippi"
        //"mis*is*p*."


        //"mississippi"
        //"mis*is*ip*."
        System.out.println(solution_10.isMatch("mississippi", "mis*is*ip*."));
    }

}
