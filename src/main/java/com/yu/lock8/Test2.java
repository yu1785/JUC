package com.yu.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 *  1.标准情况下，两个线程先打印 发短信or打电话？          1、发短信  2、打电话
 *  2.sendMsg()延迟4秒，两个线程先打印 发短信or打电话？   1、发短信  2、打电话
 *  3.增加一个普通方法后，先执行发短信or普通方法hello？   普通方法
 *  4.两个对象，两个同步方法，发短信or打电话？           打电话
 */
public class Test2 {
    public static void main(String[] args) {
        //两个对象 两个调用者，两把锁
        Phone2 phone = new Phone2();
        Phone2 phone2 = new Phone2();

        //锁的存在
        new Thread(()->{
            phone.sendMsg();
        },"A").start();

        //捕获一个等待时间 1s
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();

        new Thread(()->{
            phone.hello();
        },"C").start();

    }
}

class Phone2{
    /*
        synchronized 锁的对象是方法的调用者
        两个方法使用同一个锁 谁先拿到谁用
     */
    public synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }

    //没有锁 不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
