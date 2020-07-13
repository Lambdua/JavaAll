//实现 strStr() 函数。
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。
//
// 示例 1:
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
//
//
// 示例 2:
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
//
//
// 说明:
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
// Related Topics 双指针 字符串

package com.lt.easy;

import java.util.Arrays;

/**
 * @author liangtao
 * @Date 2020/6/29
 **/
public class e28实现strStr方法 {

    /**
     * sundays算法 \
     * 定义： i: txt当前索引，patLength:pat长度
     * txt: substring searching
     * pat: search
     * 1.
     * 逐个匹配，至 u!=e 此时 i=0(pat头s对应的txt位置), 查看txt[patLength+i]是否在pat中，查的'i'不存在pat中
     * 则i移动至'i'后面：i+=patLength+1
     * 2.
     * txt: substring searching
     * pat:        search
     * 此时发现不匹配，i=7,查看txt[patLength+i]='r' 在pat中，查找'r'在pat的最后索引位置 j=3
     * 则i移动至 i+=(patLength-j) ==> i=10
     * 3.
     * txt: substring searching
     * pat:           search
     * 匹配,返回i
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int patLength = needle.length();
        int txtLength = haystack.length();
        if (txtLength < patLength) return -1;
        int i = 0;
        if (isStrEqule(haystack.substring(i, i + patLength), needle)) {
            return i;
        }
        while (i < txtLength && i + patLength < txtLength) {
            Integer containIndex = containChar(needle, haystack.charAt(i + patLength));
            if (containIndex == -1) {
                i += patLength + 1;
            } else {
                i += (patLength - containIndex);
            }
            if ((i + patLength) > txtLength) return -1;
            if (isStrEqule(haystack.substring(i, i + patLength), needle)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isStrEqule(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) return false;
        }
        return true;
    }

    /**
     * 查找，存在返回最末索引，否则返回-1
     *
     * @param txt
     * @param pat
     * @return
     */
    public Integer containChar(String txt, Character pat) {
        for (int i = txt.length() - 1; i >= 0; i--) {
            if (txt.charAt(i) == pat) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        e28实现strStr方法 entity = new e28实现strStr方法();
        String haystack = "hello";
        String needle = "llo";
//        System.out.println(entity.strStr(haystack,needle));

        System.out.println(entity.sunday(haystack, needle));
    }


    public int sunday(String totalStr, String partStr) {
        //ASCII_SIZE=126,一共有126个字符
        int[] move = new int[126];
        int partLength = partStr.length();
        int totalLength = totalStr.length();
        Arrays.fill(move, partLength);
        //设置每个字符匹配到以后，能够跳跃的长度
        for (int i = 0; i < partLength; i++) {
            move[partStr.charAt(i)] = partLength - i;
        }
        int s = 0;//模式串头部在字符串位置
        int j;//模式串已经匹配了的长度
        while (s <= totalLength - partLength) {//到达末尾之前
            j = 0;
            while (totalStr.charAt(s + j) == partStr.charAt(j)) {
                j++;
                if (j >= partLength) {
                    return s;
                }
            }
            s += move[totalStr.charAt(s + partLength)];
        }
        return -1;
    }

}
