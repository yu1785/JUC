package com.yu.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zxy
 * @date 2020/6/19 15:08
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,1);

    public static void main(String[] args) {
        System.out.println("******** ABA问题的产生 *******");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"T1").start();

        new Thread(()->{
            // 暂停 1秒钟 保证线程 1完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 103)+"\t" + atomicReference.get());
        },"T2").start();

        System.out.println("******** ABA问题的解决 *******");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第1次版本号"+stamp);
            // 暂停一秒钟
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t 第2次版本号"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第3次版本号"+atomicStampedReference.getStamp());
        },"T3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            // 暂停 3秒钟 保证线程 3完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t 第1次版本号"+stamp);
            System.out.println(atomicStampedReference.compareAndSet(100, 103,
                    stamp, stamp + 1)+"\t 当前版本号"+atomicStampedReference.getStamp());
        },"T4").start();

    }

}
