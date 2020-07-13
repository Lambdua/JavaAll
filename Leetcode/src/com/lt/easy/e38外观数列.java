//给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
//
// 注意：整数序列中的每一项将表示为一个字符串。
//
// 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
//
// 1.     1
//2.     11
//3.     21
//4.     1211
//5.     111221
//6.     312211
//
//
// 第一项是数字 1
//
// 描述前一项，这个数是 1 即 “一个 1 ”，记作 11
//
// 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21
//
// 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211
//
// 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
//
//
//
// 示例 1:
//
// 输入: 1
//输出: "1"
//解释：这是一个基本样例。
//
// 示例 2:
//
// 输入: 4
//输出: "1211"
//解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似
//"1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
// Related Topics 字符串
package com.lt.easy;

/**
 * @author liangtao
 * @Date 2020/7/2
 **/
public class e38外观数列 {
    // 1.     1
//2.     11
//3.     21
//4.     1211
//5.     111221
//6.     312211

    /**
     * 暴力解法
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String seed = "1";
        StringBuilder sb = new StringBuilder();
        int j = 0;
        int current;
        int count=1;
        for (int i = 0; i < n-1; i++) {
            while (j < seed.length()) {
                //最后一个，直接拼接，返回
                if (j == seed.length() - 1) {
                    sb.append(String.valueOf(count)+seed.charAt(j));
                    break;
                }
                current=seed.charAt(j);
                if (seed.charAt(j+1)==current) count++;
                else {
                    sb.append(String.valueOf(count)+seed.charAt(j));
                    count=1;
                }
                j++;
            }
            j=0;
            count=1;
            seed=sb.toString();
            sb=new StringBuilder();
            System.out.println(i+1+": "+seed);
        }
        return seed;
    }
    public static void main(String[] args){
        e38外观数列 entity = new e38外观数列();
        System.out.println(entity.countAndSay(100));

    }
}
