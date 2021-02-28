package com.yingxy.leetcode.链表;

/**
 * @description: https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * 链表至少包含两个节点。
 * 链表中所有节点的值都是唯一的。
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * 不要从你的函数中返回任何结果
 * @author: yingxiuyong
 * @create: 2021-02-21
 **/
public class _237_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        // 因为是单向链表，我们无法拿到需要删除的前面的一个 ListNode
        // 只是删除某个节点，可以想出不是必须要删除这个节点，完全可以将 node 这个节点变成 node 后面的那个节点
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }
}
