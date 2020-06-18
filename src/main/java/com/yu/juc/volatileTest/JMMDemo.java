package com.yu.juc.volatileTest;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private volatile static int num = 0;

    public static void main(String[] args) { // main
        new Thread(()->{ // 线程 1 对主内存的变化不知道的
            while (num==0){
                // 在这里加上sout后 由于其底层实现有同步代码块 所以具有了可见性 程序会停止
//                System.out.println(num);
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}

