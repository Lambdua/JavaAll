package com.lt.Y19_9.week1.day3;

import java.util.ArrayList;

/**
 * @author liangtao
 * @Date 2019/9/18
 * 设计一个算法，并编写代码来序列化和反序列化二叉树。
 * 将树写入一个文件被称为“序列化”，读取文件后重建同样的二叉树被称为“反序列化”。
 * <p>
 * 如何反序列化或序列化二叉树是没有限制的，你只需要确保可以将二叉树序列化为一个字符串，并且可以将字符串反序列化为原来的树结构。
 **/
public class Solution1 {
    /*
     * TODO
      输入：{3,9,20,#,#,15,7}
      输出：{3,9,20,#,#,15,7}
解释：
二叉树 {3,9,20,#,#,15,7}，表示如下的树结构：
	  3
	 / \
	9  20
	  /  \
	 15   7
它将被序列化为 {3,9,20,#,#,15,7}
     **/



    public static String serialize(TreeNode root) {
        // write your code here
        if (root==null){
            return "{}";
        }
        ArrayList<TreeNode> list=new ArrayList<>();
        list.add(root);
        for(int i=0;i<list.size();i++){
            if (list.get(i)==null){
                continue;
            }
            list.add(list.get(i).left);
            list.add(list.get(i).right);
        }

        StringBuilder sb=new StringBuilder();
        sb.append("{"+list.get(0).val);
        for (int i = 1; i < list.size(); i++) {
           if (list.get(i)==null){
               sb.append(",#");
           }else {
               sb.append(",");
               sb.append(list.get(i).val);
           }
        }
        sb.append("}");
        return sb.toString();

   }


    public static void main(String[] args) {

        Solution1 solutio = new Solution1();
        TreeNode treeNode = solutio.new TreeNode(2);
        treeNode.right = solutio.new TreeNode(3);
        TreeNode left = solutio.new TreeNode(1);
        treeNode.left=left;
        left.right=null;
        left.left=solutio.new TreeNode(0);

        System.out.println(serialize(treeNode));
    }

    public TreeNode deserialize(String data) {
        // write your code here
        if (data.equals("{}")) {
            return null;
        }
        String[] vals = data.substring(1, data.length() - 1).split(",");
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.add(root);
        int index = 0;
        boolean isLeftChild = true;
        for (int i = 1; i < vals.length; i++) {
          if (!vals[i].equals("#")){
           TreeNode node=new TreeNode(Integer.parseInt(vals[i]));
           if (isLeftChild){
               queue.get(index).left=node;
           }else {
               queue.get(index).right=node;
           }
           queue.add(node);
          }

            if (!isLeftChild) {
                index++;
            }
            isLeftChild=!isLeftChild;
        }
       return root;
    }


    private class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
