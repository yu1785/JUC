package com.yu.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 抢车位！
 * 6车---3个停车位置
 * semaphore.acquire() 获得，假设如果已经满了，等待，等待被释放为止！
 * semaphore.release(); 释放，会将当前的信号量释放 + 1，然后唤醒等待的线程！
 * 作用： 多个共享资源互斥的使用！并发限流，控制最大的线程数！
 *
 * @author yu
 * @date 2020/6/12 8:03
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程数量：停车位! 限流！
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    // acquire() 得到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // release() 释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }

}
