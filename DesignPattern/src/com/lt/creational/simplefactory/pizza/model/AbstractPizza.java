package com.lt.creational.simplefactory.pizza.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liangtao
 * @description 披萨抽象类
 * @Date 2021/1/24
 **/
@Getter
@Setter
public abstract class AbstractPizza implements Pizza {
    private String name;
    private int type;

    public AbstractPizza(String name, int type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public Pizza prepare() {
        System.out.println(name + "正在准备制作");
        return this;
    }

    @Override
    public Pizza bake(int minute) {
        System.out.println(name + "开始烘烤" + minute + "分钟。");
        return this;
    }

    @Override
    public Pizza cut(int number) {
        System.out.println(name + "被切割成" + number + "份。");
        return this;
    }

    @Override
    public Pizza box() {
        System.out.println(name + "开始打包");
        return this;
    }


}
