package com.yingxy.leetcode.链表;

/**
 * @description: https://leetcode-cn.com/problems/reverse-linked-list/
 * @author: yingxiuyong
 * @create: 2021-02-21
 **/
public class _206_反转链表 {

    /**
     * 使用递归的方式，链表
     */
    public ListNode reverseListRecursive(ListNode head) {
        // 如果是空链表或者只有 1 个元素的话，不需要反转
        if (head == null || head.next == null) return head;
        // 将原 head 之后所有的 node 都反转过来，然后将紧靠着原 head 的后一位的 next 指向原 head，最后将新链表的最后 1 个
        // 即原链表的 head 的 next 指向 null
        ListNode newListNode = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newListNode;
    }

    /**
     *  使用迭代的方式反转
     */
    public ListNode reverseListIteration(ListNode head) {
        if (head == null || head.next == null) return head;
        // 由于只给了 head，原链表的头部，那么就只能从原链表的头部 head 出发
        // 思路：从原链表中将每次迭代的新 "头" 抠出来，接到新的链表上，即 newHead 上
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }

        return newHead;
    }
}
