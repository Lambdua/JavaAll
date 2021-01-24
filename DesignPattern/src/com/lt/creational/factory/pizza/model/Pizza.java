package com.lt.creational.factory.pizza.model;

/**
 * @author liangtao
 * @description 披萨实体接口
 * @Date 2021/1/24
 **/
public interface Pizza {
    Pizza prepare();

    Pizza bake(int minute);

    Pizza cut(int number);

    Pizza box();
}
