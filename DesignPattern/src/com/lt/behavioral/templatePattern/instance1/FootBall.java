package com.lt.behavioral.templatePattern.instance1;

/**
 * @author liangtao
 * @Date 2019/9/26
 **/
public class FootBall extends Game {
    int age;

    public FootBall(int age) {
        this.age = age;
    }

    @Override
    protected void initial() {
        System.out.println("足球初始化");
    }

    @Override
    protected void doGanme() {
        System.out.println("足球play");
    }

    @Override
    protected void destory() {
        System.out.println("足球销毁");
    }

    @Override
    protected boolean allowPlay() {

        if (age>12) {
            return true;
        }else {
            return false;
        }
    }
}
