package com.yu.juc.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        //并发 多个线程操作同一个资源类,把资源丢入线程
        Ticket2 ticket = new Ticket2();

        //lambada表达式 (参数)->{代码}
        new Thread(()->{ for (int i = 0; i < 50; i++) ticket.sale(); },"A").start();
        new Thread(()->{ for (int i = 0; i < 50; i++) ticket.sale(); },"B").start();
        new Thread(()->{ for (int i = 0; i < 50; i++) ticket.sale(); },"C").start();
    }

}

// lock
class Ticket2{
    private int number = 50;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            //业务代码
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出第"+number--+"张票，还剩"+number+"张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}