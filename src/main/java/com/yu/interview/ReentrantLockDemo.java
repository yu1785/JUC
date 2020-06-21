package com.yu.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yu
 * @date 2020/6/20 16:14
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {

        Phone phone = new Phone();

        // Synchronized
        new Thread(()->{
            phone.sendSMS();
        },"t1").start();

        new Thread(()->{
            phone.sendSMS();
        },"t2").start();


        // ReentrantLock
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();

    }

}

class Phone implements Runnable{
    // Synchronized
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail");
    }


    // ReentrantLock
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get");
            set();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked set");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
