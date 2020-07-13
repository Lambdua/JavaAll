package com.lt.easy;

import com.lt.commonStruct.ListNode;

/**
 * @author liangtao
 * @Date 2020/6/28
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * Related Topics 链表
 **/
public class e21合并两个有序链表 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode header = new ListNode();
        ListNode current = header;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                current.next = l2;
                current = current.next;
                l2 = l2.next;
            } else if (l2 == null) {
                current.next = l1;
                current = current.next;
                l1 = l1.next;
            } else {
                if (l1.val < l2.val) {
                    current.next = l1;
                    current = current.next;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    current = current.next;
                    l2 = l2.next;
                }
            }
        }
        return header.next;
    }


}

