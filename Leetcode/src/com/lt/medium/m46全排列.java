package com.lt.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2020/7/10
 **/
public class m46全排列 {
    List<List<Integer>> allList = new LinkedList<List<Integer>>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        recursive(nums, track);
        return allList;
    }

    public void recursive(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            allList.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //排除不合法的选择
            if (track.contains(nums[i])) continue;
            //做选择
            track.add(nums[i]);
            //进入下一层决策树
            recursive(nums, track);
            //撤销选择
            track.removeLast();
        }

    }

    public static void main(String[] args) {
        m46全排列 entity = new m46全排列();
        List<List<Integer>> permute = entity.permute(new int[]{1, 2, 3});
        for (List<Integer> integers : permute) {
            System.out.println("integers = " + integers);
        }
    }
}
