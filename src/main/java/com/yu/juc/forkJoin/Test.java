package com.yu.juc.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 同一个任务，别人效率高你几十倍！
 */
public class Test {
    public static void main(String[] args) throws ExecutionException,InterruptedException {
//         test1(); // 12224   // 5289
         test2(); // 10038   // 5956
//         test3(); // 153     // 121
    }
    // 普通程序员
    public static void test1(){
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+" 时间："+(end-start));
    }
    // 会使用ForkJoin
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // ForkJoinDemo 继承 ForkJoinTask<Long>  向上转型
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        // 提交一个可运行的任务执行，并返回一个表示该任务的未来。 未来的get方法将返回null 成功完成时
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);// 提交任务
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+" 时间："+(end-start));
    }
    public static void test3(){
        long start = System.currentTimeMillis();
        // Stream并行流 () (]  // TODO 不懂
        // .reduce(0, Long::sum) 求和
        long sum = LongStream.rangeClosed(0L,
                10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum="+"时间："+(end-start));
    }
}