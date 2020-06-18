package com.yu.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    public static void main(String[] args) {
//        seeOkByVolatile();
        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        // 等待上面20个线程执行完成 再用main线程查看结果值
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t int type, final number value : "+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type, final number value : "+myData.atomicInteger);

    }

    // 可以保证可见性
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        // 线程操作资源类
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo6();
            System.out.println(Thread.currentThread().getName()+"\t update value : "+myData.number);
        },"A").start();

        // 第二个线程为 main 线程
        while (myData.number == 0){

        }

        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}

// 资源类
class MyData{
    static volatile int number = 0;
    public void addTo6(){
        this.number = 6;
    }

    // 测试原子性 已加 volatile
    public void addPlusPlus(){
        number ++;
    }

    // 原子性操作
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }

}