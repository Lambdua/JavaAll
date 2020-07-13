package com.lt.Y19_9.week1.day3;

/**
 * @author liangtao
 * @Date 2019/9/18
 * 对于一个给定的 source 字符串和一个 target 字符串，
 * 你应该在 source 字符串中找出 ta
 * rget 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。
 *
 * 输入: source = "abcdabcdefg" ，target = "bcd"
 * 输出: 1
 *
 * 输入: source = "source" ， target = "target"
 * 输出:-1
 **/
public class Solution2 {
    public int strStr(String source, String target) {
        // Write your code here

        String[] splits = source.split("");
        String[] targets = target.split("");
        int sourceLength=splits.length;
        int tarLength=targets.length;
        if (sourceLength<tarLength) return -1;
        if (target.equals("")) return 0;

        for (int i = 0; i < sourceLength; i++) {
            int temp=i;
            for (int j = 0; j < tarLength; j++) {
                if (targets[j].equals(splits[temp++])){
                    if (j==tarLength-1){
                        return temp-tarLength;
                    }
                    if ((sourceLength-temp)<tarLength-j-1) return -1;
                    continue;
                }else {
                    if ((sourceLength-temp)<tarLength-1) return -1;
                    break;
                }
           }
        }
        return -1;
    }

    public static void main(String[] args) {
        int i = new Solution2().strStr("tartarget", "target");
        System.out.println(i);
    }


}
