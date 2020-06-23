package com.yu.interview;

import java.util.ArrayList;

/**
 * @author yu
 * @date 2020/6/23 11:14
 * 初始化堆内存大约为机器内存大小的1/64 .
 * 最大堆内存大小约为机器内存大小的1/4.
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {

        /*
        // 初始化堆内存大小
        long totalMemory = Runtime.getRuntime().totalMemory();
        // 最大堆内存大小
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMEROY(-Xms) = "+totalMemory+"字节 "+ (totalMemory/(double)1024/1024)+"MB");
        System.out.println("MAX_MEMEROY(-Xms) = "+maxMemory+"字节 "+ (maxMemory/(double)1024/1024)+"MB");
        */

        //TOTAL_MEMEROY(-Xms) = 126877696字节 121.0MB    初始化堆内存大小约为机器内存大小的1/64 .
        //MAX_MEMEROY(-Xms) = 1862270976字节 1776.0MB    最大堆内存大小约为机器内存大小的1/4.


//        System.out.println(System.getProperties());
        Thread.sleep(Integer.MAX_VALUE);
        System.out.println("****** hello GC ******");

//        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
