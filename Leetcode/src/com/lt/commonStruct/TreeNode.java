package com.lt.commonStruct;

/**
 * @author liangtao
 * @Date 2020/7/7
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    /**
     * 输入 [1,2,3,4,5,6],   [1,null,3]
     * 0         1          1
     *         /  \         \
     * 1      2    3         3
     *       / \  /
     * 2    4  5 6
     * N=1,position=0 --> size=2^(N+1)-1 +(position+1)*2
     * @param nodes
     * @return 1+2+4+8
     */
    public static TreeNode createThree(Integer[] nodes) {
        TreeNode header = new TreeNode(nodes[0]);
        recursiveCreateTree(header,0,0,nodes);
        return header;
    }

    /**
     *
     * @param current 当前节点
     * @param N 所在层
     * @param position 所在层的第几个 0开始
     */
    public static void recursiveCreateTree(TreeNode current,int N,int position,Integer[] nodes){
        //出口 (1 - Math.pow(2, N+1)) / -1)+3*position+2 )
        if ((Math.pow(2,N+1)-1)+(position+1)*2-1> nodes.length) return;
        int baseIndex= (int) (Math.pow(2,N+1)-1);
        int leftIndex=baseIndex+2*position;
        int rightIndex=leftIndex+1;
        if (nodes[leftIndex]!=null){
            current.left=new TreeNode(nodes[leftIndex]);
            recursiveCreateTree(current.left,N+1,2*position,nodes);
        }
        if (rightIndex<nodes.length && nodes[rightIndex]!=null ){
            current.right=new TreeNode(nodes[rightIndex]);
            recursiveCreateTree(current.right,N+1,2*position+1,nodes);
        }
    }


}
