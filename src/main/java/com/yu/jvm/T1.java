package com.yu.jvm;

/**
 * @author yu
 * @date 2020/6/23 17:14
 */
public class T1 {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        // 重复start会抛异常 java.lang.IllegalThreadStateException
        t1.start();
    }
}
