package com.lt.Y19_9.week1.day1;

/**
 * @author liangtao
 * @Date 2019/9/16
 * 给定一个长为n的数组Ages, 其中第i个元素表示第i个人的年龄。求这个n个人，发送的好友请求的总数。其中，
 * <p>
 * 1.如果Age(B) <= (1/2)Age(A) + 7, A不会给B发请求:本身只给比自己年龄-7在乘2小的发送
 * 2.如果Age(B) > Age(A)， A不会给B发请求: 本身只给比自己年龄大的发送
 * 3.如果Age(B) < 100 and Age(A) > 100, A不会给B发请求 :本身大于一百岁只给大于一百的发送请求
 * 不满足1，2，3，则A会给B发请求。
 * <p>
 * 输入: Ages = [10,39,50]
 * 输出: 1
 * 解释:
 * 只有年龄为50的人会给年龄为39的人发送好友请求。
 * <p>
 * 输入: Ages = [101,79,102]
 * 输出: 1
 * 解释:
 * 只有年龄为102的人会给年龄为101的人发送好友请求。
 **/
public class Solution4 {
    public int friendRequest(int[] ages) {
        int count = 0;
        int a;
        int b;
        //首先循环遍历
        for (int i = 0; i < ages.length; i++) {
            a = ages[i];
            for (int j = 0; j < ages.length; j++) {
                b = ages[j];
                if (i == j || (a > 100 && b < 100) || a < b || ((a / 2) + 7) >= b) {
                    continue;
                } else {
                    count++;
                }
            }
        }

        return count;
    }

}
