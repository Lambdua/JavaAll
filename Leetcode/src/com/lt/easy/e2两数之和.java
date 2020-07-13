package com.lt.easy;

import com.lt.commonStruct.ListNode;

/**
 * @author liangtao
 * @Date 2020/6/19
 **/
public class e2两数之和 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode header = new ListNode(0);
        ListNode node = header;
        int sum;
        int nextadd = 0;
        while (l1 != null || l2 != null || nextadd != 0) {
            sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + nextadd;
            node.next = new ListNode(sum % 10);
            node = node.next;
            nextadd=sum/10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return header.next;
    }

    public static void main(String[] args) {
//        ListNode l1 = create(2, 4, 3);
//        ListNode l2 = create(5, 6, 4);
        ListNode l1 = create(1);
        ListNode l2 = create(9, 9);
        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

     static ListNode create(int... item) {
        ListNode head = new ListNode(item[0]);
        ListNode node = head;
        for (int i = 1; i < item.length; i++) {
            node.next = new ListNode(item[i]);
            node = node.next;
        }
        return head;
    }
}

