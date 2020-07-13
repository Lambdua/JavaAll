package com.lt.Y19_9.week1.day1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @Date 2019/9/16
 * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 *
 * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 0 到 n-1。
 *
 * 样例
 * Example1:
 * 给出 numbers = [2, 7, 11, 15], target = 9, 返回 [0, 1].
 * Example2:
 * 给出 numbers = [15, 2, 7, 11], target = 9, 返回 [1, 2].
 **/
public class Solution2 {
    public  static int[] twoSum(int[] numbers, int target) {
        int[] index=new int[2];
        for (int i = 0; i < numbers.length; i++) {
           if (numbers[i]<target){
               for (int j=i+1;j<numbers.length;j++){
                   if (numbers[j]<target||numbers[j]<0){
                       if (numbers[i]+numbers[j]==target) {
                           index[0]=i;
                           index[1]=j;
                       }
                   }
               }

           }
        }
        return index;
    }
    public static int[] otherTwoSum(int[] numbers,int target){
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.get(numbers[i])!=null){
                int[] result={map.get(numbers[i]),i};
                return result;
            }else {
                map.put(target-numbers[i],i);
            }
        }
       int[] result={};
        return result;
    }


    public static void main(String[] args) {
        int[] number={15, 2, 7, 11};
        int[] ints = otherTwoSum(number, 22);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }
}
