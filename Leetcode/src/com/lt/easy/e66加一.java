//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。
//
// 示例 1:
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
//
//
// 示例 2:
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
//
// Related Topics 数组


package com.lt.easy;

import java.util.Arrays;

/**
 * @author liangtao
 * @Date 2020/7/3
 **/
public class e66加一 {
    public int[] plusOne(int[] digits) {
        if (digits.length == 1 && digits[0] == 0) return new int[]{1};
        return addOne(digits, digits.length - 1);
    }

    public int[] addOne(int[] digits, int index) {
        if (index < 0) {
            int[] newDigits = Arrays.copyOf(digits, digits.length + 1);
            newDigits[0] = 1;
            return newDigits;
        } else if (digits[index] == 9) {
            digits[index] = 0;
            return addOne(digits, --index);
        } else {
            digits[index] += 1;
            return digits;
        }
    }

    public static void main(String[] args) {
        e66加一 entity = new e66加一();
        for (int i : entity.plusOne(new int[]{9,9,9})) {
            System.out.printf(i + "");
        }
    }

}
