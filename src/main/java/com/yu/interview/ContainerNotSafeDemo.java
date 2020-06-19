package com.yu.interview;

import java.util.*;

/**
 * @author zxy
 * @date 2020/6/19 15:41
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {

//        List<String> list = Arrays.asList("a","b","c");
//        list.forEach(System.out::println);

//        List<String> list = new ArrayList<>();
        // ArrayList 线程不安全
        List<String> list = new Vector<>();

        // TODO
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        // java.util.ConcurrentModificationException 并发修改异常

    }
}
