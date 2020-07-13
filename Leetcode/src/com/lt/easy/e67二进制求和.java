//给你两个二进制字符串，返回它们的和（用二进制表示）。
//
// 输入为 非空 字符串且只包含数字 1 和 0。
//
//
//
// 示例 1:
//
// 输入: a = "11", b = "1"
//输出: "100"
//
// 示例 2:
//
// 输入: a = "1010", b = "1011"
//输出: "10101"
//
//
//
// 提示：
//
//
// 每个字符串仅由字符 '0' 或 '1' 组成。
// 1 <= a.length, b.length <= 10^4
// 字符串如果不是 "0" ，就都不含前导零。
//
// Related Topics 数学 字符串

package com.lt.easy;

/**
 * @author liangtao
 * @Date 2020/7/3
 **/
public class e67二进制求和 {
    public String addBinary(String a, String b) {

        StringBuilder sb = new StringBuilder();
        int ai = a.length() - 1, bi = b.length() - 1;
        int carry = 0;
        while (ai >= 0 || bi >= 0) {
            //循环递归加入到sb中
            int sum = (ai >= 0 ? Integer.parseInt(String.valueOf(a.charAt(ai--))) : 0)
                    + (bi >= 0 ? Integer.parseInt(String.valueOf(b.charAt(bi--))) : 0)
                    + carry;
            carry = sum / 2;
            sum = sum % 2;
            sb.append(sum);
        }
        if (carry > 0) sb.append(carry);
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        String a = "1111";
        String b = "11111";
        String s = new e67二进制求和().addBinary(a, b);
        System.out.println(s);
    }

}
