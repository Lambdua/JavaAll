package com.lt.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangtao
 * @Date 2020/6/19
 **/
public class MaxLengthStr {

    public static int maxLengthStr(String s) {
//        if (s == null || "".equals(s)) return 0;
//        String[] strs = s.split("");
        List<Character> window = new ArrayList<>();
        int max = 0;
        int currentIndex = 0;
        int size;
        int index;
        int nextadd=0;
        while (currentIndex < s.length()) {
            for (int i = currentIndex; i < s.length(); i++) {
                Character item = s.charAt(i);
                if (window.contains(item)) {
                    //窗口中包含该字符串，
                    size = window.size();
                    max = max > size ? max : size;
                    index = window.indexOf(item);
                    window = window.subList(index + 1, size);
                    if (size - index - 1 == 0) {
                       currentIndex+=size-nextadd;
                       nextadd=0;
                    }else {
                        currentIndex+=size-nextadd;
                        nextadd=size-index-1;
                    }
                    break;
                } else {
                    window.add(item);
                }
                if (i==s.length()-1){
                    currentIndex=s.length();
                }
            }
        }
        return  max > window.size() ? max : window.size();
    }

    public static  int getMax2(String s){
        int length = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < length; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
    public static void main(String[] args) {
//        System.out.println(maxLengthStr("pwwkew123456w23"));
//        System.out.println(maxLengthStr("dvdf"));
        System.out.println(getMax2("pwwkew123456w23"));
        System.out.println(getMax2("dvdf"));
    }
}
