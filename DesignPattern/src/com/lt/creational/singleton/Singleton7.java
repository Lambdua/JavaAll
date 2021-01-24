package com.lt.creational.singleton;

/**
 * @author liangtao
 * @description 单例枚举实现
 * @Date 2021/1/24
 **/
public class Singleton7 {
    private Singleton7() {
    }

    /**
     * 枚举类型是线程安全的，并且只会装载一次
     */
    private enum Singleton7Enum {
        INSTANCE;
        private final Singleton7 singleton7;

        Singleton7Enum() {
            this.singleton7 = new Singleton7();
        }

        //可以省略，外部类可以直接拿到内部枚举的属性
        private Singleton7 getInstance(){
            return singleton7;
        }
    }

    public static Singleton7 getInstance() {
        return Singleton7Enum.INSTANCE.getInstance();
    }
}
