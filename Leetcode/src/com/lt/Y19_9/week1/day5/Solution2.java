package com.lt.Y19_9.week1.day5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/20
 * 给定一个数字列表，返回其所有可能的排列。
 * <p>
 * 输入：[1,2,3]
 * 输出：
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 思考:
 * 递归的思想,首先从数组取一个 进行全排列,即只有一个[num]
 * 然后一次每次递增一个在原有全排列的基础上进行插入
 * eg: 原有排列:[2]
 * 新增一个[3] ,插入有两种结果
 * [2,3],[3,2]
 * <p>
 * 在次新增[6] ,插入则有2*3 中结果
 * [6,2,3] [2,6,3],[2,3,6]
 * [6,3,2],[3,6,2],[3,2,6]
 * <p>
 * 循环递归直到原有数组全部插入完毕
 * <p>
 * 递归出口:数组插入完毕
 * 循环体:
 **/
public class Solution2 {
    public  List<List<Integer>> permute(int[] nums) {


        List<List<Integer>> result = new ArrayList<>(new ArrayList<>());

        if (nums.length==0){
            result.add(new ArrayList<>());
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            result = insertNum(result, nums[i]);
        }

        return result;
    }

    public  List<List<Integer>> insertNum(List<List<Integer>> list, int insertNum) {
        List<List<Integer>> result = new ArrayList<>();

        //如果等于0,就是第一个插入,实例化一个对象
        if (list.size() == 0) {
            List<Integer> line = new ArrayList<>();
            line.add(insertNum);
            result.add(line);
            return result;
        }

        for (int i = 0; i < list.size(); i++) {
            //遍历插入每一个集合
            List<Integer> line = list.get(i);

            for (int j = 0; j <= line.size(); j++) {
                //在j位置插入
                boolean isInsert=false;
                List<Integer> temp = new ArrayList<>();
                int index = 0;
                while (index < line.size()||!isInsert) {
                    if (index == j&&!isInsert) {
                        temp.add(insertNum);
                        isInsert=true;
                    } else {
                        temp.add(line.get(index++));
                    }
                }
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> permute =new Solution2().permute(new int[]{});
        permute.forEach(a -> a.forEach(System.out::print));
    }
}
