package com.yingxy.structure.list.linked;

import com.yingxy.structure.list.MyAbstractList;

/**
 * @description: 单向链表
 * @author: yingxiuyong
 * @create: 2021-02-19
 **/
public class MyLinkedList<E> extends MyAbstractList<E> {

    /**
     * 链首
     */
    private Node<E> first;

    /**
     * 链尾
     */
    private Node<E> last;

    @Override
    public void clear() {
        // 和 jdk 源码有点不太一样，实际效果是一样的
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        // 拿到 index 的 node，将其 node.element 指向新的 element
        Node<E> oldNode = node(index);
        E oldElement = oldNode.element;
        oldNode.element = element;
        return oldElement;
    }

    /**
     * 添加的话，需要考虑边界
     * 1. 如果是新添加的，直接 first 指向该元素
     * 2. 如果不是添加到第一个话，需要重置链接关系
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) { // 添加的第一个，可以是 0 厚着末尾的
            Node<E> oldLast = last;
            // 当前新添加的节点
            Node<E> node = new Node<>(oldLast, element, null);
            if (last == null) {
                // 链表的第一个元素
                first = node;
            } else {
                // 末尾元素
                oldLast.next = node;
            }

            last = node;
        } else {
            // 先处理普通大众化，不考虑边界问题
            Node<E> oldNode = node(index);
            Node<E> oldNodePrev = oldNode.prev;
            Node<E> node = new Node<>(oldNodePrev, element, oldNode);
            if (oldNodePrev == null) {
                // 表明往链首插入
                first = node;
            } else {
                oldNodePrev.next = node;
            }

            oldNode.prev = node;
        }

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        // 先拿到需要删除的 node
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        // 一般逻辑，大众化的
        if (prev == null) {
            // 删除的是第一个元素
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            // 删除的是最后一个元素
            last = prev;
        } else {
            next.prev = prev;
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        // 如果这个 element 是 null 的话
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }

                node = node.next;
            }
        } else {
            // 如果 node 不为空
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }

                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }


    /**
     * 根据索引获取对应的 node
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        // 因为有了 first 以及 last，所以如果 index 在后半部分，从 last 开始
        // 如果 index 在前半部分，从 first 开始
        if (index < (size >> 1)) {
            // 从 first 不断的 next 获取 index 对应的 node，需要拿到 index 的前一个
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }

            return node;
        } else {
            // 从 last 不断的 prev 获取 index 对应的 node，需要拿到 index 的后一个
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }

            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            if (node(i).prev == null) {
                string.append("null").append("_");
            } else {
                string.append(node(i).prev.element).append("_");
            }

            string.append(node(i).element);

            if (node(i).next == null) {
                string.append("_").append("null");
            } else {
                string.append("_").append(node(i).next.element);
            }
        }
        string.append("]");
        return string.toString();
    }

    /**
     * 实际保存的元素
     */
    private static class Node<E> {
        /**
         * 这个 index 保存的具体的
         */
        E element;

        /**
         * 指向前面一个 node
         */
        Node<E> prev;

        /**
         * 指向下一个 node，形成链
         */
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }
}
