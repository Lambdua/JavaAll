package com.lt.Y19_9.week1.day7_;

/**
 * @author liangtao
 * @Date 2019/9/22
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 **/
public class Solution2 {
    public ListNode add(ListNode l1, ListNode l2) {

        int sum = 0;
        ListNode result = new ListNode(0);
        ListNode temp = result;
        int carry = 0;
        int x, y;
        while (l1 != null || l2 != null) {
            x = l1 == null ? 0 : l1.val;
            y = l2 == null ? 0 : l2.val;
            sum = (carry + x + y);
            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }

        return result.next;


    }

    public ListNode reverListNode(ListNode nextNode) {
        //链表的反转
        ListNode header = null;
        if (nextNode.next == null) {
            header = nextNode;
            return nextNode;
        }
        ListNode temp = reverListNode(nextNode.next);
        nextNode.next.next = nextNode;
        nextNode.next = null;
        return temp;

    }

    //节点
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        ListNode root = solution2.new ListNode(1);
        ListNode node1 = solution2.new ListNode(8);
//        ListNode node2 = solution2.new ListNode(3);
//        ListNode node3 = solution2.new ListNode(4);
        root.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = null;
//        ListNode reverList = solution2.reverListNode(root);
//        System.out.println("d");


        ListNode root2 = solution2.new ListNode(0);
//        ListNode nodee1 = solution2.new ListNode(6);
//        ListNode nodee2 = solution2.new ListNode(4);
//        ListNode nodee3 = solution2.new ListNode(3);
//        root2.next = nodee1;
//        nodee1.next = nodee2;
//        nodee2.next = nodee3;
//        nodee3.next = null;


        ListNode listNode = solution2.add(root, root2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
