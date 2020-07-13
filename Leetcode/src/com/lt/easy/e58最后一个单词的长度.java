//给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
//
// 如果不存在最后一个单词，请返回 0 。
//
// 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
//
//
//
// 示例:
//
// 输入: "Hello World"
//输出: 5
//
// Related Topics 字符串
package com.lt.easy;

/**
 * @author liangtao
 * @Date 2020/7/3
 **/
public class e58最后一个单词的长度 {
    public static int lengthOfLastWord(String s) {
        //A-Z 65-90
        //a-z 97-122
        int size = 0;
        int i = s.length() - 1;
        for (; i >= 0 && s.charAt(i) == ' '; i--) ;
        if (i < 0) return 0;
        int j = i;
        for (; j >= 0 && s.charAt(j) != ' '; j--) ;
        return i - j;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("helloxword"));
    }
}
