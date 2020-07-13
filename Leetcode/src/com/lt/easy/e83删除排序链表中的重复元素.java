//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
//
// 示例 1:
//
// 输入: 1->1->2
//输出: 1->2
//
//
// 示例 2:
//
// 输入: 1->1->2->3->3
//输出: 1->2->3
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)

package com.lt.easy;


import com.lt.commonStruct.ListNode;

/**
 * @author liangtao
 * @Date 2020/7/3
 **/
public class e83删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) return null;
        ListNode node = head;
        ListNode result = head;
        int currentVal = node.val;
        for (node = node.next; node != null ; node = node.next) {
            if ( node.val != currentVal){
                currentVal = node.val;
                result.next = node;
                result = result.next;
            }
        }
        result.next=null;
        return head;
    }
}



