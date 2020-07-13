package com.lt.Y19_10.week1.day2;

/**
 * @author liangtao
 * @Date 2019/10/9
 * <p>
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 **/
public class Solution13 {
    public int romanToInt(String s) {
        int nums[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String pha[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int result = 0;
        for (int i = 0; i < pha.length; i++) {
            while (s.startsWith(pha[i])) {
                result += nums[i];
                s=s.substring(pha[i].length());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution13 solution13 = new Solution13();
        System.out.println(solution13.romanToInt("MCMXCIV"));
    }
}
