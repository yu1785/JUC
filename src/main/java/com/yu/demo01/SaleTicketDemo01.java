package com.yu.demo01;

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        //并发 多个线程操作同一个资源类,把资源丢入线程
        Ticket ticket = new Ticket();

        //线程操作资源类
        //这个是函数式接口 以前的写法 以后用lambda表达式
        /*new Thread(new Runnable() {
            public void run() {

            }
        }).start();*/

        //lambada表达式 (参数)->{代码}
        new Thread(() ->{
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(() ->{
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        },"C").start();
    }

}

//资源类 OOP
class Ticket{
    private int number = 50;
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出第"+number--+"张票，还剩"+number+"张");
        }
    }
}