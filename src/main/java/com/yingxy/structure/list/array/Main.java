package com.yingxy.structure.list.array;

import com.yingxy.structure.list.MyList;

/**
 * @description: 测试类
 * @author: yingxiuyong
 * @create: 2021-02-18
 **/
public class Main {
    public static void main(String[] args) {
        MyList<Integer> officialList = new MyArrayList<Integer>(2);
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
