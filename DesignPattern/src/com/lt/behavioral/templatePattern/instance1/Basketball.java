package com.lt.behavioral.templatePattern.instance1;

/**
 * @author liangtao
 * @Date 2019/9/26
 **/
public class Basketball extends Game {
    int age;

    public Basketball(int age) {
        this.age = age;
    }

    @Override
    void initial() {
        System.out.println("篮球初始化");
    }

    @Override
    protected void doGanme() {
        System.out.println("篮球play");
    }

    @Override
    protected void destory() {
        System.out.println("篮球销毁");

    }

    @Override
    protected boolean allowPlay() {
        if (age > 14) {
            return true;
        } else {
            return false;
        }
    }
}
