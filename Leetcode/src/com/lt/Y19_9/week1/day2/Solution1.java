package com.lt.Y19_9.week1.day2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @Date 2019/9/17
 * 计算数字 k 在 0 到 n 中的出现的次数，k 可能是 0~9 的一个值。
 *
 * 输入：
 * k = 1, n = 12
 * 输出：
 * 5
 * 解释：
 * 在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12] 中
 * 我们发现 1 出现了 5 次 (1, 10, 11, 12)(注意11中有两个1)。
 **/
public class Solution1 {
    public static int count(int k,int n){
        Map<Integer,Integer> map=new HashMap<>();
        map.put(k,0);
        for (int i = 0; i <= n; i++) {
           int j=i;
           while (j>0){
               if (map.get(j%10)!=null){
                   map.put(k,map.get(k)+1);
               }
               j=j/10;
           }
        }

        return k==0?map.get(k)+1:map.get(k);
    }

    public static void main(String[] args) {
        System.out.println(count(0, 90));
    }
}
