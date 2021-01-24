package com.lt.creational.singleton;

/**
 * @author liangtao
 * @description 静态内部类实现单例模式
 * @Date 2021/1/24
 **/
public class Singleton6 {
    private Singleton6() {
    }

    /*
    在父类Singleton6通过classLoader加载的时候，其静态内部类SingletonHolder并不会被加载
     */
    private static class SingletonHolder {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    /**
     * 调用该方法时，静态内部类会被加载，此时Singleton6实例才被创建
     * 实现了单例的懒加载,同时也是线程安全的
     */
    public static Singleton6 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
