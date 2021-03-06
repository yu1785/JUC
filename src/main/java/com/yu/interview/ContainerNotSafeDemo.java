package com.yu.interview;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zxy
 * @date 2020/6/19 15:41
 *
 * 集合类不安全问题
 *      ArrayList、HashSet、HashMap
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {

//        List<String> list = Arrays.asList("a","b","c");
//        list.forEach(System.out::println);

//        List<String> list = new ArrayList<>();
        // ArrayList 线程不安全
        // java.util.ConcurrentModificationException 并发修改异常

        // 1. Vector 其add方法有synchronized关键字
//        List<String> list = new Vector<>();

        // 2. Collections.synchronizedList(new ArrayList<>()) 其add方法有synchronized关键字
//        List<String> list = Collections.synchronizedList(new ArrayList<>());


        // 3、List<String> list = new CopyOnWriteArrayList<>()；
        // add 方法加了锁
        List<String> list = new CopyOnWriteArrayList<>();

        // new CopyOnWriteArraySet<>() 的底层是 CopyOnWriteArrayList<E>()
        // Set<String> set = new CopyOnWriteArraySet<>();

//        Map<Object, Object> map = new ConcurrentHashMap<>();


        // TODO
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
