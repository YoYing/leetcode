package com.yingxy.structure.list.linked;

import com.yingxy.structure.list.MyAbstractList;

/**
 * @description: 单向链表
 * @author: yingxiuyong
 * @create: 2021-02-19
 **/
public class MySingleLinkedList<E> extends MyAbstractList<E> {

    /**
     * 链首
     */
    private Node<E> first;

    @Override
    public void clear() {
        first = null;
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

        if (index == 0) {
            first = new Node<E>(element, null);
        } else {
            Node<E> oldNode = node(index);
            // 将新的 element 放到 index, 并且将前面的 next 指向新添加的，新添加的 next 指向原来的
            Node<E> prev = node(index - 1);
            prev.next = new Node<E>(element, prev.next);
        }

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        // 删除第一个的话，只需要将 first 指向它的 next
        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        } else {
            // 删除中间的任何一个的话，需要将 index 的 node 挖去，将 index - 1 的 next 指向 index 的 next
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }

        // 有效元素个数需要减去 1
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

        // 从 first 不断的 next 获取 index 对应的 node
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
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
         * 指向下一个 node，形成链
         */
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
