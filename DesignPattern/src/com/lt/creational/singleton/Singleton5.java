package com.lt.creational.singleton;

/**
 * @author liangtao
 * @description 懒汉式 线程不安全的写法
 * @Date 2021/1/24
 **/
public class Singleton5 {
    //内部持有一个自身静态实例引用,这里使用volatile关键字
    private volatile static Singleton5 INSANCE;

    private Singleton5() {
        //私有化构造器
    }

    public static Singleton5 getInstance() {
        //双重判断锁校验
        if (INSANCE == null) {
            synchronized (Singleton5.class) {
                if (INSANCE == null) {
                    INSANCE = new Singleton5();
                }
            }
        }
        return INSANCE;
    }
}
