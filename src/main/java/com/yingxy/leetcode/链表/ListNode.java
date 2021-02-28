package com.yingxy.leetcode.链表;

/**
 * @description:
 * @author: yingxiuyong
 * @create: 2021-02-21
 **/
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
