//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
//
// L   C   I   R
//E T O E S I I G
//E   D   H   N
//
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
//
// 请你实现这个将字符串进行指定行数变换的函数：
//
// string convert(string s, int numRows);
//
// 示例 1:
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
//输出: "LCIRETOESIIGEDHN"
//
//
// 示例 2:
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
//输出: "LDREOEIIECIHNTSG"
//解释:
//
//L     D     R
//E   O E   I I
//E C   I H   N
//T     S     G
//------------
//n=4 len=16
//|-c=0-|-c=1-| 6(c+1)-r
//0     6     12
//1   5 7   1113
//2 4   8 10  14 16
//3     9     15
// Related Topics 字符串
// 👍 737 👎 0
package com.lt.medium;

/**
 * @author liangtao
 * @Date 2020/7/9
 **/
public class m6字形变换 {
    public String convert2(String s, int numRows) {
        if (s == null || s.length() == 1 || numRows == 1) {
            return s;
        }
        int onceTotal = numRows * 2 - 2;
        StringBuilder sb = new StringBuilder();
        int start = 0, end = numRows - 1;
        int len = s.length();
        while (start < len) {
            sb.append(s.charAt(start));
            start += onceTotal;
        }
        for (int i = 1; i < numRows - 1; i++) {
            int firstIndex = i;
            int secondIndex = onceTotal - i;
            while (firstIndex < len || secondIndex < len) {
                if (firstIndex < len) {
                    sb.append(s.charAt(firstIndex));
                    firstIndex += onceTotal;
                }
                if (secondIndex < len) {
                    sb.append(s.charAt(secondIndex));
                    secondIndex += onceTotal;
                }
            }
        }
        while (end < len) {
            sb.append(s.charAt(end));
            end += onceTotal;
        }
        return sb.toString();
    }

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 1 || numRows == 1) {
            return s;
        }
        int[] indexs = new int[numRows];
        int length = s.length();
        int onceTotal = numRows * 2 - 2;
        char[][] rowsArray = new char[numRows][length];
        int j = 0;
        for (int i = 0; i < length; i++) {
            int ca = i % onceTotal;
            if (ca < numRows) {
                j = ca;
            } else {
                j = onceTotal - ca;
            }
            rowsArray[j][indexs[j]++] = s.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowsArray.length; i++) {
            sb.append(String.valueOf(rowsArray[i]).trim());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        m6字形变换 entity = new m6字形变换();
        System.out.println(entity.convert2("LEETCODEISHIRING", 4));
    }
}
