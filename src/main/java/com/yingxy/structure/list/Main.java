package com.yingxy.structure.list;

import com.yingxy.structure.list.linked.MyLinkedList;

/**
 * @description:
 * @author: yingxiuyong
 * @create: 2021-02-20
 **/
public class Main {
    public static void main(String[] args) {
        // MyAbstractList<Integer> officialList = new MyArrayList<>();
        // MyAbstractList<Integer> officialList = new MySingleLinkedList<>();
        MyAbstractList<Integer> officialList = new MyLinkedList<>();
        officialList.add(1);
        officialList.add(2);
        officialList.add(3);
        officialList.add(4);
        officialList.add(3, 100);
        officialList.add(1);
        officialList.add(1);

        System.out.println(officialList.toString());
        System.out.println(officialList.get(3));
        System.out.println(officialList.indexOf(100));

        officialList.remove(3);
        System.out.println(officialList.toString());
    }
}
