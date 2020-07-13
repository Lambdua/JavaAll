package com.lt.Y19_9.week2.day5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/27
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * 示例:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 思考:
 *  根据row和字符串长度计算出一个二维数组,按照规律进行插入,对于空格插入占位符,最后循环遍历获得结果.
 *
 *  插入:
 *      一个V一个V 的进行插入.
 *
 **/
public class Solution_6 {

    public static void main(String[] args) {
      /*  int row = 4;
        int length = 1;
        getIntArray(row, length);
*/
      String s="LEETCODEISHIRING";
        Solution_6 solution_6 = new Solution_6();
        solution_6.convert(s,3);


    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();

    }


    public static int[][] getIntArray(int row, int length) {
        int column = 0;
        //根据行数可得出V形所有字符个数
        int v = (row - 1) * 2;
        //一个V 形所占列数:
        int oneColumn = row - 1;
        //剩余的无法组成V的个数
        int last = length % v;
        if (last == 0) {
            //刚好组完V
            column = (length / v) * oneColumn;
        } else {
            //没有组完,计算多的个数,若个数多余行数,每多一个加一列,否走只加一列
            int addColumn = last / row > 1 ? last % row : 0;
            if (addColumn > 0) {
                column = (length / v) * oneColumn + 1 + addColumn;
            } else {
                column = (length / v) * oneColumn + 1;
            }
        }
        return new int[row][column];
    }
}
