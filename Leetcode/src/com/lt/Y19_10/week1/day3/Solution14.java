package com.lt.Y19_10.week1.day3;

/**
 * @author liangtao
 * @Date 2019/10/10
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * 输入: ["aaa","aac","bbb","bbc","ab"]
 * 输出:""
 *
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 *
 * 思考:
 **/
public class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String maxPrefix=strs[0];
        for (int i=1;i<strs.length;i++){
            while (strs[i].indexOf(maxPrefix)!=0){
                maxPrefix=maxPrefix.substring(0,maxPrefix.length()-1);
                if (maxPrefix.isEmpty()) {
                    return "";
                }
            }
        }
        return maxPrefix;
    }

//    public String longestCommonPrefix2(String[] strs){
//        if (strs.length == 0) {
//            return "";
//        }
//
//    }



    public static void main(String[] args) {
        Solution14 solution14 = new Solution14();
        String s = solution14.longestCommonPrefix(new String[]{"flower", "flow", "floight"});
        System.out.println(s);
    }
}
