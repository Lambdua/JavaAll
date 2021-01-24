package com.lt.creational.singleton;

/**
 * @author liangtao
 * @description 饿汉式模式 静态代码块
 * @Date 2021/1/24
 **/
public class Singleton2 {
    private Singleton2() {
        //私有化构造器
    }

    private static final Singleton2 INSTANCE;

    //在静态代码块中实例化对象
    static {
        INSTANCE = new Singleton2();
    }

    //通过向外暴露方法返回该类的实例对象
    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}
