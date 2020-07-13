package com.lt.Y19_9.week1.day1;

/**
 * @author liangtao
 * @Date 2019/9/16
 * 给定一个字符串（以字符数组的形式给出）和一个偏移量，根据偏移量原地旋转字符串(从左向右旋转)。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入:  str="abcdefg", offset = 3
 * 输出:  str = "efgabcd"
 * 样例解释:  注意是原地旋转，即str旋转后为"efgabcd"
 **/
public class Solution1 {
    public static void rotateString(char[] str, int offset) {
        if (str.length==0) return;
        if (offset>str.length) offset=offset%str.length;
        char temp;
        int index=0;
        for (int i = str.length - offset; i < str.length; i++) {
            temp = str[i];
            //将索引位置前的元素全部后移一位
            for (int j = i; j >= index; j--) {
                if (j > index) {
                    str[j] = str[j - 1];
                }
            }
            //空出的首位置放如temp
            str[index++] = temp;
        }

    }


}
