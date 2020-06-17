package com.yu.volatileTest;

// volatile 不保证原子性
public class VilatileDemo02 {
    // volatile 不保证原子性
    private volatile static int num = 0;
    public static void add(){
        num++;
    }
    public static void main(String[] args) {
        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000 ; j++) {
                    add();
                }
            }).start();
        }
        // Thread.yield() TODO
        while (Thread.activeCount()>2){ // main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
