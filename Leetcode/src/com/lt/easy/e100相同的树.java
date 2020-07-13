//给定两个二叉树，编写一个函数来检验它们是否相同。
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
//
// 示例 1:
//
// 输入:       1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//输出: true
//
// 示例 2:
//
// 输入:      1          1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//输出: false
//
//
// 示例 3:
//
// 输入:       1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//输出: false
//
// Related Topics 树 深度优先搜索
// 👍 391 👎 0

package com.lt.easy;


import com.lt.commonStruct.TreeNode;

/**
 * @author liangtao
 * @Date 2020/7/7
 * 思路：
 * 前序/中序/后序遍历
 **/
public class e100相同的树 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return midR(p, q);
    }

    public boolean midR(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if ((p==null &&q!=null) || (q==null && p!=null) || (p.val!=q.val)) return false;
//        if (p.val != q.val) return false;
        return midR(p.left, q.left) && midR(p.right, q.right);
    }

    public static void main(String[] args){
        Integer[] integers1 = {1, 1, 3};
        TreeNode three1 = TreeNode.createThree(integers1);
        Integer[] integers2 = {1, 2, 3};
        TreeNode three2 = TreeNode.createThree(integers2);
        e100相同的树 entity = new e100相同的树();
        System.out.println(entity.midR(three1, three2));
    }
}
