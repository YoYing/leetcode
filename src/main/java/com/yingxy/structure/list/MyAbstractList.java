package com.yingxy.structure.list;

/**
 * @description:
 * @author: yingxiuyong
 * @create: 2021-02-18
 **/
public abstract class MyAbstractList<E> implements MyList<E> {
    /**
     * 数组的实际元素的数量
     */
    protected int size;

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    protected void rangeCheck(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * A version of rangeCheck used by add and addAll.
     */
    protected void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    public void add(E element) {
        // 加到有元素的后一个位置
        add(size, element);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }
}
