package com.lt.Y19_9.week2.day6;

/**
 * @author liangtao
 * @Date 2019/9/28
 * <p>
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * <p>
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，
 * 作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * <p>
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
 * 如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231)
 * <p>
 * 例 1:
 * 输入: "42"
 * 输出: 42
 * <p>
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * <p>
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * <p>
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231)
 * <p>
 * 思考:
 * 首先对于字符进行去空格,判断首字符是否为非数字或者非+-符号,若不是,则直接返回,不进行转换
 * 其次,可以用一个String BUilder进行存储数字,最后判断大小是否超出范围,超出则返回边际值,否则返回该值.  ★:只能存储32位的情况下,此方法不适用
 * <p>
 * 只能存储32位时,同Solution_7的判断溢出思想一致.
 **/
public class Solution_8 {
    public int myAtoi(String str) {
        char[] chars = str.trim().toCharArray();
        if (chars.length==0) {
            return 0;
        }
        //进行判断首字符
        boolean flag = false;
        char firstChar = chars[0];
        int current = 0;
        int pop;
        if (firstChar != '+' && firstChar != '-' && firstChar > '9' && firstChar < '0') {
            return 0;
        }
        if (firstChar == '-'||firstChar=='+') {
            flag = true;
        }
        int i = flag ? 1 : 0;
        for (; i < chars.length; i++) {

            if (chars[i] <= '9' && chars[i] >= '0') {
                pop = Integer.parseInt(chars[i] + "");
                if (firstChar=='-') {
                    if (current > Integer.MAX_VALUE / 10 + 1 || (current == Integer.MAX_VALUE / 10 && pop > 8)) {
                        return Integer.MIN_VALUE;
                    }
                    current = current * 10 + pop;
                } else {
                    if (current > Integer.MAX_VALUE / 10 || (current == Integer.MAX_VALUE / 10 && pop > 7)) {
                        return Integer.MAX_VALUE;
                    }
                    current = current * 10 + pop;
                }

            } else {
                return firstChar!='-' ? current : -current;
            }
        }
        return firstChar!='-' ? current : -current;
    }


    public static void main(String[] args) {
        Solution_8 solution_8 = new Solution_8();

//        solution_8.myAtoi("-123a");
        System.out.println(solution_8.myAtoi("-91283472332"));
        System.out.println(solution_8.myAtoi("words and 987"));
        System.out.println(solution_8.myAtoi("  -4193 with words"));

        System.out.println(solution_8.myAtoi(" "));
    }
}

