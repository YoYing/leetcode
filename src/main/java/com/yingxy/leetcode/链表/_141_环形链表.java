package com.yingxy.leetcode.链表;

/**
 * @description: https://leetcode-cn.com/problems/linked-list-cycle/
 * @author: yingxiuyong
 * @create: 2021-02-21
 **/
public class _141_环形链表 {
    /**
     *  使用快慢指针的方式，如果有环的话，最后肯定会相遇
     */
    public boolean hasCycle(ListNode head) {
        // 如果空元素链表或者只有 1 个元素的链表，那显然是没有环的
        if (head == null || head.next == null) return false;

        // 慢指针, 每次走 1 步
        ListNode slow = head.next;
        // 快指针，每次都 2 步
        ListNode fast = head.next.next;

        // 因为 fast 每次走 2 步，如果 fast 的 next 为空了，那么说明是到头了
        while (fast != null && fast.next != null) {
            // 如果快指针和慢指针相遇了，那么就说明有环
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }
}
