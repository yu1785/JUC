package com.yu.interview;

public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t SingletonDemo构造函数...");
    }

    // 使用synchronized关键字 太重了
    /*public static synchronized SingletonDemo getInstance(){
        if (instance == null){
            instance = new SingletonDemo();
        }
        return instance;
    }*/

    /**
     *  DCL(double-checked locking) 双重校验锁
     *  还不够保险 潜在隐患 可能存在指令重排
     *  因此需要在对象前加上volatile 禁止指令重排
     *          private static volatile SingletonDemo instance = null;
     * @return
     */
    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    // new SingletonDemo() 不是原子性操作 分三步
                    // 重排后可能出现对象为空
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {

        //获取CPU个数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 并发多线程后
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }

    }

}
