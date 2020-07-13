package com.lt.Y19_9.week1.day4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/19
 * 给定一个二叉查找树和范围[k1, k2]。按照升序返回给定范围内的节点值。
 * 输入：{20,8,22,4,12},10,22
 * 输出：[12,20,22]
 * 解释：
 *         20
 *        /  \
 *       8   22
 *      / \
 *     4   12
 * 它将被序列化为 {20,8,22,4,12}
 * [12,20,22]介于10和22之间
 **/
public class Solution1 {
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> result=new ArrayList<>();
        if (root==null) return  result;
        List<TreeNode> list=new ArrayList<>();
        list.add(root);
        for (int i=0;i<list.size();i++){
            if (list.get(i).left!=null){
                list.add(list.get(i).left);
            }
            if (list.get(i).right != null) {
                list.add(list.get(i).right);
            }
        }

       list.stream()
               .filter((node)->(node.val<=k2&&node.val>=k1))
               .sorted((a,b)->a.val-b.val)
               .forEach((node)->result.add(node.val));
        return result;

    }

    public static void main(String[] args) {
        Solution1 solutio = new Solution1();
        TreeNode treeNode = solutio.new TreeNode(2);
        treeNode.right = solutio.new TreeNode(3);
        TreeNode left = solutio.new TreeNode(1);
        treeNode.left=left;
        left.right=null;
        left.left=solutio.new TreeNode(0);


        solutio.searchRange(treeNode,1,2);
    }

     class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
