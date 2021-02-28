package com.yingxy.leetcode.链表;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * @author: yingxiuyong
 * @create: 2021-02-21
 **/
public class _876_链表的中间结点 {

    /**
     *  暴力穷举法
     */
    public ListNode middleNode01(ListNode head) {
        int count = 1;
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        nodeMap.put(count, head);
        while (head.next != null) {
            nodeMap.put(++count, head.next);
        }

        // 能整出需要
        int middleIndex = (count / 2) + 1;
        return nodeMap.get(middleIndex);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);



    }
}
