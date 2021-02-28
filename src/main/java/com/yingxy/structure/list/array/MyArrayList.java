package com.yingxy.structure.list.array;

import com.yingxy.structure.list.MyAbstractList;

/**
 * @description:
 * @author: yingxiuyong
 * @create: 2021-02-18
 **/
public class MyArrayList<E> extends MyAbstractList<E> {
    /**
     * 所有的元素，包括默认未使用的
     */
    private Object[] elements;

    /**
     * 默认 List 的容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int initCapacity) {
        // 默认容量为 10 个，如果传入的容量小于 10 个话，就使用 10 个作为初始化容量
        initCapacity = Math.max(DEFAULT_CAPACITY, initCapacity);
        // initCapacity = initCapacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : initCapacity;
        elements = new Object[initCapacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elements[index];
    }

    /**
     * 1. set 方法，直接将其值变更
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        E oldElement = (E) elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * 在确定的位置，插入一个元素。这个元素的 index 必须要在 size 之内
     * 1. 在 index 处添加元素的话，需要考虑边界问题
     * a. index 是否已经超过现数组的总长度
     * b. index 是否有效
     * <p>
     * 2. 需要有扩容
     * <p>
     * 2. 添加过后，size 需要 +1
     */
    @Override
    public void add(int index, E element) {
        // 查看
        rangeCheckForAdd(index);
        ensureExplicitCapacity(size + 1);

        // 给 index 位置腾出一个位置，所以 index 后面的都得往右移 1
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        // 检查是否越界
        rangeCheck(index);

        Object oldElement = elements[index];
        // 删除 index 这个坑之后，需要将 index 之后的元素整体往前移 1 位
        for (int i = index + 1; i < size; i++) {
            // 如果使用 i + 1 会越界
            elements[i - 1] = elements[i];
        }

        // 需要将有效数组的最后一位设为 null
        elements[--size] = null;
        return (E) oldElement;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    /**
     * 给 Array 进行扩容
     */
    private void ensureExplicitCapacity(int capacity) {
        // 原始数组的容量
        int oldCapacity = elements.length;
        // 如果原始的容量大于 "必须要的" 容量，直接返回
        if (oldCapacity > capacity) {
            return;
        }

        // 不够，需要扩容，扩容 1.5 倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newElements = new Object[newCapacity];
        // 将 oldElements 的元素复制到 newElements
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        // 将旧的 elements 指向新的 elements
        elements = newElements;
        System.out.println("数组容量由【" + oldCapacity + "】扩展到【" + newCapacity + "】");
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
