package com.yu.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zxy
 * @date 2020/6/19 10:49
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(0, 5));

        atomicInteger.getAndIncrement();
    }
}
