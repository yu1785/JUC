package com.yu.jvm;

/**
 * @author yu
 * @date 2020/6/24 16:54
 */
public class RefCountGC {
    private byte[] bigSize = new byte[2 * 1024 * 1024];//这个成员属性唯一的作用就是占用一点内存
    Object instance = null;

    public static void main(String[] args)
    {
        RefCountGC objectA = new RefCountGC();
        RefCountGC objectB = new RefCountGC();
        objectA.instance = objectB;
        objectB.instance = objectA;
        objectA = null;
        objectB = null;

        System.gc(); // 手动唤醒GC 并不是立刻开启GC
        // 当我们调用System.gc()的时候，其实并不会马上进行垃圾回收，甚至不一定会执行垃圾回收，查看系统源码可以看到
    }
}
