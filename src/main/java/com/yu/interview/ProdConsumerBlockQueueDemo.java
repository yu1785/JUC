package com.yu.interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG = true; // 默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.getAndIncrement() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if(retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列:" + data  + "成功" );
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列:" + data  + "失败" );
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 停止生产，表示FLAG=false，生产结束");
    }

    public void myConsumer() throws Exception{
        String retValue;
        // 多线程环境的判断，一定要使用while进行，防止出现虚假唤醒
        // 当FLAG为true的时候，开始生产
        while(FLAG) {
            // 2秒存入1个data
            retValue = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (retValue == null || retValue.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 消费失败，队列中已为空，退出" );
                // 退出消费队列
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列:" + retValue  + "成功" );
        }
    }

    /**
     * 停止生产的判断
     */
    public void stop() {
        this.FLAG = false;
    }
}

/**
 * @author yu
 * @date 2020/6/21 17:52
 */
public class ProdConsumerBlockQueueDemo {
    public static void main(String[] args) {

        // 查看底层CPU核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 传入具体的实现类， ArrayBlockingQueue
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "prod").start();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");

            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        // 5秒后，停止生产和消费
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("5秒中后，生产和消费线程停止，线程结束");
        myResource.stop();
    }
}
