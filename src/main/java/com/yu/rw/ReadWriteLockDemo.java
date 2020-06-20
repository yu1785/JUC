package com.yu.rw;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu
 * @date 2020/6/12 8:21
 *
 * ReadWriteLock
 *
 */
public class ReadWriteLockDemo {


}

//自定义缓存
class MyCache{

    //volatile 保证可见性，不能保证原子性
    private volatile Map<String,Object> map = new HashMap<>();

    // 存，写
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入OK");
    }
    // 取，读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取OK");
    }

}