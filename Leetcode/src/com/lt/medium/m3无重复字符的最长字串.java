//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3: aknfufqopmcjihnhaytgdjkgfdlxbnbgtzvc
//
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// Related Topics 哈希表 双指针 字符串 Sliding Window

package com.lt.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @Date 2020/6/19
 **/
public class m3无重复字符的最长字串 {
    public int lengthOfLongestSubstring(String s) {
        int length = s.length(), max = 0;
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int start = 0, end = 0; end < length; end++) {
            if (indexMap.containsKey(s.charAt(end))) {
                start = Math.max(indexMap.get(s.charAt(end)),start);
            }
            max = Math.max(end - start + 1, max);
            indexMap.put(s.charAt(end), end + 1);
        }
        return max;
    }
}
