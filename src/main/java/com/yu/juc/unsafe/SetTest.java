package com.yu.juc.unsafe;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

//java.util.ConcurrentModificationException
public class SetTest {
    public static void main(String[] args) {

//        Set<String> set = new HashSet<>();

        // 工具類寫法
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());

        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
