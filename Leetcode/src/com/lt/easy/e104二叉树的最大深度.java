//给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例：
//给定二叉树 [3,9,20,null,null,15,7]，
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回它的最大深度 3 。
// Related Topics 树 深度优先搜索
// 👍 593 👎 0
package com.lt.easy;

import com.lt.commonStruct.TreeNode;

/**
 * @author liangtao
 * @Date 2020/7/9
 **/
public class e104二叉树的最大深度 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return maxDepth(root.left, root.right, 1);
    }

    /**
     * @param left
     * @param right
     * @param N     父节点的深度
     * @return
     */
    public int maxDepth(TreeNode left, TreeNode right, int N) {
        if (left == null && right == null) {
            return N;
        }
        if (left == null) {
            return maxDepth(right.left, right.right, N + 1);
        }
        if (right == null) {
            return maxDepth(left.left, left.right, N + 1);
        }
        return Math.max(maxDepth(left.left, left.right, N + 1), maxDepth(right.left, right.right, N + 1));
    }

}
