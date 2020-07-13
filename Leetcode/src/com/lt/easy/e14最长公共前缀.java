//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
//
//
// 说明:
//
// 所有输入只包含小写字母 a-z 。
// Related Topics 字符串

package com.lt.easy;

/**
 * @author liangtao
 * @Date 2020/6/19
 **/
public class e14最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String max = strs[0];
        for (String str : strs) {
            int i;
            for (i = 0; i < str.length(); i++) {
                if (max.length() <= i || max.charAt(i) != str.charAt(i)) {
                    break;
                }
            }
            max = max.substring(0, i);
        }
        return max;
    }

    public static void main(String[] args) {
//        String[] strs = new String[]{"flower", "flow1233333333333333", "floight"};
        String[] strs = new String[]{"d"};
        e14最长公共前缀 entity = new e14最长公共前缀();
        System.out.println(entity.longestCommonPrefix(strs));
    }


}
