package com.lt.creational.singleton;

/**
 * @author liangtao
 * @description 饿汉式静态常量实现
 * @Date 2021/1/24
 **/
public class Singleton1 {
    private Singleton1(){
        //私有化构造器
    }
    //内部持有一个静态实例
    private static final Singleton1 INSTANCE = new Singleton1();

    //通过向外暴露方法返回该类的实例对象
    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}

