package com.lt.creational.singleton;

/**
 * @author liangtao
 * @description 懒汉式 线程不安全的写法
 * @Date 2021/1/24
 **/
public class Singleton4 {
    //内部持有一个自身静态实例引用
    private static Singleton4 INSANCE;

    private Singleton4() {
        //私有化构造器
    }

    /**
     * 在方法内部判断对象是否创建，没有创建则为内部的实例应用新建一个对象，如果已经创建了,返回实例.
     * 添加了同步锁,解决线程不安全问题
     * @author liangtao
     * @date 2021/1/24
     **/
    public static synchronized Singleton4 getInstance(){
        if (INSANCE==null){
            INSANCE=new Singleton4();
        }
        return INSANCE;
    }
}
