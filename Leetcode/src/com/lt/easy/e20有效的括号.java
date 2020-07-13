//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
//输出: true
//
//
// 示例 2:
//
// 输入: "()[]{}"
//输出: true
//
//
// 示例 3:
//
// 输入: "(]"
//输出: false
//
//
// 示例 4:
//
// 输入: "([)]"
//输出: false
//
//
// 示例 5:
//
// 输入: "{[()([])]}"
//输出: true
// Related Topics 栈 字符串

/*
规则：
 1. 括号必须成对出现
 2. 括号中的括号必须成对出现
思路：
    栈解决
 */
package com.lt.easy;

import java.util.*;

/**
 * @author liangtao
 * @Date 2020/6/23
 **/
public class e20有效的括号 {
    public static void main(String[] args) {
        String str = "[";
        e20有效的括号 entity = new e20有效的括号();
        System.out.println(entity.isValid(str));
    }

    public boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;
        Map<Character, Character> symbolMap = new HashMap<>();
        symbolMap.put('(', ')');
        symbolMap.put('[', ']');
        symbolMap.put('{', '}');
        Deque<Character> stack = new ArrayDeque<>();
        for (char symbol : s.toCharArray()) {
            if (symbolMap.containsKey(symbol)) {
                stack.push(symbol);
            } else if (symbolMap.containsValue(symbol)) {
                if (stack.size() == 0 || symbolMap.get(stack.pop()) != symbol) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
