package com.lt.Y19_9.week2.day2;

import java.util.*;

/**
 * @author liangtao
 * @Date 2019/9/24
 * 请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 *
 * 思考:
 *  滑动窗口的思想,一个队列当作一个窗口,
 *  每次进入队列时,判断队列中是否有该字符,若存在 则一直排除,知道重复字符排除
 *
 **/
public class Solution_3 {
    public static int lengthOfLongestSubstring(String s) {


        if (s.equals("")||s==null){
            return 0;
        }
        Queue<String> window=new LinkedList<>();
        String[] split = s.split("");

        //a,b,c,a,b,c,d
        int max=0;

        Set<String> set=new LinkedHashSet<>();
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if (!set.contains(temp)){
                set.add(temp);
                window.add(temp);
                max=max<window.size()?window.size():max;
            }else {
                 window.add(temp);

                while (!window.peek().equals(temp)){
                    String poll = window.poll();
                    set.remove(poll);
                    /*if (!poll.equals(temp)){
                        set.remove(poll);
                    }*/
                }
                window.poll();
                max=max<window.size()?window.size():max;
            }

        }
        return max;
    }


    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("tmmzuxt"));
    }
}
