package com.lt.Y19_9.week1.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/21
 *
 * https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
 **/
public class Solution1_improve {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }


        Arrays.sort(nums);
        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), results);

        return results;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<Integer>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }


            //当前位置不是第一个,且当前层前面存有同样的分支节点
            if (i>0&&nums[i]==nums[i-1]&&visited[i-1]){
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;

            dfs(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}
