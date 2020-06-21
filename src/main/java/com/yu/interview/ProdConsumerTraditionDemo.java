package com.yu.interview;

/**
 * @author yu
 * @date 2020/6/21 17:24
 * 生产者消费者 传统版
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 */

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程 操作 资源类
 * 判断 干活 通知
 * 防止虚假唤醒机制  多线程判断 必须用while
 */
public class ProdConsumerTraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();
    }
}

class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 1.判断
            while (number != 0){
                // 等待
                condition.await();
            }
            // 2.干活
            number ++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3.通知唤醒
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number == 0){
                // 等待
                condition.await();
            }
            number --;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
