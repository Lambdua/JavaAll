//给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
// 例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回其自底向上的层次遍历为：
//
// [
//  [15,7],
//  [9,20],
//  [3]
//]
//
// Related Topics 树 广度优先搜索
// 👍 264 👎 0


package com.lt.easy;

import com.lt.commonStruct.TreeNode;

import java.util.*;

/**
 * @author liangtao
 * @Date 2020/7/15
 **/
public class e107二叉树的层次遍历 {
    List<List<Integer>> cenList = new LinkedList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        recurisiveGet(root, 0);
        Collections.reverse(cenList);
        return cenList;
    }

    public void recurisiveGet(TreeNode node, int N) {
        if (node == null) return;
        if (N >= cenList.size()) {
            ArrayList<Integer> item = new ArrayList<>();
            item.add(node.val);
            cenList.add(item);
        } else {
            cenList.get(N).add(node.val);
        }
        recurisiveGet(node.left, N + 1);
        recurisiveGet(node.right, N + 1);
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.createThree(0,2,4,1,null,3,-1,5,1,null,6,null,8);
        e107二叉树的层次遍历 entity = new e107二叉树的层次遍历();
        List<List<Integer>> lists = entity.levelOrderBottom(node);
        lists.forEach(list -> list.forEach(item -> System.out.println(item)));
    }
}
