package com.lt.creational.singleton.instance1;

import com.lt.creational.singleton.instance2.SingletonInstance2StaticClass;
import lombok.Data;

/**
 * @author liangtao
 * @Date 2019/9/19
 **/
@Data
public class SingletonInstance2Enum {

    String name;
    String age;


    private SingletonInstance2Enum() {
    }

    ;

    public SingletonInstance2Enum(String name, String age) {
        this.name = name;
        this.age = age;
    }

    enum InstanceEnum {
        INSTANCE;
        private SingletonInstance2Enum instance = new SingletonInstance2Enum("单例", "12");

        public SingletonInstance2Enum getInstance() {
            return instance;
        }
    }


    public static SingletonInstance2Enum getInstance() {
        return InstanceEnum.INSTANCE.getInstance();
    }

}
