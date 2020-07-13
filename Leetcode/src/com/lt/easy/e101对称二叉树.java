//给定一个二叉树，检查它是否是镜像对称的。
//
//
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
//
//
//
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
//
//
//
//
// 进阶：
//
// 你可以运用递归和迭代两种方法解决这个问题吗？
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 893 👎 0

package com.lt.easy;

import com.lt.commonStruct.TreeNode;

/**
 * @author liangtao
 * @Date 2020/7/8
 * 思路：
 * 递归一层层对比
 * 或者逆向编程数组，对比
 **/
public class e101对称二叉树 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recursiveCheck(root.left, root.right);
    }

    public boolean recursiveCheck(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if ((left == null || right == null) || (left.val != right.val)) return false;
        return recursiveCheck(left.right, right.left) && recursiveCheck(left.left, right.right) && left.val == right.val;
    }


    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 2, 2, 3, 4, 4, 3};
        TreeNode threeNode = TreeNode.createThree(nums);
        e101对称二叉树 entity = new e101对称二叉树();
        System.out.println(entity.isSymmetric(threeNode));

    }
}
