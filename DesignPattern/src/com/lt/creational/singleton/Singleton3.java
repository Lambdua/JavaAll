package com.lt.creational.singleton;

/**
 * @author liangtao
 * @description 懒汉式 线程不安全的写法
 * @Date 2021/1/24
 **/
public class Singleton3 {
    //内部持有一个自身静态实例引用
    private static Singleton3 INSANCE;

    private Singleton3() {
        //私有化构造器
    }

    /**
     * 在方法内部判断对象是否创建，没有创建则为内部的实例应用新建一个对象，如果已经创建了,返回实例
     * 应为没有加锁，存在并发问题，所以线程不安全的。
     * @author liangtao
     * @date 2021/1/24
     **/
    public static Singleton3 getInstance(){
        if (INSANCE==null){
            INSANCE=new Singleton3();
        }
        return INSANCE;
    }
}
