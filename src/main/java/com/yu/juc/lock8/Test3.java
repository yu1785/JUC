package com.yu.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5.增加两个静态的同步方法，只有一个对象，先发短信or打电话？      发短信
 * 6.两个对象，增加两个静态的同步方法，先发短信or打电话？          打电话
 */
public class Test3 {

    public static void main(String[] args) {
        //两个对象的class类的模板只有一个 static，锁的是class
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();

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


    }
}

// Phone3唯一的一个class对象
class Phone3{
    /*
        synchronized 锁的对象是方法的调用者
        两个方法使用同一个锁 谁先拿到谁用
     */
    public static synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call(){
        System.out.println("打电话");
    }

}
