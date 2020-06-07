package com.yu.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 7. 1个静态的同步方法，1个普通的同步方法 ，一个对象，先打印 发短信？打电话？   打电话
 * 8. 1个静态的同步方法，1个普通的同步方法 ，两个对象，先打印 发短信？打电话？   打电话
 */
public class Test4 {

    public static void main(String[] args) {
        //两个对象的class类的模板只有一个 static，锁的是class
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();

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
            phone.call();
        },"B").start();


    }
}

class Phone4{

    // 静态的同步方法 锁的是class类模板
    public static synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通同步方法 锁的是调用者
    public synchronized void call(){
        System.out.println("打电话");
    }

}
