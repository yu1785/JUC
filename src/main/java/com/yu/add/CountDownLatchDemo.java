package com.yu.add;

import java.util.concurrent.CountDownLatch;

/**
 * @author yu
 * @date 2020/6/10 22:52
 */
//计数器
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        //总数6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            //jdk1.8后这里可以不用加final //TODO
            final int temp = i;
            new Thread(()->{
                System.out.println(temp);
                System.out.println(Thread.currentThread().getName()+" go out");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await(); //等待计数器归零，然后再向下执行
        System.out.println("Close the door");

    }

}
