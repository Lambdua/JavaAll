package com.lt.Y19_9.week1.day2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/17
 * 在数组中找到第 k 大的元素
 **/
public class Solution2 {
    public static int find(int[] nums,int n){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        list.sort((a,b)->b-a);
        return list.get(n-1);
    }

    public static void main(String[] args) {
        int[] sum={9,3,2,4,8};

        find(sum,3  );
    }
}
